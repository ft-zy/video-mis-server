package com.zy.web.video_management.videoInfo.controller;

import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("video")
public class video {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");
    @Value("${file-save-videoPath}")
    private String VideoSavePath;

    @PostMapping
    public ResultVo uploadVideo(MultipartFile file, String type, HttpServletRequest request) {
        //1.后半段目录：
        String directory = simpleDateFormat.format(new Date());
        String filePath = VideoSavePath;
        File dir = new File(filePath + "/" + type + "/" + directory);
        System.out.println(dir + "----------------------");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));

        String newFileName= UUID.randomUUID().toString().replaceAll("-", "")+suffix;
        File newFile = new File(filePath + "/" + type + "/" + directory + newFileName);
        try {
            file.transferTo(newFile);
            String url = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + "/zy/video_web/video/" + type + "/" + directory + newFileName;
            log.info("图片上传，访问URL:" + url);
            return ResultUtils.success("成功",url);
        } catch (IOException e) {
            return ResultUtils.error("失败");
        }
    }
}
