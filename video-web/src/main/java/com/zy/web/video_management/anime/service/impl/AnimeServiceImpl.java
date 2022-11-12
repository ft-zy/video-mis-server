package com.zy.web.video_management.anime.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.entity.VideoType;
import com.zy.web.entity.vo.SelectOption;
import com.zy.web.video_management.anime.entity.Anime;
import com.zy.web.video_management.anime.entity.AnimeList;
import com.zy.web.video_management.anime.entity.AnimeParm;
import com.zy.web.video_management.anime.entity.AnimeVo;
import com.zy.web.video_management.anime.mapper.AnimeMapper;
import com.zy.web.video_management.anime.service.AnimeService;
import com.zy.web.video_management.anime_name.entity.AnimeName;
import com.zy.web.video_management.anime_name.service.AnimeNameService;
import com.zy.web.video_management.tv_name.entity.TvSeriesName;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @author Administrator
* @description 针对表【anime】的数据库操作Service实现
* @createDate 2022-11-02 20:26:57
*/
@Transactional
@Slf4j
@Service
public class AnimeServiceImpl extends ServiceImpl<AnimeMapper, Anime>
    implements AnimeService{

    @Resource
    private AnimeNameService animeNameService;

    @Override
    public List<Anime> selectAnimeList(Long aid) {
        return this.baseMapper.selectAnimeList(aid);
    }

    //@Override
    //public Page<Anime> getList(AnimeParm animeParm) {
    //    Page<Anime> page = new Page<>(animeParm.getPageSize(),animeParm.getCurrentPage());
    //    QueryWrapper<Anime> query = new QueryWrapper<>();
    //    if (StringUtils.isNotEmpty(animeParm.getAid())) {
    //        query.lambda().like(Anime::getAid, animeParm.getAid());
    //    }
    //    return this.baseMapper.selectPage(page, query);
    //}

    @Override
    public ResultVo getAnimeList() {
        // 查询
        List<AnimeName> list = animeNameService.list();
        // 组装需要的数据格式
        List<AnimeVo> selectOptions = new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .forEach(item-> {
                    AnimeVo option = new AnimeVo();
                    option.setValue(item.getAid());
                    option.setLabel(item.getName());
                    selectOptions.add(option);
                });
        return ResultUtils.success("查询成功", selectOptions);
    }

    @Override
    public List<Anime> selectAnimeInfoList(AnimeList query) {
        //条件构造器
        QueryWrapper<Anime> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(query.getAid() != null && query.getAid().size() > 0,Anime::getAid)
                .eq(StringUtils.isNotEmpty(query.getName()), Anime::getName, query.getName())
                .eq(StringUtils.isNotEmpty(query.getCount()), Anime::getCount, query.getCount())
                .eq(StringUtils.isNotEmpty(query.getVurl()), Anime::getVurl, query.getVurl());
        log.info(String.valueOf(queryWrapper));
        return this.baseMapper.selectAnimeInfoList(queryWrapper);
    }
}




