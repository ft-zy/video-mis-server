package com.zy.web.video_management.video_SlideShow.service;

import com.zy.utils.ResultVo;
import com.zy.web.video_management.video_SlideShow.entity.Slideshow;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【slideshow】的数据库操作Service
* @createDate 2022-10-17 10:58:57
*/
public interface SlideshowService extends IService<Slideshow> {

    ResultVo BulkInsert(Slideshow slideshow);

    // 批量替换轮播图
    ResultVo Batch(Slideshow slideshow);

}
