package com.zy.web.video_management.tv_series.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName tv_series
 */
@TableName(value ="tv_series")
@Data
public class TvSeries implements Serializable {
    /**
     * 视频id
     */
    @TableId(value = "vid", type = IdType.AUTO)
    private Long vid;

    /**
     * 电视id
     */
    @TableField(value = "tvid")
    private Long tvid;

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
        TvSeries other = (TvSeries) that;
        return (this.getVid() == null ? other.getVid() == null : this.getVid().equals(other.getVid()))
            && (this.getTvid() == null ? other.getTvid() == null : this.getTvid().equals(other.getTvid()))
            && (this.getCount() == null ? other.getCount() == null : this.getCount().equals(other.getCount()))
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
        result = prime * result + ((getTvid() == null) ? 0 : getTvid().hashCode());
        result = prime * result + ((getCount() == null) ? 0 : getCount().hashCode());
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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", vid=").append(vid);
        sb.append(", tvid=").append(tvid);
        sb.append(", count=").append(count);
        sb.append(", vurl=").append(vurl);
        sb.append(", vnum=").append(vnum);
        sb.append(", anum=").append(anum);
        sb.append(", bnum=").append(bnum);
        sb.append(", deleted=").append(deleted);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
