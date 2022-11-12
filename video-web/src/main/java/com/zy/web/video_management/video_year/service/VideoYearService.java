package com.zy.web.video_management.video_year.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.utils.ResultVo;
import com.zy.web.entity.VideoYear;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.web.entity.vo.YearParm;


/**
* @author Administrator
* @description 针对表【video_year】的数据库操作Service
* @createDate 2022-08-18 21:13:00
*/
public interface VideoYearService extends IService<VideoYear> {
    IPage<VideoYear> getList(YearParm yearParm);

    ResultVo add(VideoYear videoYear);

    ResultVo delete(Long yid);

    ResultVo update(VideoYear videoYear);
}
