package com.zy.web.video_management.anime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName anime
 */
@TableName(value ="anime")
@Data
public class Anime implements Serializable {
    /**
     * 视频id
     */
    @TableId(value = "avid", type = IdType.AUTO)
    private Long avid;

    /**
     * 电视id
     */
    @TableField(value = "aid")
    private Long aid;

    /**
     * 电视集数
     */
    @TableField(value = "count")
    private Long count;

    /**
     * 视频存储地址
     */
    @TableField(value = "vurl")
    private String vurl;

    /**
     * 视频播放量
     */
    @TableField(value = "vnum")
    private Long vnum;

    /**
     * 视频点赞量
     */
    @TableField(value = "anum")
    private Long anum;

    /**
     * 视频差劲量
     */
    @TableField(value = "bnum")
    private Long bnum;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    private Long deleted;

    /**
     * 添加时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String name;


}
