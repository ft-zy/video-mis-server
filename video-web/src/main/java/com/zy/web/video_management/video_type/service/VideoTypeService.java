package com.zy.web.video_management.video_type.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.utils.ResultVo;
import com.zy.web.entity.VideoType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.web.entity.vo.TypeParm;

/**
* @author Administrator
* @description 针对表【video_type】的数据库操作Service
* @createDate 2022-08-06 15:41:25
*/
public interface VideoTypeService extends IService<VideoType> {

    IPage<VideoType> getTypeList(TypeParm typeParm);

    ResultVo addType (VideoType videoType);

    ResultVo deleteById(Long id);

    ResultVo updateType(VideoType videoType);
}
