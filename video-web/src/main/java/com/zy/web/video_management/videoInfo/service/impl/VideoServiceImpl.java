package com.zy.web.video_management.videoInfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.entity.Video;
import com.zy.web.entity.vo.ListParm;
import com.zy.web.entity.vo.SelectOption;
import com.zy.web.entity.vo.VideoParm;
import com.zy.web.video_management.videoInfo.mapper.VideoMapper;
import com.zy.web.video_management.videoInfo.service.VideoService;
import com.zy.web.entity.Region;
import com.zy.web.video_management.video_region.service.RegionService;
import com.zy.web.entity.VideoType;
import com.zy.web.video_management.video_type.service.VideoTypeService;
import com.zy.web.entity.VideoYear;
import com.zy.web.video_management.video_year.service.VideoYearService;
import com.zy.web.video_system.admin.entity.Admin;
import com.zy.web.video_system.admin.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @author Administrator
* &#064;description  针对表【video】的数据库操作Service实现
* &#064;createDate  2022-08-23 16:58:31
 */
@Service
@Slf4j
@Lazy
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService{

    @Resource
    private VideoMapper videoMapper;

    @Resource
    private VideoTypeService videoTypeService;

    @Resource
    private RegionService regionService;

    @Resource
    private VideoYearService videoYearService;


    @Override
    public IPage<Video> getList(VideoParm videoParm) {
        // 构造分页对象
        IPage<Video> page = new Page<>();
        page.setSize(videoParm.getPageSize());
        page.setCurrent(videoParm.getCurrentPage());
        // 构造查询条件
        QueryWrapper<Video> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(videoParm.getVname())) {
            query.lambda().like(Video::getVname,videoParm.getVname());
        }
        return this.baseMapper.selectPage(page, query);
    }

    @Override
    public ResultVo addInfo(Video video) {
        int save = videoMapper.insertAll(video);
        if (save > 0) {
            return ResultUtils.success("添加成功");
        }
        return ResultUtils.error("添加失败");
    }

    @Override
    public ResultVo deleteInfoById(Long vid) {
        int save = videoMapper.deleteById(vid);
        if (save > 0) {
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }

    @Override
    public ResultVo updateInfo(Video video) {
        int save = videoMapper.updateSelective(video);
        if (save > 0) {
            return ResultUtils.success("修改成功");
        }
        return ResultUtils.error("修改失败");
    }

    @Override
    public ResultVo getTypeList() {
        // 查询
        List<VideoType> list = videoTypeService.list();
        // 组装需要的数据格式
        List<SelectOption> selectOptions = new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .forEach(item-> {
                    SelectOption option = new SelectOption();
                    option.setValue(item.getId());
                    option.setLabel(item.getTypeName());
                    selectOptions.add(option);
                });
        return ResultUtils.success("查询成功", selectOptions);
    }

    @Override
    public ResultVo getRegionList() {
        List<Region> list = regionService.list();
        // 组装需要的数据格式
        List<SelectOption> selectOptions = new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .forEach(item-> {
                    SelectOption option = new SelectOption();
                    option.setValue(item.getRid());
                    option.setLabel(item.getRegion());
                    selectOptions.add(option);
                });
        return ResultUtils.success("查询成功", selectOptions);
    }

    @Override
    public ResultVo getYearList() {
        List<VideoYear> list = videoYearService.list();
        // 组装需要的数据格式
        List<SelectOption> selectOptions = new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .forEach(item-> {
                    SelectOption option = new SelectOption();
                    option.setValue(item.getYid());
                    option.setLabel(item.getVideoYear());
                    selectOptions.add(option);
                });
        return ResultUtils.success("查询成功", selectOptions);
    }

    // 保存视频信息
    //@Override
    //public void saveVideoInfo(VideoInfoParm videoInfoParm) {
    //    ArrayList<Video> list = new ArrayList<>();
    //        Video video = new Video();
    //        video.setVid(videoInfoParm.getVid());
    //        video.setRid(videoInfoParm.getRid());
    //        video.setId(videoInfoParm.getId());
    //        video.setYid(videoInfoParm.getYid());
    //        video.setTypeName(videoInfoParm.getTypeName());
    //        video.setVideoYear(videoInfoParm.getVideoYear());
    //        video.setRegion(videoInfoParm.getRegion());
    //        video.setVname(videoInfoParm.getVname());
    //        video.setVphoto(videoInfoParm.getVphoto());
    //        video.setVurl(videoInfoParm.getVurl());
    //        video.setVinfo(videoInfoParm.getVinfo());
    //        list.add(video);
    //    // 保存
    //    this.saveBatch(list);
    //}

    @Override
    public List<Video> selectVideoInfoList(ListParm query) {
        //条件构造器
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(query.getId() != null && query.getId().size() > 0,Video::getId)
                .in(query.getRid() != null && query.getRid().size() > 0,Video::getRid)
                .in(query.getYid() != null && query.getYid().size() > 0,Video::getYid)
                .ge(StringUtils.isNotEmpty(query.getVideoYear()), Video::getVideoYear, query.getVideoYear())
                .le(StringUtils.isNotEmpty(query.getRegion()), Video::getRegion, query.getRegion())
                .eq(StringUtils.isNotEmpty(query.getTypeName()), Video::getTypeName, query.getTypeName())
                .eq(StringUtils.isNotEmpty(query.getVname()), Video::getVname, query.getVname())
                .eq(StringUtils.isNotEmpty(query.getVphoto()), Video::getVphoto, query.getVphoto())
                .eq(StringUtils.isNotEmpty(query.getVinfo()), Video::getVinfo, query.getVinfo())
                .eq(StringUtils.isNotEmpty(query.getVurl()), Video::getVurl, query.getVurl());
        log.info(String.valueOf(queryWrapper));
        return this.baseMapper.selectVideoInfoList(queryWrapper);
    }

    @Override
    public List<Video> selectVideoByIdInfo(Long vid) {
        return this.baseMapper.selectVideoByIdInfo(vid);
    }

}




