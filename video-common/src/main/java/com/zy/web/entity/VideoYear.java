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
 * @TableName video_year
 */
@ApiModel("视频年份实体")
@TableName(value ="video_year")
@Data
public class VideoYear implements Serializable {
    /**
     * id
     */
    @ApiModelProperty("主键")
    @TableId(value = "yid", type = IdType.AUTO)
    private Long yid;

    /**
     * 年份
     */
    @ApiModelProperty("视频年份")
    @TableField(value = "video_year")
    private String videoYear;

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
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

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
        VideoYear other = (VideoYear) that;
        return (this.getYid() == null ? other.getYid() == null : this.getYid().equals(other.getYid()))
            && (this.getVideoYear() == null ? other.getVideoYear() == null : this.getVideoYear().equals(other.getVideoYear()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getYid() == null) ? 0 : getYid().hashCode());
        result = prime * result + ((getVideoYear() == null) ? 0 : getVideoYear().hashCode());
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
                ", yid=" + yid +
                ", videoYear=" + videoYear +
                ", deleted=" + deleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}
