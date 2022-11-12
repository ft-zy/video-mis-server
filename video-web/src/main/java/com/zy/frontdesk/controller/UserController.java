package com.zy.frontdesk.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.frontdesk.entity.User;
import com.zy.frontdesk.entity.vo.UserParm;
import com.zy.frontdesk.service.UserService;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "用户列表")
@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("分页查询")
    @GetMapping("UserPage")
    public ResultVo UserPage(UserParm userParm) {
        IPage<User> list = userService.userPage(userParm);
        return ResultUtils.success("查询成功", list);
    }


    @ApiOperation("删除用户")
    @GetMapping("{uid}")
    public ResultVo deleteUser(@PathVariable("uid") Long uid) {
        boolean save = userService.removeById(uid);
        if (save) {
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }

    @ApiOperation("修改用户")
    @PutMapping("editUser")
    public ResultVo editUser(@RequestBody User user) {
        boolean save = userService.updateById(user);
        if (save) {
            return ResultUtils.success("设置成功");
        }
        return ResultUtils.error("设置失败");
    }

    @ApiOperation("添加用户")
    @PostMapping("addUser")
    public ResultVo addUser(@RequestBody User user) {
        boolean save = userService.updateById(user);
        if (save) {
            return ResultUtils.success("添加成功");
        }
        return ResultUtils.error("添加失败");
    }

    //



    //@ApiOperation(value = "根据Id查询信息")
    //@RequestMapping(value = "getByIdUser", method = RequestMethod.GET)
    //public ResultVo getByIdUser(Long uid) {
    //    User user = userService.getById(uid);
    //    return ResultUtils.success("查询成功", user);
    //}

}
