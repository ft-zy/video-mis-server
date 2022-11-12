package com.zy.web.video_management.tv_name.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.video_management.tv_name.entity.TvSeriesName;
import com.zy.web.video_management.tv_name.entity.TvSeriesNameVo;
import com.zy.web.video_management.tv_name.service.TvSeriesNameService;
import com.zy.web.video_management.tv_name.mapper.TvSeriesNameMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tv_series_name】的数据库操作Service实现
* @createDate 2022-11-02 15:53:07
*/

@Slf4j
@Service
public class TvSeriesNameServiceImpl extends ServiceImpl<TvSeriesNameMapper, TvSeriesName>
    implements TvSeriesNameService{

    @Override
    public List<TvSeriesName> selectTvSeriesNameInfoList(TvSeriesNameVo query) {
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
        return this.baseMapper.selectTvInfoList(queryWrapper);
    }

    @Override
    public ResultVo AddTvName(TvSeriesName tvSeriesName) {
        QueryWrapper<TvSeriesName> query = new QueryWrapper<>();
        query.lambda().eq(TvSeriesName::getName, tvSeriesName.getName());
        TvSeriesName one = super.getOne(query);
        if (one != null) {
            return ResultUtils.error("已有" + tvSeriesName.getName() + "电视剧");
        }
        boolean save = super.save(tvSeriesName);
        if (save) {
            return ResultUtils.success("添加成功");
        }
        return ResultUtils.error("添加失败");
    }

    @Override
    public ResultVo EditTvName(TvSeriesName tvSeriesName) {
        QueryWrapper<TvSeriesName> query = new QueryWrapper<>();
        query.lambda().eq(TvSeriesName::getName, tvSeriesName.getName());
        TvSeriesName one = super.getOne(query);
        boolean save = super.updateById(tvSeriesName);
        if (save) {
            return ResultUtils.success("修改成功");
        }
        return ResultUtils.error("修改失败");
    }

    @Override
    public ResultVo DeleteById(Long tvid) {
        boolean save = super.removeById(tvid);
        if (save) {
            this.baseMapper.deleteTvid(tvid);
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }

    @Override
    public List<TvSeriesName> selectByIdInfo(Long tvid) {
        return this.baseMapper.selectByIdInfo(tvid);
    }

}




