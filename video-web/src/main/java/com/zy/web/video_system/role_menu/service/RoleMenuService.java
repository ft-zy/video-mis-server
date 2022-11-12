package com.zy.web.video_system.role_menu.service;

import com.zy.web.video_system.role_menu.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.web.video_system.role_menu.entity.SaveAssign;

/**
* @author Administrator
* @description 针对表【role_menu】的数据库操作Service
* @createDate 2022-10-10 22:06:07
*/
public interface RoleMenuService extends IService<RoleMenu> {

    void assignSave(SaveAssign saveAssign);

}
