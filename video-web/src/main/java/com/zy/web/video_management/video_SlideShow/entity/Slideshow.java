package com.zy.web.video_management.video_SlideShow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 *
 * @TableName slideshow
 */
@TableName(value ="slideshow")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Slideshow implements Serializable {
    /**
     * 轮播图id
     */
    @TableId(value = "sid", type = IdType.AUTO)
    private Long sid;

    /**
     * 轮播图名字
     */
    @TableField(value = "Swiper_name")
    private String swiperName;

    /**
     * 是否是轮播图
     */
    @TableField(value = "isSwiper")
    private Boolean isswiper;

    /**
     * 轮播图地址
     */
    @TableField(value = "swiperPic")
    private String swiperpic;

    /**
     * 轮播图排序
     */
    @TableField(value = "swiperSort")
    private Integer swipersort;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    private Integer deleted;

    /**
     * 添加时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

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
        Slideshow other = (Slideshow) that;
        return (this.getSid() == null ? other.getSid() == null : this.getSid().equals(other.getSid()))
            && (this.getSwiperName() == null ? other.getSwiperName() == null : this.getSwiperName().equals(other.getSwiperName()))
            && (this.getIsswiper() == null ? other.getIsswiper() == null : this.getIsswiper().equals(other.getIsswiper()))
            && (this.getSwiperpic() == null ? other.getSwiperpic() == null : this.getSwiperpic().equals(other.getSwiperpic()))
            && (this.getSwipersort() == null ? other.getSwipersort() == null : this.getSwipersort().equals(other.getSwipersort()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSid() == null) ? 0 : getSid().hashCode());
        result = prime * result + ((getSwiperName() == null) ? 0 : getSwiperName().hashCode());
        result = prime * result + ((getIsswiper() == null) ? 0 : getIsswiper().hashCode());
        result = prime * result + ((getSwiperpic() == null) ? 0 : getSwiperpic().hashCode());
        result = prime * result + ((getSwipersort() == null) ? 0 : getSwipersort().hashCode());
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
        sb.append(", sid=").append(sid);
        sb.append(", swiperName=").append(swiperName);
        sb.append(", isswiper=").append(isswiper);
        sb.append(", swiperpic=").append(swiperpic);
        sb.append(", swipersort=").append(swipersort);
        sb.append(", deleted=").append(deleted);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public void getSid(int i) {
    }
}
