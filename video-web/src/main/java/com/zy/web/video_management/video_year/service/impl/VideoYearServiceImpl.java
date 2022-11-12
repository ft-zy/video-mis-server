package com.zy.web.video_management.video_year.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.entity.VideoYear;
import com.zy.web.entity.vo.YearParm;
import com.zy.web.video_management.video_year.service.VideoYearService;
import com.zy.web.video_management.video_year.mapper.VideoYearMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author Administrator
* @description 针对表【video_year】的数据库操作Service实现
* @createDate 2022-08-18 21:13:00
*/
@Slf4j
@Service
public class VideoYearServiceImpl extends ServiceImpl<VideoYearMapper, VideoYear>
    implements VideoYearService {

    @Resource
    private VideoYearMapper videoYearMapper;

    @Resource
    @Lazy
    private  VideoYearService videoYearService;

    @Override
    @Lazy
    public IPage<VideoYear> getList(YearParm yearParm) {
        // 构造分页对象
        IPage<VideoYear> page = new Page<>();
        page.setSize(yearParm.getPageSize());
        page.setCurrent(yearParm.getCurrentPage());
        // 构造查询条件
        QueryWrapper<VideoYear> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(yearParm.getVideoYear())) {
            query.lambda().like(VideoYear::getVideoYear,yearParm.getVideoYear());
        }
        return this.baseMapper.selectPage(page, query);
    }



    @Override
    public ResultVo add(VideoYear videoYear) {
        if (query(videoYear)) return ResultUtils.e("已有该年分信息!");
        videoYearMapper.insertAll(videoYear);
        log.info("添加年份信息" + videoYear);
        return ResultUtils.success("新增成功!");
    }

    @Override
    public ResultVo delete(Long yid) {
        int save = videoYearMapper.deleteByYid(yid);
        if (save > 0) {
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败");
    }

    @Override
    public ResultVo update(VideoYear videoYear) {
        if (query(videoYear)) return ResultUtils.e("已有该年分信息!");
        videoYearMapper.updateSelective(videoYear);
        return ResultUtils.success("修改成功!");
    }

    private boolean query(VideoYear videoYear) {
        QueryWrapper<VideoYear> query = new QueryWrapper<>();
        query.lambda().eq(VideoYear::getVideoYear,videoYear.getVideoYear());
        VideoYear one = videoYearService.getOne(query);
        if(one != null){
            log.info("重复");
            return true;
        }
        return false;
    }
}




