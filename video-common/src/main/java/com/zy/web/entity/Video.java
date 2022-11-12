package com.zy.web.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * &#064;TableName  video
 */
@ApiModel("视频信息实体")
@TableName(value ="video")
@Data
public class Video implements Serializable {
    /**
     * 视频id
     */
    @ApiModelProperty("主键")
    @TableId(value = "vid", type = IdType.AUTO)
    private Long vid;

    /**
     * 视频名称
     */
    @ApiModelProperty("视频名字")
    @TableField(value = "vname")
    private String vname;

    /**
     * 视频介绍
     */
    @ApiModelProperty("视频简介")
    @TableField(value = "vinfo")
    private String vinfo;

    /**
     * 视频存储地址
     */
    @ApiModelProperty("视频存放地址")
    @TableField(value = "vurl")
    private String vurl;

    /**
     * 视频播放量
     */
    @ApiModelProperty("视频播放量")
    @TableField(value = "vnum")
    private Integer vnum;

    /**
     * 视频点赞量
     */
    @ApiModelProperty("视频点赞数")
    @TableField(value = "anum")
    private Integer anum;

    /**
     * 视频差劲量
     */
    @ApiModelProperty("视频差劲数")
    @TableField(value = "bnum")
    private Integer bnum;

    /**
     * 地区
     */
    @ApiModelProperty("地区id")
    @TableField(value = "rid")
    private Integer rid;

    /**
     * 类型
     */
    @ApiModelProperty("类型id")
    @TableField(value = "id")
    private Integer id;

    /**
     * 年份
     */
    @ApiModelProperty("年份id")
    @TableField(value = "yid")
    private Integer yid;

    /**
     * 逻辑删除
     */
    @ApiModelProperty("逻辑删除")
    @TableField(value = "deleted")
    private Integer deleted;

    /**
     * 添加时间
     */
    @ApiModelProperty("添加时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 视频封面
     */
    @ApiModelProperty("视频封面")
    @TableField(value = "vphoto")
    private String vphoto;

    @TableField(exist = false)
    private String region;

    @TableField(exist = false)
    private String typeName;

    @TableField(exist = false)
    private String videoYear;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Video other = (Video) that;
        return (this.getVid() == null ? other.getVid() == null : this.getVid().equals(other.getVid()))
                && (this.getRid() == null ? other.getRid() == null : this.getRid().equals(other.getRid()))
                && (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getYid() == null ? other.getYid() == null : this.getYid().equals(other.getYid()))
                && (this.getVname() == null ? other.getVname() == null : this.getVname().equals(other.getVname()))
                && (this.getVinfo() == null ? other.getVinfo() == null : this.getVinfo().equals(other.getVinfo()))
                && (this.getVphoto() == null ? other.getVphoto() == null : this.getVphoto().equals(other.getVphoto()))
                && (this.getVurl() == null ? other.getVurl() == null : this.getVurl().equals(other.getVurl()))
                && (this.getVnum() == null ? other.getVnum() == null : this.getVnum().equals(other.getVnum()))
                && (this.getAnum() == null ? other.getAnum() == null : this.getAnum().equals(other.getAnum()))
                && (this.getBnum() == null ? other.getBnum() == null : this.getBnum().equals(other.getBnum()))
                && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getVid() == null) ? 0 : getVid().hashCode());
        result = prime * result + ((getRid() == null) ? 0 : getRid().hashCode());
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getYid() == null) ? 0 : getYid().hashCode());
        result = prime * result + ((getVname() == null) ? 0 : getVname().hashCode());
        result = prime * result + ((getVinfo() == null) ? 0 : getVinfo().hashCode());
        result = prime * result + ((getVphoto() == null) ? 0 : getVphoto().hashCode());
        result = prime * result + ((getVurl() == null) ? 0 : getVurl().hashCode());
        result = prime * result + ((getVnum() == null) ? 0 : getVnum().hashCode());
        result = prime * result + ((getAnum() == null) ? 0 : getAnum().hashCode());
        result = prime * result + ((getBnum() == null) ? 0 : getBnum().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", vid=" + vid +
                ", vname=" + vname +
                ", vinfo=" + vinfo +
                ", vurl=" + vurl +
                ", vnum=" + vnum +
                ", anum=" + anum +
                ", bnum=" + bnum +
                ", rid=" + rid +
                ", id=" + id +
                ", yid=" + yid +
                ", deleted=" + deleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", vphoto=" + vphoto +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}
