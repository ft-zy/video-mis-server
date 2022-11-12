package com.zy.email.service.Impl;

import com.zy.email.service.VerifyService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class VerifyServiceImpl implements VerifyService {

    @Resource
    JavaMailSender sender;

    @Resource
    StringRedisTemplate template;

    @Value("${spring.mail.username}")
    String from;

    @SneakyThrows
    @Override
    public Map sendVerifyCode(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("[xxx网站] 您的注册验证码");
        Random random = new Random();
        int code = random.nextInt(899999) + 100000;
        String key = UUID.randomUUID().toString();
        template.opsForValue().set(key ,code+"",3, TimeUnit.MINUTES);

        System.out.println(key);
        System.out.println(code);
        System.out.println(email);
        System.out.println(from);

        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        Date date = new Date();
        message.setText("您的注册验证码为："+ code +"，三分钟内有效，请及时完成注册，如果不是本人操作，请忽略。 " + myFmt.format(date));
        message.setTo(email);
        message.setFrom(from);
        sender.send(message);

        Map<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("verCode", String.valueOf(code));
        return map;
    }


}
