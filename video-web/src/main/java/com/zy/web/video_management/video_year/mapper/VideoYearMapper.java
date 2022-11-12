package com.zy.web.video_management.video_year.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.web.entity.VideoYear;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【video_year】的数据库操作Mapper
* @createDate 2022-08-18 21:13:00
* @Entity com.zy.web.server.video_management.video_year.entity.VideoYear
*/
@Mapper
public interface VideoYearMapper extends BaseMapper<VideoYear> {

    int insertAll(VideoYear videoYear);

    int deleteByYid(@Param("yid") Long yid);

    int updateSelective(VideoYear videoYear);

}




