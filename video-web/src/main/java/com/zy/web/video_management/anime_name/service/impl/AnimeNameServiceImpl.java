package com.zy.web.video_management.anime_name.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.web.entity.Video;
import com.zy.web.entity.VideoType;
import com.zy.web.video_management.anime_name.entity.AnimeName;
import com.zy.web.video_management.anime_name.entity.AnimeNameParm;
import com.zy.web.video_management.anime_name.entity.AnimeNameVo;
import com.zy.web.video_management.anime_name.service.AnimeNameService;
import com.zy.web.video_management.anime_name.mapper.AnimeNameMapper;
import com.zy.web.video_management.tv_name.entity.TvSeriesName;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author Administrator
* @description 针对表【anime_name】的数据库操作Service实现
* @createDate 2022-11-02 20:47:20
*/

@Slf4j
@Service
public class AnimeNameServiceImpl extends ServiceImpl<AnimeNameMapper, AnimeName>
    implements AnimeNameService{

    @Transactional
    @Override
    public List<AnimeName> selectAnimeNameInfoList(AnimeNameVo query) {
        //条件构造器
        QueryWrapper<TvSeriesName> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(query.getId() != null && query.getId().size() > 0,TvSeriesName::getId)
                .in(query.getRid() != null && query.getRid().size() > 0,TvSeriesName::getRid)
                .in(query.getYid() != null && query.getYid().size() > 0,TvSeriesName::getYid)
                .ge(StringUtils.isNotEmpty(query.getVideoYear()), TvSeriesName::getVideoYear, query.getVideoYear())
                .le(StringUtils.isNotEmpty(query.getRegion()), TvSeriesName::getRegion, query.getRegion())
                .eq(StringUtils.isNotEmpty(query.getTypeName()), TvSeriesName::getTypeName, query.getTypeName())
                .eq(StringUtils.isNotEmpty(query.getName()), TvSeriesName::getName, query.getName())
                .eq(StringUtils.isNotEmpty(query.getCover()), TvSeriesName::getCover, query.getCover())
                .eq(StringUtils.isNotEmpty(query.getInfo()), TvSeriesName::getInfo, query.getInfo());
        log.info(String.valueOf(queryWrapper));
        return this.baseMapper.selectAnimeNameInfoList(queryWrapper);
    }

    @Override
    public List<AnimeName> selectAnimeNameByIdInfo(Long aid) {
        return this.baseMapper.selectAnimeNameByIdInfo(aid);
    }

    @Override
    public Long deleteAid(Long aid) {
        return this.baseMapper.deleteAid(aid);
    }

    //@Override
    //public IPage<AnimeName> getAnimeNameList(AnimeNameParm animeNameParm) {
    //    IPage<AnimeName> page = new Page<>();
    //    page.setSize(animeNameParm.getPageSize());
    //    page.setCurrent(animeNameParm.getCurrentPage());
    //
    //    QueryWrapper<AnimeName> query = new QueryWrapper<>();
    //    if (StringUtils.isNotEmpty(animeNameParm.getName())) {
    //        query.lambda().like(AnimeName::getName, animeNameParm.getName());
    //    }
    //    return this.baseMapper.selectPage(page, query);
    //}
}




