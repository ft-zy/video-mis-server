package com.zy.web.video_management.anime_name.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zy.web.video_management.anime_name.entity.AnimeName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.web.video_management.anime_name.entity.AnimeNameVo;
import com.zy.web.video_management.tv_name.entity.TvSeriesName;
import com.zy.web.video_management.tv_name.entity.TvSeriesNameVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【anime_name】的数据库操作Mapper
* @createDate 2022-11-02 20:47:20
* @Entity com.zy.web.video_management.anime_name.entity.AnimeName
*/
@Mapper
public interface AnimeNameMapper extends BaseMapper<AnimeName> {

    List<AnimeName> selectAnimeNameInfoList(@Param(Constants.WRAPPER) Wrapper<?> queryWrapper);

    List<AnimeName> selectAnimeNameByIdInfo(@Param("aid") Long aid);

    @Delete("delete from anime where aid = #{aid}")
    Long deleteAid(@Param("aid") Long aid);

}




