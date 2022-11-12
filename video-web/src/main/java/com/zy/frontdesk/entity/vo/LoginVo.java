package com.zy.frontdesk.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class LoginVo {

    private Long uid;

    private String username;

    private String password;

    @TableField(exist = false)
    private String uuid;

    @TableField(exist = false)
    private String verCode;
}
