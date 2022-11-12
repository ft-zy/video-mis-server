package com.zy.web.video_management.videoInfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.utils.ResultVo;
import com.zy.web.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.web.entity.vo.ListParm;
import com.zy.web.entity.vo.VideoParm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* &#064;description  针对表【video】的数据库操作Service
* &#064;createDate  2022-08-23 16:58:31
 */
public interface VideoService extends IService<Video> {
    IPage<Video> getList(VideoParm videoParm);

    ResultVo addInfo(Video video);

    ResultVo deleteInfoById(Long vid);

    ResultVo updateInfo(Video video);

    ResultVo getTypeList();

    ResultVo getRegionList();

     ResultVo getYearList();

     List<Video> selectVideoInfoList(ListParm listParm);

    List<Video> selectVideoByIdInfo(Long vid);
}
