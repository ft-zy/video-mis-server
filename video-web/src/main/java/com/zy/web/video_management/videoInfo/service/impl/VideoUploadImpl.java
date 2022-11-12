package com.zy.web.video_management.videoInfo.service.impl;

import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.entity.Video;
import com.zy.web.video_management.videoInfo.service.VideoUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class VideoUploadImpl implements VideoUploadService {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

    @Value("${file-save-imgPath}")
    private String ImgSavePath;

    @Value("${file-save-videoPath}")
    private String VideoSavePath;

    @Override
        public ResultVo uploadFile(MultipartFile file, HttpServletRequest request) {
            //1.后半段目录：
            String directory = simpleDateFormat.format(new Date());

            String filePath = ImgSavePath;
            File dir = new File(filePath + directory);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //log.info("图片上传，保存位置：" + ImgSavePath + directory);
            //3.给文件重新设置一个名字
            //后缀
            String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
            if (!suffix.endsWith(".png") && !suffix.endsWith(".jpg") && !suffix.endsWith(".webp")) {
                return ResultUtils.error("文件类型不对（支持 .png / .jpg 图片类型）");
            }
            String newFileName= UUID.randomUUID().toString().replaceAll("-", "")+suffix;
            //4.创建这个新文件
            File newFile = new File(filePath + directory + newFileName);
            //5.复制操作
            try {
                file.transferTo(newFile);
                //协议 :// ip地址 ：端口号 / 文件目录
                String url = request.getScheme() + "://" + request.getServerName()
                        + ":" + request.getServerPort() + "/zy/video_web/img/" + directory + newFileName;
                log.info("图片上传，访问URL:" + url);
                return ResultUtils.success("成功",url);
            } catch (IOException e) {
                return ResultUtils.error("失败");
            }
        }

    @Override
    public ResultVo uploadVideo(MultipartFile file, HttpServletRequest request) {
        //1.后半段目录：
        String directory = simpleDateFormat.format(new Date());
        String filePath = VideoSavePath;
        File dir = new File(filePath  + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));

        String newFileName= UUID.randomUUID().toString().replaceAll("-", "")+suffix;
        File newFile = new File(filePath + directory + newFileName);
        try {
            file.transferTo(newFile);
            String url = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + "/zy/video_web/video/" + directory + newFileName;
            log.info("视频上传，访问URL:" + url);
            return ResultUtils.success("成功",url);
        } catch (IOException e) {
            return ResultUtils.error("失败");
        }
    }


}
