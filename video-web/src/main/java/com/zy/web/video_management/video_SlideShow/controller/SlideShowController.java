package com.zy.web.video_management.video_SlideShow.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.video_management.video_SlideShow.entity.Slideshow;
import com.zy.web.video_management.video_SlideShow.service.SlideshowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api("轮播图模块")
@RestController
@RequestMapping("/api/swiper/")
public class SlideShowController {

    @Resource
    private SlideshowService slideshowService;


    @ApiModelProperty(value = "查询轮播图")
    @GetMapping("findSwiper")
    public ResultVo findSwiper() {
        QueryWrapper<Slideshow> query = new QueryWrapper<>();
        query.eq("isSwiper", true).orderByAsc("swiperSort");
        List<Slideshow> list = slideshowService.list(query);
        return ResultUtils.success("ok", list);
    }




}
