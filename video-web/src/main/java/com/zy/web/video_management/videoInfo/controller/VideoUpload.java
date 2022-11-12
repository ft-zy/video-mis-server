package com.zy.web.video_management.videoInfo.controller;

import com.zy.utils.ResultVo;
import com.zy.web.video_management.videoInfo.service.VideoUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(tags = "上传图片和视频功能")
@RestController
@RequestMapping("/api/video/upload/")
public class VideoUpload {

    @Resource
    private VideoUploadService videoUploadService;

    @ApiOperation("上传视频封面功能")
    @PostMapping("UploadImgPic")
    public ResultVo UploadImgPic(MultipartFile file, HttpServletRequest request) {
        return videoUploadService.uploadFile(file,request);
    }

    @ApiOperation("上传视频视频功能")
    @PostMapping("UploadVideoPic")
    public ResultVo UploadVideoPic(MultipartFile file, HttpServletRequest request) {
        return videoUploadService.uploadVideo(file,request);
    }




}

