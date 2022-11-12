package com.zy.web.video_management.tv_series.controller;

import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.video_management.tv_series.entity.TvSeries;
import com.zy.web.video_management.tv_series.entity.TvSeriesList;
import com.zy.web.video_management.tv_series.service.TvSeriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Api(tags = "电视剧模块")
@RestController
@RequestMapping("/api/tv/")
public class TvController {

    @Resource
    private TvSeriesService tv;

    @ApiOperation("获取所有电视剧信息")
    @GetMapping("getTvInfoList")
    public ResultVo getTvInfoList(TvSeriesList tvSeriesList) {
        List<TvSeries> list = tv.selectTvInfoList(tvSeriesList);
        return ResultUtils.success("查询成功",list);
    }

    @ApiOperation("根据电视剧 id 获取信息")
    @GetMapping("getByIdTvList")
    public ResultVo getByIdTvList(Long tvid) {
        List<TvSeries> list = tv.selectTvList(tvid);
        //if (list != null) {
            return ResultUtils.success("查询成功", list);
        //}
        //return ResultUtils.error("查询失败");
    }

    @ApiOperation("添加视频信息")
    @PostMapping("add_URL")
    public ResultVo AddURL(@RequestBody TvSeries tvSeries) {
        boolean save = tv.save(tvSeries);
        if (save) {
            return ResultUtils.success("添加成功");
        }
        return ResultUtils.error("添加失败");
    }

    @ApiOperation("修改视频信息")
    @PutMapping("edit_URL")
    public ResultVo EditURL(@RequestBody TvSeries tvSeries) {
        boolean save = tv.updateById(tvSeries);
        if (save) {
            return ResultUtils.success("修改成功");
        }
        return ResultUtils.error("修改失败");
    }

    @ApiOperation("删除视频信息")
    @DeleteMapping("tv/{vid}")
    public ResultVo DeleteURL (@PathVariable("vid") Long vid) {
        boolean save = tv.removeById(vid);
        if (save) {
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }

    @ApiOperation(value = "构造组装数据格式获取电视剧名信息")
    @GetMapping("getTvList")
    public ResultVo getTvList() {
        return tv.getTvList();
    }

    @Value("${file-save-TvPath}")
    private String TvPath;

    @ApiOperation("上传电视剧视频")
    @PostMapping("uploadTvURL")
    public ResultVo uploadTvURL(MultipartFile file, HttpServletRequest request) {
        //1.后半段目录：
        String filePath = TvPath;
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));

        String newFileName= UUID.randomUUID().toString().replaceAll("-", "")+suffix;
        File newFile = new File(filePath  + newFileName);
        try {
            file.transferTo(newFile);
            String url = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + "/zy/video_web/tv/" + newFileName;
            log.info("图片上传，访问URL:" + url);
            return ResultUtils.success("成功",url);
        } catch (IOException e) {
            return ResultUtils.error("失败");
        }
    }


}
