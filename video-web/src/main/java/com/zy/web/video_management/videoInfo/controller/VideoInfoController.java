package com.zy.web.video_management.videoInfo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.frontdesk.service.UserService;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.entity.Video;
import com.zy.web.entity.vo.ListParm;
import com.zy.web.entity.vo.VideoParm;
import com.zy.web.video_management.videoInfo.service.VideoService;
import com.zy.web.video_system.admin.entity.Admin;
import com.zy.web.video_system.admin.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Api(tags = "视频信息")
@RestController
@RequestMapping("/api/video/")
public class VideoInfoController {
    @Resource
    private VideoService videoService;

    @Resource
    private AdminService adminService;

    @ApiOperation(value = "新增视频信息")
    @PostMapping("add_video")
    public ResultVo add_video(@RequestBody Video video) {
        return videoService.addInfo(video);
    }

    @ApiOperation(value = "修改视频信息")
    @PutMapping("edit_video")
    public ResultVo edit_video(@RequestBody Video video) {
        return videoService.updateInfo(video);
    }

    @ApiOperation(value = "根据 vid 删除视频信息")
    @DeleteMapping("{vid}")
    public ResultVo delete_video(@PathVariable("vid") Long vid) {
       return videoService.deleteInfoById(vid);
    }

    @ApiOperation(value = "查询视频信息，分页")
    @GetMapping("list_video")
    public ResultVo list_video(VideoParm videoParm) {
        IPage<Video> list = videoService.getList(videoParm);
        return ResultUtils.success("查询成功", list);
    }

    @ApiOperation(value = "构造组装数据格式获取视频分类信息")
    @GetMapping("getTypeList")
    public ResultVo getTypeList() {
      return videoService.getTypeList();
    }

    @ApiOperation(value = "构造组装数据格式获取视频地区信息")
    @GetMapping("getRegionList")
    public ResultVo getRegionList() {
        return videoService.getRegionList();
    }

    @ApiOperation(value = "构造组装数据格式获取视频年份信息")
    @GetMapping("getYearList")
    public ResultVo getYearList() {
        return videoService.getYearList();
    }


    @ApiOperation(value = "视频信息查询")
    @GetMapping("getVideoInfoList")
    public ResultVo getVideoInfoList(ListParm listParm) {
        List<Video> list = videoService.selectVideoInfoList(listParm);
        return ResultUtils.success("查询成功",list);
    }

    @ApiOperation("通过 id 获取到视频信息")
    @GetMapping("getByIdVideInfo")
    public ResultVo getByIdVideInfo(Long vid) {
        List<Video> videos = videoService.selectVideoByIdInfo(vid);
        if ( videos != null) {
            return ResultUtils.success("查询成功", videos);
        }
        return ResultUtils.error("查询失败");
    }



    @ApiOperation("通过 电影名 查找视频信息")
    @GetMapping("getNameInfo")
    public ResultVo getNameInfo(String name) {
        QueryWrapper<Video> query = new QueryWrapper<>();
        query.lambda().like(Video::getVname,name);
        Video one = videoService.getOne(query);
        if (one != null){
            return ResultUtils.success("查询成功",one);
        }
        return ResultUtils.error("查询失败");
    }
}
