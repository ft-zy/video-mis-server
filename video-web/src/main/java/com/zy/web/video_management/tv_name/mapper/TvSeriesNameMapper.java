package com.zy.web.video_management.tv_name.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zy.web.entity.Video;
import com.zy.web.video_management.tv_name.entity.TvSeriesName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tv_series_name】的数据库操作Mapper
* @createDate 2022-11-02 15:53:07
* @Entity com.zy.web.video_management.tv_name.entity.TvSeriesName
*/
@Mapper
public interface TvSeriesNameMapper extends BaseMapper<TvSeriesName> {
    List<TvSeriesName> selectTvInfoList(@Param(Constants.WRAPPER) Wrapper<?> queryWrapper);

    List<TvSeriesName> selectByIdInfo(@Param("tvid") Long tvid);

    @Delete("delete from tv_series where tvid = #{tvid}")
    Long deleteTvid(@Param("tvid") Long tvid);
}




