package com.zy.web.video_management.video_SlideShow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.video_management.video_SlideShow.entity.Slideshow;
import com.zy.web.video_management.video_SlideShow.service.SlideshowService;
import com.zy.web.video_management.video_SlideShow.mapper.SlideshowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author Administrator
* @description 针对表【slideshow】的数据库操作Service实现
* @createDate 2022-10-17 10:58:57
*/
@Service
public class SlideshowServiceImpl extends ServiceImpl<SlideshowMapper, Slideshow>
    implements SlideshowService{

    @Override
    public ResultVo BulkInsert(Slideshow slideshow) {
        return null;
    }

    @Override
    public ResultVo Batch(Slideshow slideshow) {
        return null;
    }
}




