package com.zy.frontdesk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @TableName user
 */
@TableName(value ="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "u_id", type = IdType.AUTO)
    private Long uid;

    /**
     * 用户账号
     */
    @TableField(value = "username")
    private String username;

    /**
     * 用户网名
     */
    @TableField(value = "net_name")
    private String netName;

    /**
     * 用户密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 用户头像
     */
    @TableField(value = "uportrait")
    private String uportrait;

    /**
     * 0-未删除；1-已删除
     */
    @TableField(value = "deleted")
    private Long deleted;

    /**
     * 乐观锁
     */
    @TableField(value = "version")
    private Long version;

    /**
     *
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     *
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 用户状态 0 正常 1被封
     */
    @TableField(value = "state")
    private Long state;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String uuid;

    @TableField(exist = false)
    private String VerCode;
}
