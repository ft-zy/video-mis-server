package com.zy.web.video_management.tv_name.service;

import com.zy.utils.ResultVo;
import com.zy.web.entity.Video;
import com.zy.web.entity.vo.ListParm;
import com.zy.web.video_management.tv_name.entity.TvSeriesName;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.web.video_management.tv_name.entity.TvSeriesNameVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tv_series_name】的数据库操作Service
* @createDate 2022-11-02 15:53:07
*/
public interface TvSeriesNameService extends IService<TvSeriesName> {
    List<TvSeriesName> selectTvSeriesNameInfoList(TvSeriesNameVo tvSeriesNameVo);

    ResultVo AddTvName(TvSeriesName tvSeriesName);

    ResultVo EditTvName(TvSeriesName tvSeriesName);

    ResultVo DeleteById(Long tvid);

    List<TvSeriesName> selectByIdInfo(Long tvid);

}
