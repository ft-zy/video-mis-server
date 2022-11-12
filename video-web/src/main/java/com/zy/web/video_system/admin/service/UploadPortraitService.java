package com.zy.web.video_system.admin.service;


import com.zy.utils.ResultVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface UploadPortraitService {
    ResultVo uploadFile(MultipartFile file, HttpServletRequest request);
}
