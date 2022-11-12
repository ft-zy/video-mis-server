package com.zy.email.controller;

import com.zy.email.service.VerifyService;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@Api(tags = "邮件发送")
@RestController
@RequestMapping("/api/email/")
public class SendEmailController {

    @Resource
    private VerifyService verifyService;

    @ApiOperation("发送邮件")
    @GetMapping("send")
    public ResultVo SendVerify(@RequestParam("email") String email) {
        Map map = verifyService.sendVerifyCode(email);
        return ResultUtils.success("邮件发送成功", map);
    }

}
