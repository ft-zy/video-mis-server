package com.zy.web.video_management.video_region.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.entity.Region;

import com.zy.web.entity.vo.RegionParm;
import com.zy.web.video_management.video_region.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "视频地区接口")
@RestController
@RequestMapping("/api/region/")
public class RegionController {
    @Resource
    private RegionService regionService;

    @ApiOperation("新增地区信息")
    @PostMapping("add_region")
    public ResultVo add_region(@RequestBody Region region){
       return regionService.addRegion(region);
    }

    @ApiOperation("修改地区功能")
    @PutMapping("edit_region")
    public ResultVo edit_region(@RequestBody Region region) {
        return regionService.updateRegion(region);
    }

    @ApiOperation("根据地区 rid 删除地区信息")
    @DeleteMapping("{rid}")
    public ResultVo delete_region(@PathVariable("rid") Long rid) {
        return regionService.deleteById(rid);
    }

    @ApiOperation("查询地区信息，分页")
    @GetMapping("list_region")
    public ResultVo list_region(RegionParm regionParm) {
        IPage<Region> list = regionService.getList(regionParm);
        return ResultUtils.success("查询成功", list);
    }
}
