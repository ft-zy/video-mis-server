package com.zy.web.video_management.tv_series.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zy.web.video_management.anime.entity.Anime;
import com.zy.web.video_management.tv_name.entity.TvSeriesName;
import com.zy.web.video_management.tv_series.entity.TvSeries;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tv_series】的数据库操作Mapper
* @createDate 2022-11-02 15:52:07
* @Entity com.zy.web.video_management.tv_series.entity.TvSeries
*/
@Mapper
public interface TvSeriesMapper extends BaseMapper<TvSeries> {

    List<TvSeries> selectTvList(@Param("tvid") Long tvid);

    List<TvSeries> selectTvInfoList(@Param(Constants.WRAPPER) Wrapper<?> queryWrapper);

}




