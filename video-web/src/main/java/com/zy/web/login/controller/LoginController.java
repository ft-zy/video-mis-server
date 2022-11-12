package com.zy.web.login.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zy.jwt.JwtUtils;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.login.entity.LoginParm;
import com.zy.web.login.entity.LoginResult;
import com.zy.web.login.entity.MenuResult;
import com.zy.web.login.entity.RouterVO;
import com.zy.web.video_system.admin.entity.Admin;
import com.zy.web.video_system.admin.service.AdminService;
import com.zy.web.video_system.menu.entity.MakeTree;
import com.zy.web.video_system.menu.entity.Menu;
import com.zy.web.video_system.menu.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = "管理员登录模块")
@Slf4j
@RestController
@RequestMapping("/api/login/")
public class LoginController {
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private AdminService adminService;
    @Resource
    private StringRedisTemplate template;
    @Resource
    private MenuService menuService;

    @ApiOperation(value = "管理员登录")
   @PostMapping( "doLogin")
    public ResultVo login(@RequestBody LoginParm loginParm) {
        String uuid1 = template.opsForValue().get(loginParm.getUuid());
        if (loginParm.getVerCode().equals(uuid1) || loginParm.getVerCode() == null) {
            if (StringUtils.isEmpty(loginParm.getAccount()) || StringUtils.isEmpty(loginParm.getApassword())
                    || StringUtils.isEmpty(loginParm.getVerCode())) {
                return ResultUtils.error("用户名密码或验证码不能为空");
            }
            // 查询用户
            QueryWrapper<Admin> query = new QueryWrapper<>();
            query.lambda().eq(Admin::getAccount, loginParm.getAccount())
                    .eq(Admin::getApassword, loginParm.getApassword());
            //DigestUtils.md5DigestAsHex(loginParm.getApassword().getBytes())
            Admin admin = adminService.getOne(query);

            if ( admin != null ) {
                // 生成 Token
                Map<String, String> map = new HashMap<>();
                map.put("aportrait", admin.getAportrait());
                map.put("account", admin.getAccount());
                map.put("aid", Long.toString(admin.getAid()));
                map.put("roId", Long.toString(admin.getRoid()));
                String token = jwtUtils.generateToken(map);

                // 构造返回值
                LoginResult result = new LoginResult();
                result.setAid(admin.getAid());
                result.setAportrait(admin.getAportrait());
                result.setRoId(admin.getRoid());
                result.setToken(token);
                result.setAccount(admin.getAccount());

                log.info("admin{}", admin +" 在 " + new Date() + " 登录");

                return ResultUtils.success("登录成功", result);
            }
            return ResultUtils.e("用户名或者密码错误！", 666);
        }
        return ResultUtils.e("验证码错误");
    }

    @GetMapping("/getMenuList")
    public ResultVo getMenuList(Long aid) {
        List<Menu> menuList = menuList = menuService.getMenuByAid(aid);

        if (menuList == null || menuList.size() < 1) {
            return ResultUtils.error("您暂无菜单权限，请联系管理员!");
        }
        List<Menu> collect = menuList.stream().filter(item -> item != null && !item.getType().equals("2"))
                .collect(Collectors.toList());
        //组装路由数据
        List<RouterVO> router = MakeTree.makeRouter(collect, 0L);
        //权限字段
        Object[] array = menuList.stream().filter(item -> item.getCode() != null).map(item -> item.getCode()).toArray();
        //返回
        MenuResult result = new MenuResult();
        result.setAuthList(array);
        result.setMenuList(router);
        return ResultUtils.success("查询成功", result);
    }

}
