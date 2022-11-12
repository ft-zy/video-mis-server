package com.zy.web.video_system.role.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.web.video_system.role.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【role】的数据库操作Mapper
* @createDate 2022-09-18 22:16:32
* @Entity com.zy.web.video_system.role.entity.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    int insertAll(Role role);

    int updateSelective(Role role);

    int delByRoid(@Param("roId") Long roId);

}




