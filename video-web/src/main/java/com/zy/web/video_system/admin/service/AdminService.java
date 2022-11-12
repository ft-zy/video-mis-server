package com.zy.web.video_system.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.utils.ResultVo;
import com.zy.web.video_system.admin.entity.vo.AdminParm;
import com.zy.web.video_system.admin.entity.vo.SelectAdminInfo;
import com.zy.web.video_system.admin.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【admin】的数据库操作Service
* @createDate 2022-10-11 18:53:09
*/
public interface AdminService extends IService<Admin> {

    ResultVo addAdmin(Admin admin);

    ResultVo editAdmin(Admin admin);

    ResultVo deleteAdmin(Long aid);

    IPage<Admin> getList(AdminParm adminParm);

    List<Admin> selectAdminList(SelectAdminInfo query);

    ResultVo getRole();
}
