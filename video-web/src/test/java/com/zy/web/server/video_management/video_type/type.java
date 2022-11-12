package com.zy.web.server.video_management.video_type;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.frontdesk.entity.User;
import com.zy.frontdesk.service.UserService;
import com.zy.web.entity.VideoYear;
import com.zy.web.video_management.anime.entity.Anime;
import com.zy.web.video_management.anime.service.AnimeService;
import com.zy.web.video_management.anime_name.entity.AnimeName;
import com.zy.web.video_management.anime_name.service.AnimeNameService;
import com.zy.web.video_management.video_year.service.VideoYearService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;


@SpringBootTest
public class type {

    @Resource
    private JavaMailSender sender;

    @Resource
    private AnimeNameService tv;

    @Resource
    private AnimeService animeService;

    @Resource
    private VideoYearService year;

    @Resource
    private UserService userService;

    @Test
    void t2() {
        //QueryWrapper<User> wrapper = new QueryWrapper<>();
        //wrapper.lambda().eq(User::getUid, "48");
        //User one = userService.getOne(wrapper);
        //System.out.println(one);
        User byId = userService.getById(48);
        System.out.println(byId);
        String state = String.valueOf(byId.getState());
        if (state.equals("1")) {
            System.out.println("被封");
        } else {
            System.out.println("ok");
        }

    }


    @Test
    void test1() throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject("Test");
        helper.setText("sb鞠恒");
        helper.setTo("850320814@QQ.com");
        helper.setFrom("2401332784@QQ.com");
        sender.send(message);
    }

    @Test
    void test6() {
        ArrayList<AnimeName> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AnimeName t = new AnimeName();
            t.setCover("http://localhost:8080/cover/"+ (i+1) +".mp4");
            list.add(t);
        }
        tv.saveBatch(list);
    }

    @Test
    void test8() {
        ArrayList<Anime> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Anime anime = new Anime();
            anime.setVurl("http://localhost:8089/zy/video_web/anime/完美世界/"+ (i+1) +".mp4");
            list.add(anime);
        }
        animeService.saveBatch(list);
    }

    @Test
    void test7() {
        ArrayList<VideoYear> list = new ArrayList<>();
        for (int i = 2011; i <= 2022; i++) {
            VideoYear y = new VideoYear();
            y.setVideoYear(i+"");
            list.add(y);
        }
        year.saveOrUpdateBatch(list);
    }


}
