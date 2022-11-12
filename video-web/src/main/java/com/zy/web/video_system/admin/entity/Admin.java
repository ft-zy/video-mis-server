package com.zy.web.video_system.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName admin
 */
@TableName(value ="admin")
@Data
public class Admin implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "a_Id", type = IdType.AUTO)
    private Long aid;

    /**
     * 角色id
     */
    @TableField(value = "roId")
    private Long roid;

    /**
     * 账号密码
     */
    @TableField(value = "account")
    private String account;

    /**
     * 登录密码
     */
    @TableField(value = "apassword")
    private String apassword;

    /**
     * 手机号码
     */
    @TableField(value = "aphone")
    private String aphone;

    /**
     * 邮箱
     */
    @TableField(value = "aemail")
    private String aemail;

    /**
     * 性别 0：男 1：女
     */
    @TableField(value = "asex")
    private String asex;

    /**
     * 头像
     */
    @TableField(value = "aportrait")
    private String aportrait;

    /**
     * 管理员 1 | 0
     */
    @TableField(value = "isadmin")
    private Long isadmin;

    /**
     * 账户是否过期
     */
    @TableField(value = "account_non_expired")
    private boolean accountNonExpired = true;

    /**
     * 账户是否被锁定
     */
    @TableField(value = "account_non_locked")
    private boolean accountNonLocked = true;

    /**
     * 密码是否过期
     */
    @TableField(value = "accoun_non_expired")
    private boolean accounNonExpired = true;

    /**
     * 账号是否可用
     */
    @TableField(value = "aenabled")
    private Long aenabled;

    /**
     * 用户名
     */
    @TableField(value = "aname")
    private String aname;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private String roleName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}
