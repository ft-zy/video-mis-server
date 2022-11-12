package com.zy.web.video_management.video_SlideShow.controller;

import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Slf4j
public class FileController {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

    @Value("${file-save-SlideShow}")
    private String SlideShowPath;

    // 上传图片
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResultVo uploadVideo(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
        int length = files.length;
        System.out.println(length);

        List list = new ArrayList<>();

        for (MultipartFile file: files) {
            // 源文件名
            String filename = file.getOriginalFilename();

            String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));

            String newFileName= UUID.randomUUID().toString().replaceAll("-", "")+suffix;

            String directory = simpleDateFormat.format(new Date());
            // 文件存储路径(注意这里image目录后一定要写\\，指定是在image目录下，否则会保存到父目录)
            String filePath = SlideShowPath;

            // 文件全路径
            File targetFile = new File(filePath + directory + newFileName);

            // 判断文件存储目录是否存在，不存在则新建目录
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdir();
            }
            try {
                // 将图片保存
                file.transferTo(targetFile);

                String url = request.getScheme() + "://" + request.getServerName()
                        + ":" + request.getServerPort() + "/zy/video_web/SlideShow/" + directory + newFileName;
                list.add(url);
                System.out.println(url);
            } catch (IOException e) {
                 log.info("文件上传异常:{}",e);
            }

        }
        return ResultUtils.success("ok", list);
    }
}
