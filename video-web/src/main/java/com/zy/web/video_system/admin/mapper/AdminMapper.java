package com.zy.web.video_system.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zy.web.video_system.admin.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【admin】的数据库操作Mapper
* @createDate 2022-10-11 18:53:09
* @Entity com.zy.web.video_system.admin.entity.Admin
*/
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    List<Admin> selectAdminList(@Param(Constants.WRAPPER) Wrapper<?> queryWrapper);

}




