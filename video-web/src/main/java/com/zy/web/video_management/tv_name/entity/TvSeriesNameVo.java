package com.zy.web.video_management.tv_name.entity;

import lombok.Data;

import java.util.List;

@Data
public class TvSeriesNameVo {

    private List<Long> id;

    private List<Long> yid;

    private List<Long> rid;

    private String name;

    private String cover;

    private String info;

    private String typeName;

    private String region;

    private String videoYear;

}
