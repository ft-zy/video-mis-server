package com.zy.web.video_management.video_type.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.web.entity.VideoType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【video_type】的数据库操作Mapper
* @createDate 2022-08-06 15:41:25
* @Entity com.zy.web.server.video_management.video_type.entity.VideoType
*/
@Mapper
public interface VideoTypeMapper extends BaseMapper<VideoType> {

    int insertAll(VideoType videoType);

    int deleteById(@Param("id") Long id);

    int updateSelective(VideoType videoType);
}




