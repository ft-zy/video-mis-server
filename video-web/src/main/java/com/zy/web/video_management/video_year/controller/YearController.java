package com.zy.web.video_management.video_year.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.entity.VideoYear;
import com.zy.web.entity.vo.YearParm;
import com.zy.web.video_management.video_year.service.VideoYearService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "视频年份信息")
@RestController
@RequestMapping("/api/year/")
public class YearController {

    @Resource
    private VideoYearService videoYearService;

    @ApiOperation("新增视频年份信息")
    @PostMapping("add_year")
    public ResultVo add_region(@RequestBody VideoYear videoYear){
        return videoYearService.add(videoYear);
    }

    @ApiOperation("修改视频年份信息")
    @PutMapping("edit_year")
    public ResultVo edit_region(@RequestBody VideoYear videoYear) {
       return videoYearService.update(videoYear);
    }

    @ApiOperation("根据年份 yid 删除年份信息")
    @DeleteMapping("{yid}")
    public ResultVo delete_year(@PathVariable("yid") Long yid) {
        return videoYearService.delete(yid);
    }

    @ApiOperation("查询年份信息，分页")
    @GetMapping("list_year")
    public ResultVo list_region(YearParm yearParm) {
        IPage<VideoYear> list = videoYearService.getList(yearParm);
        return ResultUtils.success("查询成功", list);
    }
}
