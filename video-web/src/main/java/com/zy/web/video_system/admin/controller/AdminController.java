package com.zy.web.video_system.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.video_system.admin.entity.Admin;
import com.zy.web.video_system.admin.entity.vo.SelectAdminInfo;
import com.zy.web.video_system.admin.service.AdminService;
import com.zy.web.video_system.admin_role.entity.AdminRole;
import com.zy.web.video_system.admin_role.service.AdminRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Api(tags = "管理员模块")
@RestController
@RequestMapping("/api/admin/")
public class AdminController {
    @Resource
    private AdminService adminService;

    @Resource
    private AdminRoleService adminRoleService;

    private String pwd;

    @ApiOperation(value = "添加管理员用户")
    @RequestMapping(value = "addAdmin", method = RequestMethod.POST)
    public ResultVo addAdmin(@RequestBody Admin admin) {
        QueryWrapper<Admin> query = new QueryWrapper<>();
        query.lambda().eq(Admin::getAname, admin.getAname());
        Admin one = adminService.getOne(query);
        if (one != null) {
            return ResultUtils.error("该用户名已被占用");
        }
        return adminService.addAdmin(admin);
    }

    @ApiOperation(value = "根据用户Id删除管理员信息")
    @RequestMapping(value = "{aid}", method = RequestMethod.DELETE)
    public ResultVo deleteAdmin(@PathVariable("aid") Long aid) {
        return adminService.deleteAdmin(aid);
    }

    @ApiOperation(value = "修改管理员信息")
    @RequestMapping(value = "editAdmin", method = RequestMethod.PUT)
    public ResultVo editAdmin(@RequestBody Admin admin) {
        QueryWrapper<Admin> query = new QueryWrapper<>();
        query.lambda().eq(Admin::getAname, admin.getAname());
        Admin one = adminService.getOne(query);
        if (one != null && !one.getAid().equals(admin.getAid())) {
            return ResultUtils.error("该用户名已被占用");
        }
        return adminService.editAdmin(admin);
    }

    @ApiOperation(value = "分页查询管理员信息")
    @RequestMapping(value = "getAdminList", method = RequestMethod.GET)
    public ResultVo getAdminList(SelectAdminInfo select) {
        List<Admin> list = adminService.selectAdminList(select);
        return ResultUtils.success("查询成功", list);
    }

    @ApiOperation(value = "角色列表查询")
    @RequestMapping(value = "getRoleSelect", method = RequestMethod.GET)
    public ResultVo getRoleSelect() {
        return adminService.getRole();
    }

    @ApiOperation(value = "根据Id查询管理员信息")
    @RequestMapping(value = "getByIdAdmin", method = RequestMethod.GET)
    public ResultVo getByIdAmin(Long aid) {
        Admin admin = adminService.getById(aid);
        QueryWrapper<AdminRole> query = new QueryWrapper<>();
        query.lambda().eq(AdminRole::getAid,aid);
        AdminRole one = adminRoleService.getOne(query);
        admin.setRoid(one.getRoid());
        return ResultUtils.success("查询成功", admin);
    }



}
