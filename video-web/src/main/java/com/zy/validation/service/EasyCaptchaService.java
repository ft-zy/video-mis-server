package com.zy.validation.service;


import com.wf.captcha.base.Captcha;
import com.zy.enums.CodeTypeEnum;
import com.zy.validation.producer.EasyCaptchaProducer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class EasyCaptchaService {

    @Resource
    private EasyCaptchaProducer easyCaptchaProducer;

    @Resource
    private StringRedisTemplate template;

    /**
     * 获取指定类型的验证码结果以及Base64编码值
     * @param codeType 验证码类型
     * @return
     */
    @CrossOrigin
    public Map<String,String> getCaptchaValueAndBase64(CodeTypeEnum codeType){
        Captcha captcha = null;
        if (codeType == null){
            captcha = easyCaptchaProducer.getCaptcha();
        }else {
            captcha = easyCaptchaProducer.getCaptcha(codeType);
        }
        //1、获取到结果值
        String captchaValue = captcha.text();
        //对于数学类型的需要进行处理
        if (codeType == null || codeType == CodeTypeEnum.ARITHMETIC) {
            if (captcha.getCharType() - 1 == CodeTypeEnum.ARITHMETIC.ordinal() && captchaValue.contains(".")) {
                captchaValue = captchaValue.split("\\.")[0];
            }
        }
        String verCode = captchaValue.toUpperCase();
        ////2、获取到Base64编码
        String captchaBase64 = captcha.toBase64();
        String key = UUID.randomUUID().toString();
        template.opsForValue().set(key, verCode, 3, TimeUnit.MINUTES);
        System.out.println(key);
        String s = template.opsForValue().get(key);
        System.out.println(s + "<++++++++++++++++++++>");
        System.out.println(verCode + "-------------->");
        Map<String,String> result = new HashMap<>(2);
        result.put("key", key);
        result.put("code", verCode);
        result.put("base64", captchaBase64);
        return result;

    }
}
