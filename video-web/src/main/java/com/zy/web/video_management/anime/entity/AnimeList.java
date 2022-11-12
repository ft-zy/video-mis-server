package com.zy.web.video_management.anime.entity;

import lombok.Data;

import java.util.List;

@Data
public class AnimeList {

    private List<Long> aid;

    private String name;

    private String count;

    private String vurl;

}
