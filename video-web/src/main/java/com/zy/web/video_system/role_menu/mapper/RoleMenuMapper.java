package com.zy.web.video_system.role_menu.mapper;

import com.zy.web.video_system.role_menu.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【role_menu】的数据库操作Mapper
* @createDate 2022-10-10 22:06:07
* @Entity com.zy.web.video_system.role_menu.entity.RoleMenu
*/
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    void assignSave(@Param("roId") Long roId, @Param("menuList")List<Long> menuList);

}




