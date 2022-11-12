package com.zy.web.video_management.tv_name.controller;


import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.entity.Video;
import com.zy.web.entity.vo.ListParm;
import com.zy.web.video_management.tv_name.entity.TvSeriesName;
import com.zy.web.video_management.tv_name.entity.TvSeriesNameVo;
import com.zy.web.video_management.tv_name.service.TvSeriesNameService;
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
@Api(tags = "电视剧名信息")
@RestController
@RequestMapping("/api/tv_name/")
public class TvNameController {
    @Resource
    private TvSeriesNameService tv;

    @ApiOperation("获取所有电视剧信息")
    @GetMapping("getTvNameList")
    public ResultVo getTvNameList(TvSeriesNameVo tvSeriesNameVo) {
        List<TvSeriesName> list = tv.selectTvSeriesNameInfoList(tvSeriesNameVo);
        return ResultUtils.success("查询成功",list);
    }


    @ApiOperation("根据id获取电视剧信息")
    @GetMapping("getTvNameByIdList")
    public ResultVo getAnimeNameList(Long tvid) {
        List<TvSeriesName> tv = this.tv.selectByIdInfo(tvid);
        return ResultUtils.success("查询成功", tv);
    }

    @ApiOperation("添加电视剧剧名")
    @PostMapping("add_tv_name")
    public ResultVo AddTvName(@RequestBody TvSeriesName tvSeriesName) {
       return tv.AddTvName(tvSeriesName);
    }

    @ApiOperation("修改电视剧名")
    @PutMapping("edit_tv_name")
    public ResultVo EditTvName(@RequestBody TvSeriesName tvSeriesName) {
        return tv.EditTvName(tvSeriesName);
    }

    @ApiOperation("删除电视信息")
    @GetMapping("{tvid}")
    public ResultVo DeleteById(@PathVariable("tvid") Long tvid) {
       return tv.DeleteById(tvid);
    }

    @Value("${file-save-TvCoverPath}")
    private String TvCoverPath;

    @ApiOperation("上传动漫封面")
    @PostMapping("uploadTvCover")
    public ResultVo uploadTvCover(MultipartFile file, HttpServletRequest request) {
        String filePath = TvCoverPath;
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        if (!suffix.endsWith(".png") && !suffix.endsWith(".jpg") && !suffix.endsWith(".webp")) {
            return ResultUtils.error("文件类型不对（支持 .png / .jpg 图片类型）");
        }
        String newFileName= UUID.randomUUID().toString().replaceAll("-", "")+suffix;
        //4.创建这个新文件
        File newFile = new File(filePath + newFileName);
        //5.复制操作
        try {
            file.transferTo(newFile);
            //协议 :// ip地址 ：端口号 / 文件目录
            String url = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + "/zy/video_web/tv_cover/"  + newFileName;
            log.info("图片上传，访问URL:" + url);
            return ResultUtils.success("成功",url);
        } catch (IOException e) {
            return ResultUtils.error("失败");
        }
    }
}
