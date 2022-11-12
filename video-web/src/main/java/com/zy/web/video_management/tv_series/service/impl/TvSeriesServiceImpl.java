package com.zy.web.video_management.tv_series.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.errorprone.annotations.Var;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.video_management.anime.entity.Anime;
import com.zy.web.video_management.anime.entity.AnimeVo;
import com.zy.web.video_management.anime_name.entity.AnimeName;
import com.zy.web.video_management.tv_name.entity.TvSeriesName;
import com.zy.web.video_management.tv_name.service.TvSeriesNameService;
import com.zy.web.video_management.tv_series.entity.TvSeries;
import com.zy.web.video_management.tv_series.entity.TvSeriesList;
import com.zy.web.video_management.tv_series.entity.TvSeriesVo;
import com.zy.web.video_management.tv_series.service.TvSeriesService;
import com.zy.web.video_management.tv_series.mapper.TvSeriesMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @author Administrator
* @description 针对表【tv_series】的数据库操作Service实现
* @createDate 2022-11-02 15:52:07
*/
@Slf4j
@Service
public class TvSeriesServiceImpl extends ServiceImpl<TvSeriesMapper, TvSeries>
    implements TvSeriesService{

    @Resource
    private TvSeriesNameService tvSeriesNameService;

    @Override
    public List<TvSeries> selectTvList(Long tvid) {
        return this.baseMapper.selectTvList(tvid);
    }

    @Override
    public List<TvSeries> selectTvInfoList(TvSeriesList query) {
        //条件构造器
        QueryWrapper<TvSeries> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(query.getTvid() != null && query.getTvid().size() > 0,TvSeries::getTvid)
                .eq(StringUtils.isNotEmpty(query.getName()), TvSeries::getName, query.getName())
                .eq(StringUtils.isNotEmpty(query.getCount()), TvSeries::getCount, query.getCount())
                .eq(StringUtils.isNotEmpty(query.getVurl()), TvSeries::getVurl, query.getVurl());
        log.info(String.valueOf(queryWrapper));
        return this.baseMapper.selectTvInfoList(queryWrapper);
    }

    @Override
    public ResultVo getTvList() {
        // 查询
        List<TvSeriesName> list = tvSeriesNameService.list();
        // 组装需要的数据格式
        List<TvSeriesVo> selectOptions = new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .forEach(item-> {
                    TvSeriesVo option = new TvSeriesVo();
                    option.setValue(item.getTvid());
                    option.setLabel(item.getName());
                    selectOptions.add(option);
                });
        return ResultUtils.success("查询成功", selectOptions);
    }
}




