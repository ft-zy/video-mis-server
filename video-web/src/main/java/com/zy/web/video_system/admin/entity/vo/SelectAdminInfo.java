package com.zy.web.video_system.admin.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class SelectAdminInfo {

    private List<Long> roId;

    private String account;

    private String apassword;

    private String aphone;

    private String aemil;

    private String asex;

    private String aportrait;

    private String aname;

    private String roleName;


}
