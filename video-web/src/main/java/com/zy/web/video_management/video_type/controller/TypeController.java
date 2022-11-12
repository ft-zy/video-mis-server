package com.zy.web.video_management.video_type.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.entity.VideoType;
import com.zy.web.entity.vo.TypeParm;
import com.zy.web.video_management.video_type.service.VideoTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "视频分类")
@RestController
@RequestMapping("/api/type/")
public class TypeController {

    @Resource
    private VideoTypeService videoTypeService;

    @ApiOperation("新增视频分类信息")
    @PostMapping("type_add")
    public ResultVo type_add(@RequestBody VideoType videoType) {
        return videoTypeService.addType(videoType);
    }

    @ApiOperation("修改视频分类信息")
    @PutMapping("type_edit")
    public ResultVo type_edit(@RequestBody VideoType videoType) {
        return videoTypeService.updateType(videoType);
    }

    @ApiOperation("根据分类 id 删除视频信息")
    @DeleteMapping("{id}")
    public ResultVo type_delete(@PathVariable("id") Long id) {
       return videoTypeService.deleteById(id);
    }

    @ApiOperation("查询分类信息，分页")
    @GetMapping("type_list")
    public ResultVo getList(TypeParm typeParm){
        IPage<VideoType> list = videoTypeService.getTypeList(typeParm);
        return ResultUtils.success("查询成功", list);
    }

}
