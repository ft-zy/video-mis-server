package com.zy.web.video_management.video_region.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.entity.Region;
import com.zy.web.entity.vo.RegionParm;
import com.zy.web.video_management.video_region.service.RegionService;
import com.zy.web.video_management.video_region.mapper.RegionMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author Administrator
* @description 针对表【region】的数据库操作Service实现
* @createDate 2022-08-16 20:33:57
*/
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region>
    implements RegionService {

    @Resource
    private RegionMapper regionMapper;

    @Override
    public IPage<Region> getList(RegionParm regionParm) {
        // 构造分页对象
        IPage<Region> page = new Page<>();
        page.setSize(regionParm.getPageSize());
        page.setCurrent(regionParm.getCurrentPage());
        // 构造查询条件
        QueryWrapper<Region> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(regionParm.getRegion())) {
            query.lambda().like(Region::getRegion,regionParm.getRegion());
        }
        return this.baseMapper.selectPage(page, query);
    }

    @Override
    public ResultVo addRegion(Region region) {
        int save = regionMapper.insertAll(region);
        if (save > 0) {
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败");
    }

    @Override
    public ResultVo deleteById(Long rid) {
        int save = regionMapper.deleteByRid(rid);
        if (save > 0) {
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }

    @Override
    public ResultVo updateRegion(Region region) {
        int save = regionMapper.updateSelective(region);
        if (save > 0) {
            return ResultUtils.success("编辑成功");
        }
        return ResultUtils.error("编辑失败");
    }
}




