package com.zy.web.video_management.videoInfo.service;

import com.zy.utils.ResultVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface VideoUploadService {

    ResultVo uploadFile(MultipartFile file, HttpServletRequest request);

    ResultVo uploadVideo(MultipartFile file, HttpServletRequest request);
}
