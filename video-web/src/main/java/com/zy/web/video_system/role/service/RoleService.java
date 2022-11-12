package com.zy.web.video_system.role.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.utils.ResultVo;
import com.zy.web.video_system.role.entity.AssignParm;
import com.zy.web.video_system.role.entity.AssignVo;
import com.zy.web.video_system.role.entity.Role;
import com.zy.web.video_system.role.entity.RoleParm;

/**
* @author Administrator
* @description 针对表【role】的数据库操作Service
* @createDate 2022-09-18 22:16:32
*/
public interface RoleService extends IService<Role> {
    IPage<Role> getList(RoleParm roleParm);

    ResultVo addRole(Role role);

    ResultVo deleteRoleById(Long roId);

    ResultVo updateRole(Role role);

    AssignVo getAssignShow(AssignParm parm);
}
