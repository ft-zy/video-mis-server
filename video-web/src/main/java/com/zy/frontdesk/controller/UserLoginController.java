package com.zy.frontdesk.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.frontdesk.entity.vo.LoginVo;
import com.zy.frontdesk.entity.User;
import com.zy.frontdesk.entity.vo.UserLoginResult;
import com.zy.frontdesk.service.UserService;
import com.zy.jwt.JwtUtils;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Synchronized;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户登录模块")
@RestController
@RequestMapping("/api/user/")
public class UserLoginController {

    @Resource
    private StringRedisTemplate template;

    @Resource
    private UserService userService;

    @Resource
    private JwtUtils jwtUtils;

    @Transactional
    @ApiOperation("用户登录")
    @PostMapping("UserLogin")
    public ResultVo UserLogin(@RequestBody LoginVo loginVo) {
      return userService.UserLogin(loginVo);
    }

    @ApiOperation("根据用户邮箱查询用户信息")
    @PostMapping("getEmailUserInfo")
    public ResultVo getEmailUserInfo(String email) {
        return userService.getEmailUser(email);
    }

    @ApiOperation("修改密码")
    @PostMapping("edit_password")
    public ResultVo editPassword(@RequestBody User user) {
       return userService.editPassword(user);
    }

    @ApiOperation("用户忘记密码通过邮箱查询来修改密码")
    @PostMapping("findPwd")
    public ResultVo FindPwd(User user) {
        return userService.FindPwd(user);
    }


}
