package com.zy.frontdesk.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zy.frontdesk.entity.User;
import com.zy.frontdesk.service.UserService;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "用户注册模块")
@RestController
@RequestMapping("/api/registered/")
public class RegisteredController {

    @Resource
    private UserService userService;

    @Resource
    private StringRedisTemplate template;

    @ApiOperation("用户注册")
    @PostMapping("add_user")
    public ResultVo Registered(@RequestBody User user) {
        return userService.Registered(user);
    }

    @ApiOperation("用户注销")
    @DeleteMapping("{uid}")
    public ResultVo Logout(@PathVariable("uid") Long uid) {
        boolean save = userService.removeById(uid);
        if (save) {
            return ResultUtils.success("注销账号成功");
        }
        return ResultUtils.error("注销账号失败");
    }

    @ApiOperation("修改用户信息")
    @PostMapping("edit_user")
    public ResultVo Modify(@RequestBody User user) {
       return userService.Modify(user);
    }

    @ApiOperation("修改用户头像")
    @PostMapping("editT")
    public ResultVo edit(@RequestBody User user) {
        boolean save = userService.updateById(user);
        if (save) {
            return ResultUtils.success("修改成功");
        }
        return ResultUtils.error("修改失败");
    }

    @ApiOperation("根据用户id查询信息")
    @GetMapping("getByIdInfo")
    public ResultVo getUserIfo(Long uid) {
        User user = userService.getById(uid);
        if (user != null) {
            return ResultUtils.success("查询成功", user);
        }
        return ResultUtils.error("查询失败");
    }

}
