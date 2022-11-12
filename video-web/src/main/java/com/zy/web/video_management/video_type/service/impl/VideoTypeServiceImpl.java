package com.zy.web.video_management.video_type.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.entity.VideoType;
import com.zy.web.entity.vo.TypeParm;
import com.zy.web.video_management.video_type.service.VideoTypeService;
import com.zy.web.video_management.video_type.mapper.VideoTypeMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author Administrator
* @description 针对表【video_type】的数据库操作Service实现
* @createDate 2022-08-06 15:41:25
*/
@Service
public class VideoTypeServiceImpl extends ServiceImpl<VideoTypeMapper, VideoType>
    implements VideoTypeService{

    @Resource
    private VideoTypeMapper videoTypeMapper;

    @Override
    public IPage<VideoType> getTypeList(TypeParm typeParm) {
        IPage<VideoType> page = new Page<>();
        page.setSize(typeParm.getPageSize());
        page.setCurrent(typeParm.getCurrentPage());

        QueryWrapper<VideoType> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(typeParm.getTypeName())) {
            query.lambda().like(VideoType::getTypeName,typeParm.getTypeName());
        }
        return this.baseMapper.selectPage(page, query);
    }

    @Override
    public ResultVo addType(VideoType videoType) {
        int save = videoTypeMapper.insertAll(videoType);
        if (save > 0) {
            return ResultUtils.success("添加成功");
        }
        return ResultUtils.error("添加失败");
    }

    @Override
    public ResultVo deleteById(Long id) {
        int save = videoTypeMapper.deleteById(id);
        if (save > 0) {
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }

    @Override
    public ResultVo updateType(VideoType videoType) {
        int save = videoTypeMapper.updateSelective(videoType);
        if (save > 0) {
            return ResultUtils.success("编辑成功");
        }
        return ResultUtils.error("编辑失败");
    }
}




