package com.zy.web.video_system.admin_role.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName admin_role
 */
@TableName(value ="admin_role")
@Data
public class AdminRole implements Serializable {
    /**
     * 用户角色表
     */
    @TableId(value = "urid", type = IdType.AUTO)
    private Long urid;

    /**
     * 用户id
     */
    @TableField(value = "a_Id")
    private Long aid;

    /**
     * 角色id
     */
    @TableField(value = "roId")
    private Long roid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}
