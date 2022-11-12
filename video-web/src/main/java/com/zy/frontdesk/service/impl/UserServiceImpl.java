package com.zy.frontdesk.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.frontdesk.entity.User;
import com.zy.frontdesk.entity.vo.LoginVo;
import com.zy.frontdesk.entity.vo.UserLoginResult;
import com.zy.frontdesk.entity.vo.UserParm;
import com.zy.frontdesk.mapper.UserMapper;
import com.zy.frontdesk.service.UserService;
import com.zy.jwt.JwtUtils;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-10-25 14:23:58
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private StringRedisTemplate template;

    @Resource
    private JwtUtils jwtUtils;

    /**
     * 用户注册
     */
    @Override
    public ResultVo Registered(User user) {
        System.out.println(user.getVerCode());
        String res = template.opsForValue().get(user.getUuid());
        if ( user.getVerCode() == null || user.getVerCode().equals(res)) {
            if ( StringUtils.isEmpty(user.getNetName())|| StringUtils.isEmpty(user.getUsername()) ||
                    StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getEmail())) {
                return ResultUtils.error("用户注册信息不能为空");
            }
            User username = this.baseMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, user.getUsername()));
            if (username != null) return ResultUtils.Use("已有此账号注册");
            User NetName = this.baseMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getNetName, user.getNetName()));
            if (NetName != null) return ResultUtils.Use("已有用户注册过此用户名");
            User email = this.baseMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getEmail, user.getEmail()));
            if (email != null) return ResultUtils.Use("此邮箱已被注册过");
            int save = this.baseMapper.insert(user);
            if (save > 0) {
                return ResultUtils.success("用户注册成功");
            }
            return ResultUtils.error("用户注册失败");
        }
        return ResultUtils.ver("验证码错误");
    }

    /**
     * 修改信息
     */
    @Override
    public ResultVo FindPwd(User user) {
        String uuid = template.opsForValue().get(user.getUuid());
        if (user.getVerCode() == null || user.getVerCode().equals(uuid)) {
            if(StringUtils.isEmpty(user.getEmail()) ||
                    StringUtils.isEmpty(user.getVerCode()) || StringUtils.isEmpty(user.getPassword())) {
                return ResultUtils.error("用户修改信息不能为空");
            }
            User email = this.baseMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getEmail, user.getEmail()));
            if (email != null) {
                Map<String, String> map = new HashMap<>();
                map.put("uid", Long.toString(user.getUid()));
                String token = jwtUtils.generateToken(map);

                UserLoginResult result = new UserLoginResult();
                result.setUid(user.getUid());
                result.setToken(token);

                int save = this.baseMapper.updateById(user);
                if (save > 0) {
                    return ResultUtils.success("密码修改成功", result);
                }
                return ResultUtils.error("修改失败");
            }
            return ResultUtils.error("此邮箱未被注册过，请先注册");
        }
        return ResultUtils.error("验证码错误");
    }



    /**
     * 分页查询用户信息
     */
    @Override
    public IPage<User> userPage(UserParm userParm) {
        Page<User> page = new Page<>();
        page.setSize(userParm.getPageSize());
        page.setCurrent(userParm.getCurrentPage());

        QueryWrapper<User> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(userParm.getUsername())) {
            query.lambda().like(User::getUsername, userParm.getUsername());
        }
        return this.baseMapper.selectPage(page, query);
    }

    /**
     * 用户登陆模块
     */
    @Override
    public ResultVo UserLogin(LoginVo loginVo) {
        String res = template.opsForValue().get(loginVo.getUuid());
        QueryWrapper<User> query = new QueryWrapper<>();
        query.lambda().eq(User::getUsername, loginVo.getUsername()).eq(User::getPassword, loginVo.getPassword());
        User user = super.getOne(query);
        if (user == null) {
            return ResultUtils.error("账号或密码错误");
        }
        if (loginVo.getVerCode() == null || loginVo.getVerCode().equals(res)) {
            if (StringUtils.isEmpty(loginVo.getUsername()) ||StringUtils.isEmpty(loginVo.getPassword()) ||
                    StringUtils.isEmpty(loginVo.getVerCode())) {
                return ResultUtils.error("用户登陆信息不能为空");
            }

            String state = String.valueOf(user.getState());
            //if (user != null) {
                if (state.equals("1")) {
                    return ResultUtils.error("账号已被封");
                }
                StpUtil.login(user.getUid());
                Map<String, String> map = new HashMap<>();
                map.put("uid", Long.toString(user.getUid()));
                map.put("username", user.getUsername());
                map.put("netName", user.getNetName());
                String token = jwtUtils.generateToken(map);
                UserLoginResult result = new UserLoginResult();
                result.setUid(user.getUid());
                result.setUsername(user.getUsername());
                result.setNetName(user.getNetName());
                result.setToken(token);
                return ResultUtils.success("用户登录成功", result);
            //}
            //return ResultUtils.error("账号或密码错误");
        }
        return ResultUtils.error("验证码错误");
    }

    /**
     * 通过邮箱查找账号信息
     */
    @Override
    public ResultVo getEmailUser(String email) {
        if (StringUtils.isEmpty(email)) {
            ResultUtils.error("请填写找回密码信息");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getEmail, email);
        User user = super.baseMapper.selectOne(wrapper);
        if (user != null) {
            return ResultUtils.success("发送成功", user);
        }
        return ResultUtils.error("发送失败，此邮箱未被注册请先注册");
    }

    /**
     * 重置密码
     */
    @Override
    public ResultVo editPassword(User user) {
        if (StringUtils.isEmpty(user.getPassword())) {
            return ResultUtils.error("重设密码不能为空");
        }
        boolean save = super.updateById(user);
        if (save) {
            return ResultUtils.success("修改成功");
        }
        return ResultUtils.error("修改失败");
    }

    @Override
    public ResultVo Modify(User user) {
        String uuid = template.opsForValue().get(user.getUuid());
        if (user.getVerCode() == null || user.getVerCode().equals(uuid)) {
            if(com.baomidou.mybatisplus.core.toolkit.StringUtils.isEmpty(user.getNetName()) || com.baomidou.mybatisplus.core.toolkit.StringUtils.isEmpty(user.getEmail()) || com.baomidou.mybatisplus.core.toolkit.StringUtils.isEmpty(user.getVerCode()) || com.baomidou.mybatisplus.core.toolkit.StringUtils.isEmpty(user.getPassword())) {
                return ResultUtils.error("用户修改信息不能为空");
            }
            User netName = super.getOne(new QueryWrapper<User>().lambda().eq(User::getNetName,user.getNetName()));
            if ( netName != null) {
                return ResultUtils.error("用户名已被注册");
            }
            User one = super.getOne(new QueryWrapper<User>().lambda().eq(User::getEmail, user.getEmail()));
            if (one == null) {
                ResultUtils.error("邮箱错误");
            }
            boolean save = super.updateById(user);
            if (save) {
                return ResultUtils.success("修改成功");
            }
            return ResultUtils.error("修改失败");
        }
        return ResultUtils.error("验证码错误");
    }


}




