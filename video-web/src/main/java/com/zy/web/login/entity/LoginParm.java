package com.zy.web.login.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class LoginParm {

    private String account;

    private String apassword;

    @TableField(exist = false)
    private String verCode;

    @TableField(exist = false)
    private String uuid;

    //@TableField(exist = false)
    //private String verificationCode;

}
