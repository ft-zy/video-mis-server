package com.zy.frontdesk.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Back {

    private String email;

    @TableField(exist = false)
    private String uuid;

    @TableField(exist = false)
    private String VerCode;
}
