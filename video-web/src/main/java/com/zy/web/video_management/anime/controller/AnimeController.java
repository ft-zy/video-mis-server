package com.zy.web.video_management.anime.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.video_management.anime.entity.Anime;
import com.zy.web.video_management.anime.entity.AnimeList;
import com.zy.web.video_management.anime.entity.AnimeParm;
import com.zy.web.video_management.anime.service.AnimeService;
import com.zy.web.video_management.anime_name.entity.AnimeName;
import com.zy.web.video_management.anime_name.entity.AnimeNameVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Api(tags = "动漫模块")
@RestController
@RequestMapping("/api/anime/")
public class AnimeController {

    @Resource
    private AnimeService animeService;

    @ApiOperation("根据动漫 id 获取信息")
    @GetMapping("getByIdAnimeList")
    public ResultVo getByIdAnimeList(Long aid) {
        List<Anime> list = animeService.selectAnimeList(aid);
            return ResultUtils.success("查询成功", list);
    }

    @ApiOperation("获取所有动漫信息")
    @GetMapping("getAnimeInfoList")
    public ResultVo getAnimeInfoList(AnimeList animeList) {
        List<Anime> list = animeService.selectAnimeInfoList(animeList);
        return ResultUtils.success("查询成功",list);
    }

    @ApiOperation("添加动漫信息")
    @PostMapping("add_animeInfo")
    public ResultVo AddAnimeInfo(@RequestBody Anime anime) {
        boolean save = animeService.save(anime);
        if (save) {
            return ResultUtils.success("添加成功");
        }
        return ResultUtils.error("添加失败");
    }

    @ApiOperation("修改动漫信息")
    @PutMapping("edit_animeInfo")
    public ResultVo EditAnimeInfo(@RequestBody Anime anime) {
        boolean save = animeService.updateById(anime);
        if (save) {
            return ResultUtils.success("修改成功");
        }
        return ResultUtils.error("修改失败");
    }

    @ApiOperation("删除动漫信息")
    @DeleteMapping("anime/{avid}")
    public ResultVo DeleteById(@PathVariable("avid") Long avid) {
        boolean save = animeService.removeById(avid);
        if (save) {
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }

    @Value("${file-save-AnimePath}")
    private String AnimePath;

    @ApiOperation("上传动漫视频")
    @PostMapping("uploadAnimeURL")
    public ResultVo uploadVideo(MultipartFile file, HttpServletRequest request) {
        //1.后半段目录：
        String filePath = AnimePath;
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));

        String newFileName= UUID.randomUUID().toString().replaceAll("-", "")+suffix;
        File newFile = new File(filePath  + newFileName);
        try {
            file.transferTo(newFile);
            String url = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + "/zy/video_web/anime/" + newFileName;
            log.info("动漫上传，访问URL:" + url);
            return ResultUtils.success("成功",url);
        } catch (IOException e) {
            return ResultUtils.error("失败");
        }
    }

    //@ApiOperation("分页查询")
    //@GetMapping("anime_list")
    //public ResultVo getPageList(AnimeParm animeParm) {
    //    IPage<Anime> list = animeService.getList(animeParm);
    //    return ResultUtils.success("查询成功", list);
    //}

    @ApiOperation(value = "构造组装数据格式获取动漫名信息")
    @GetMapping("getAnimeList")
    public ResultVo getAnimeList() {
        return animeService.getAnimeList();
    }

}
