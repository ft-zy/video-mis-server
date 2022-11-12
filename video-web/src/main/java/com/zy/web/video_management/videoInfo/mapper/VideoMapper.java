package com.zy.web.video_management.videoInfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zy.web.entity.Video;
import com.zy.web.entity.vo.ListParm;
import com.zy.web.video_management.tv_name.entity.TvSeriesName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import java.util.List;

/**
* @author Administrator
* @description 针对表【video】的数据库操作Mapper
* @createDate 2022-08-23 16:58:31
* @Entity com.zy.web.server.video_management.videoInfo.entity.Video
*/
@Mapper
public interface VideoMapper extends BaseMapper<Video> {

    int insertAll(Video video);

    int deleteById(@Param("id") Integer id);

    int updateSelective(Video video);

    List<Video> selectVideoInfoList(@Param(Constants.WRAPPER) Wrapper<?> queryWrapper);

    List<Video> selectVideoByIdInfo(@Param("vid") Long vid);
}




