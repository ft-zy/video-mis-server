package com.zy.web.video_management.video_region.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.utils.ResultVo;
import com.zy.web.entity.Region;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.web.entity.vo.RegionParm;

/**
* @author Administrator
* @description 针对表【region】的数据库操作Service
* @createDate 2022-08-16 20:33:57
*/
public interface RegionService extends IService<Region> {

    IPage<Region> getList(RegionParm regionParm);

    ResultVo addRegion(Region region);

    ResultVo deleteById(Long rid);

    ResultVo updateRegion(Region region);

}
