package com.zy.web.video_system.admin.service.impl;

import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.video_system.admin.service.UploadPortraitService;
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

@Slf4j
@Service
public class UploadPortraitImpl implements UploadPortraitService {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

    @Value("${file-save-Portrait}")
    private String PortraitPath;

    @Override
    public ResultVo uploadFile(MultipartFile file, HttpServletRequest request) {

        String directory = simpleDateFormat.format(new Date());

        String filePath = PortraitPath;

        File dir = new File(filePath + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        if (!suffix.endsWith(".png") && !suffix.endsWith(".jpg") && !suffix.endsWith(".webp")) {
            return ResultUtils.error("文件类型不对（支持 .png / .jpg /.webp 图片类型）");
        }
        String newFileName= UUID.randomUUID().toString().replaceAll("-", "")+suffix;
        File newFile = new File(filePath + directory + newFileName);
        try {
            file.transferTo(newFile);
            //协议 :// ip地址 ：端口号 / 文件目录
            String url = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + "/zy/video_web/Portrait/" + directory + newFileName;
            log.info("图片上传，访问URL:" + url);

            return ResultUtils.success("成功",url);
        } catch (IOException e) {
            return ResultUtils.error("失败");
        }
    }
}
