package com.zy.web.login.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class LoginResult {

    private Long aid;

    private String account;

    private String token;

    private String aportrait;

    private Long roId;


}
