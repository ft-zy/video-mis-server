package com.zy.frontdesk.entity.vo;

import lombok.Data;

@Data
public class UserParm {
    private Long currentPage;

    private Long pageSize;

    private String username;

    private String netName;

    private String email;

}
