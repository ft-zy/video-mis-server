package com.zy.web.video_management.anime.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.utils.ResultVo;
import com.zy.web.video_management.anime.entity.Anime;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.web.video_management.anime.entity.AnimeList;
import com.zy.web.video_management.anime.entity.AnimeParm;
import com.zy.web.video_management.tv_series.entity.TvSeries;

import java.util.List;

/**
* @author Administrator
* @description 针对表【anime】的数据库操作Service
* @createDate 2022-11-02 20:26:57
*/
public interface AnimeService extends IService<Anime> {

    List<Anime> selectAnimeList(Long aid);

    //Page<Anime> getList(AnimeParm animeParm);

    ResultVo getAnimeList();

    List<Anime> selectAnimeInfoList(AnimeList animeList);

}
