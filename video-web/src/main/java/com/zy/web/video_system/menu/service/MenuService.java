package com.zy.web.video_system.menu.service;

import com.zy.utils.ResultVo;
import com.zy.web.video_system.menu.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【menu】的数据库操作Service
* @createDate 2022-10-03 16:00:07
*/
public interface MenuService extends IService<Menu> {

    ResultVo addMenu(Menu menu);
    ResultVo editMenu(Menu menu);
    ResultVo deleteByMid(Long mid);
    //IPage<Menu> getList(AdminParm adminParm);
    List<Menu> getList();
    List<Menu> parentList();

    // 根据用户 id 查询菜单
    List<Menu> getMenuByAid(Long aid);

    // 根据角色 id 查询菜单
    List<Menu> getMenuByRoleId(Long roId);

}
