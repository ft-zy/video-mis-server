package com.zy.validation.controller;

import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.enums.CodeTypeEnum;
import com.zy.validation.service.EasyCaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Api(tags = "验证码模块")
@RestController
@RequestMapping("/api/")
public class EasyCaptchaController {

    @Autowired
    private EasyCaptchaService easyCaptchaService;

    //1、算术类型
    @ApiOperation("算法类型")
    @GetMapping("/captcha1")
    public ResultVo getGifcCaptcha1(){
        Map<String, String> res = easyCaptchaService.getCaptchaValueAndBase64(null);
        return ResultUtils.success("ok",res);
    }

    //2、Gif
    @GetMapping("/captcha2")
    public ResultVo getGifcCaptcha2(){
        Map<String, String> res = easyCaptchaService.getCaptchaValueAndBase64(CodeTypeEnum.GIF);
        return ResultUtils.success("ok",res);
    }

    //3、png类型
    @GetMapping("/captcha3")
    public ResultVo getGifcCaptcha3(){
        Map<String, String> res = easyCaptchaService.getCaptchaValueAndBase64(CodeTypeEnum.SPEC);
        return ResultUtils.success("ok",res);
    }

    //4、chinese文字类型
    @GetMapping("/captcha4")
    public ResultVo getGifcCaptcha4(){
        Map<String, String> res = easyCaptchaService.getCaptchaValueAndBase64(CodeTypeEnum.CHINESE);
        return ResultUtils.success("ok",res);
    }

    //4、chinese Gif类型
    @GetMapping("/captcha5")
    public ResultVo getGifcCaptcha5(){
        Map<String, String> res = easyCaptchaService.getCaptchaValueAndBase64(CodeTypeEnum.CHINESE_GIF);
        return ResultUtils.success("ok",res);
    }
}
