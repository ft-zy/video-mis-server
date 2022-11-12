package com.zy.web.video_management.anime.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zy.web.video_management.anime.entity.Anime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.web.video_management.anime_name.entity.AnimeName;
import com.zy.web.video_management.tv_series.entity.TvSeries;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【anime】的数据库操作Mapper
* @createDate 2022-11-02 20:26:57
* @Entity com.zy.web.video_management.anime.entity.Anime
*/
@Mapper
public interface AnimeMapper extends BaseMapper<Anime> {
    List<Anime> selectAnimeList(@Param("aid") Long aid);

    List<Anime> selectAnimeInfoList(@Param(Constants.WRAPPER) Wrapper<?> queryWrapper);
}




