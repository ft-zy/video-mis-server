package com.zy.frontdesk.mapper;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zy.web.entity.Video;
import org.apache.ibatis.annotations.Param;

import com.zy.frontdesk.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-10-25 14:23:58
* @Entity com.zy.frontdesk.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> selectByEmail(@Param("email") String email);

    List<User> getEmailInfo(@Param(Constants.WRAPPER) Wrapper<?> queryWrapper);

    //@Update("UPDATE `user` set uportrait = #{uportrait} where u_id = #{u_id}")
    //int updateUser(@Param("uid") Long uid);

}




