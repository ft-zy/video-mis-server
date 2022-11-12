package com.zy.web.video_system.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.video_system.admin.entity.Admin;
import com.zy.web.video_system.admin.entity.vo.AdminParm;
import com.zy.web.video_system.admin.entity.vo.SelectAdminInfo;
import com.zy.web.video_system.admin.entity.vo.SelectOption;
import com.zy.web.video_system.admin.mapper.AdminMapper;
import com.zy.web.video_system.admin.service.AdminService;
import com.zy.web.video_system.admin_role.entity.AdminRole;
import com.zy.web.video_system.admin_role.service.AdminRoleService;
import com.zy.web.video_system.role.entity.Role;
import com.zy.web.video_system.role.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @author Administrator
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2022-10-11 18:53:09
*/
@Slf4j
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService {

    @Lazy
    @Resource
    private AdminRoleService adminRoleService;

    @Lazy
    @Resource
    private RoleService roleService;

    @Override
    public ResultVo addAdmin(Admin admin) {
        int save = this.baseMapper.insert(admin);
        if (save > 0) {
            AdminRole adminRole = new AdminRole();
            adminRole.setRoid(admin.getRoid());
            adminRole.setAid(admin.getAid());
            adminRoleService.save(adminRole);
        }
        return ResultUtils.success("新增用户和设置用户角色成功");
    }

    @Override
    @Transactional
    public ResultVo editAdmin(Admin admin) {
        int save = this.baseMapper.updateById(admin);
        if (save > 0) {
            QueryWrapper<AdminRole> query = new QueryWrapper<>();
            query.lambda().eq(AdminRole::getAid, admin.getAid());
            adminRoleService.remove(query);
            AdminRole adminRole = new AdminRole();
            adminRole.setRoid(admin.getRoid());
            adminRole.setAid(admin.getAid());
            adminRoleService.save(adminRole);
        }
        return ResultUtils.success("修改用户和修改用户角色成功");
    }


    @Override
    public ResultVo deleteAdmin(Long aid) {
        int save = this.baseMapper.deleteById(aid);
        if (save > 0) {
            QueryWrapper<AdminRole> query = new QueryWrapper<>();
            query.lambda().eq(AdminRole::getAid,aid);
            adminRoleService.remove(query);
        }
        return ResultUtils.success("删除用户和用户角色成功");
    }

    @Override
    public IPage<Admin> getList(AdminParm adminParm) {
        Page<Admin> page = new Page<>();
        page.setSize(adminParm.getPageSize());
        page.setCurrent(adminParm.getCurrentPage());

        QueryWrapper<Admin> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(adminParm.getAname())) {
            query.lambda().like(Admin::getAname,adminParm.getAname());
        }
        if (StringUtils.isNotEmpty(adminParm.getAccount())) {
            query.lambda().like(Admin::getAccount, adminParm.getAccount());
        }
        return this.baseMapper.selectPage(page, query);
    }

    @Override
    public List<Admin> selectAdminList(SelectAdminInfo query) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(query.getRoId() != null && query.getRoId().size() > 0, Admin::getRoid)
                .ge(StringUtils.isNotEmpty(query.getAname()), Admin::getAname,query.getAname())
                .le(StringUtils.isNotEmpty(query.getRoleName()), Admin::getRoleName,query.getRoleName())
                .le(StringUtils.isNotEmpty(query.getAphone()),Admin::getAphone, query.getAphone())
                .eq(StringUtils.isNotEmpty(query.getAemil()), Admin::getAemail, query.getAemil())
                .eq(StringUtils.isNotEmpty(query.getAccount()), Admin::getAccount, query.getAccount())
                .eq(StringUtils.isNotEmpty(query.getApassword()), Admin::getApassword, query.getApassword())
                .eq(StringUtils.isNotEmpty(query.getAsex()), Admin::getAsex, query.getAsex())
                .eq(StringUtils.isNotEmpty(query.getAportrait()), Admin::getAportrait, query.getAportrait());
        log.info(String.valueOf(queryWrapper));
        return this.baseMapper.selectAdminList(queryWrapper);
    }

    @Override
    public ResultVo getRole() {
        List<Role> list = roleService.list();
        List<SelectOption> selectOptions = new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .forEach(item-> {
                    SelectOption option = new SelectOption();
                    option.setValue(item.getRoId());
                    option.setLabel(item.getRoleName());
                    selectOptions.add(option);
                });
        return ResultUtils.success("查询成功", selectOptions);
    }

}




