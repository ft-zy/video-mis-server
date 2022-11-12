package com.zy.web.video_management.anime_name.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.web.video_management.anime_name.entity.AnimeName;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.web.video_management.anime_name.entity.AnimeNameParm;
import com.zy.web.video_management.anime_name.entity.AnimeNameVo;
import com.zy.web.video_management.tv_name.entity.TvSeriesName;
import com.zy.web.video_management.tv_name.entity.TvSeriesNameVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【anime_name】的数据库操作Service
* @createDate 2022-11-02 20:47:20
*/
public interface AnimeNameService extends IService<AnimeName> {

    List<AnimeName> selectAnimeNameInfoList(AnimeNameVo animeNameVo);

    List<AnimeName> selectAnimeNameByIdInfo(@Param("aid") Long aid);

    Long deleteAid(Long aid);

}
