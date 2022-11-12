package com.zy.web.video_system.role.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.video_system.role.entity.AssignParm;
import com.zy.web.video_system.role.entity.AssignVo;
import com.zy.web.video_system.role.entity.Role;
import com.zy.web.video_system.role.entity.RoleParm;
import com.zy.web.video_system.role.service.RoleService;
import com.zy.web.video_system.role_menu.entity.SaveAssign;
import com.zy.web.video_system.role_menu.service.RoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/api/role/")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private RoleMenuService roleMenuService;

    @ApiOperation("添加角色")
    @PostMapping("addRole")
    public ResultVo addRole(@RequestBody Role role) {
       return roleService.addRole(role);
    }

    @ApiOperation("更具id来删除信息")
    @DeleteMapping("{roId}")
    public ResultVo deleteRole(@PathVariable("roId") Long roId) {
       return roleService.deleteRoleById(roId);
    }

    @ApiOperation("修改角色信息")
    @PutMapping("editRole")
    public ResultVo editRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    @ApiOperation("分页查询，角色信息")
    @GetMapping("getRole")
    public ResultVo getRole (RoleParm roleParm) {
        IPage<Role> list = roleService.getList(roleParm);
        return ResultUtils.success("查询成功", list);
    }

    @ApiOperation("查询分配权限的回显")
    @GetMapping("/getAssingShow")
    public ResultVo getAssingShow(AssignParm assignParm) {
        AssignVo vo = roleService.getAssignShow(assignParm);
        return ResultUtils.success("查询成功", vo);
    }

    @ApiOperation("分配权限的保存")
    @PostMapping("assignSave")
    public ResultVo assignSave(@RequestBody SaveAssign saveAssign) {
        roleMenuService.assignSave(saveAssign);
        return ResultUtils.success("分配权限成功");
    }


}
