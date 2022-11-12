package com.zy.web.entity.vo;

import lombok.Data;

@Data
public class RegionParm {
    private Long currentPage;
    private Long pageSize;
    private String region;
}
