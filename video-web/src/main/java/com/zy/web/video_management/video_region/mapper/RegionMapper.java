package com.zy.web.video_management.video_region.mapper;

import com.zy.web.entity.Region;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【region】的数据库操作Mapper
* @createDate 2022-08-16 20:33:57
* @Entity com.zy.web.server.video_management.video_region.entity.Region
*/
@Mapper
public interface RegionMapper extends BaseMapper<Region> {

    int insertAll(Region region);
    int deleteByRid(@Param("rid") Long rid);

    int updateSelective(Region region);
}




