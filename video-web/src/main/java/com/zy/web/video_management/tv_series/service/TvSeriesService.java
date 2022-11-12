package com.zy.web.video_management.tv_series.service;

import com.zy.utils.ResultVo;
import com.zy.web.video_management.anime.entity.Anime;
import com.zy.web.video_management.anime.entity.AnimeList;
import com.zy.web.video_management.tv_series.entity.TvSeries;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.web.video_management.tv_series.entity.TvSeriesList;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tv_series】的数据库操作Service
* @createDate 2022-11-02 15:52:07
*/
public interface TvSeriesService extends IService<TvSeries> {

    List<TvSeries> selectTvList(Long tvid);

    List<TvSeries> selectTvInfoList(TvSeriesList tvSeriesList);

    ResultVo getTvList();
}
