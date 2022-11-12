package com.zy.web.video_system.admin.controller;

import com.zy.utils.ResultVo;
import com.zy.web.video_system.admin.service.UploadPortraitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(tags = "上传用户头像模块")
@RestController
@RequestMapping("/api/admin/")
public class UploadPortrait {

    @Resource
    private UploadPortraitService portraitService;

    @ApiOperation("上传头像")
    @RequestMapping(value = "uploadPortrait", method = RequestMethod.POST)
    public ResultVo uploadPortrait(MultipartFile file, HttpServletRequest request) {
        return portraitService.uploadFile(file,request);
    }

}
