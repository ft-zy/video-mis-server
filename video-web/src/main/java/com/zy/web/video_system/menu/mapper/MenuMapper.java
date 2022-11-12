package com.zy.web.video_system.menu.mapper;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.web.video_system.menu.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【menu】的数据库操作Mapper
* @createDate 2022-10-03 16:00:07
* @Entity com.zy.web.video_system.menu.entity.Menu
*/
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    int insertAll(Menu menu);

    int updateSelective(Menu menu);
    int deleteByMid(@Param("mid") Long mid);

    // 根据用户 id 查询菜单
    List<Menu> getMenuByAid(@Param("aid") Long aid);

    // 根据角色 id 查询菜单
    List<Menu> getMenuByRoleId(@Param("roId") Long roId);


}




