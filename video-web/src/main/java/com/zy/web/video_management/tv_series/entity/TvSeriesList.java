package com.zy.web.video_management.tv_series.entity;

import lombok.Data;

import java.util.List;

@Data
public class TvSeriesList {

    private List<Long> tvid;

    private String name;

    private String count;

    private String vurl;
}
