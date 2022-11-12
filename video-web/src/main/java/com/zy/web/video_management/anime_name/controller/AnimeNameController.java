package com.zy.web.video_management.anime_name.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.video_management.anime.entity.Anime;
import com.zy.web.video_management.anime.service.AnimeService;
import com.zy.web.video_management.anime_name.entity.AnimeName;
import com.zy.web.video_management.anime_name.entity.AnimeNameParm;
import com.zy.web.video_management.anime_name.entity.AnimeNameVo;
import com.zy.web.video_management.anime_name.service.AnimeNameService;
import com.zy.web.video_management.tv_name.entity.TvSeriesName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Api(tags = "动漫名信息")
@RestController
@RequestMapping("/api/anime_name/")
public class AnimeNameController {

    @Resource
    private AnimeNameService animeNameService;

    @Resource
    private AnimeService animeService;

    @ApiOperation("获取所有动漫信息")
    @GetMapping("getAnimeNameList")
    public ResultVo getAnimeNameList(AnimeNameVo animeNameVo) {
        List<AnimeName> list = animeNameService.selectAnimeNameInfoList(animeNameVo);
        return ResultUtils.success("查询成功",list);
    }

    @ApiOperation("根据id获取电视剧信息")
    @GetMapping("selectAnimeNameByIdInfo")
    public ResultVo selectAnimeNameByIdInfo(Long aid) {
        List<AnimeName> animeNames = animeNameService.selectAnimeNameByIdInfo(aid);
        return ResultUtils.success("查询成功", animeNames);
    }

    @ApiOperation("添加动漫名")
    @PostMapping("add_animName")
    public ResultVo AddAnimeName(@RequestBody AnimeName animeName) {
        QueryWrapper<AnimeName> query = new QueryWrapper<>();
        query.lambda().eq(AnimeName::getName, animeName.getName());
        AnimeName one = animeNameService.getOne(query);
        if (one != null) {
            return ResultUtils.e("已有" + animeName.getName() + "动漫");
        }
        boolean save = animeNameService.save(animeName);
        if (save) {
            return ResultUtils.success("添加成功");
        }
        return ResultUtils.error("添加失败");
    }

    @ApiOperation("修改动漫名")
    @PutMapping("edit_animeName")
    public ResultVo EditAnimeName(@RequestBody AnimeName animeName) {
        boolean save = animeNameService.updateById(animeName);
        if (save) {
            return ResultUtils.success("修改成功");
        }
        return ResultUtils.error("修改失败");
    }

    @ApiOperation("删除动漫信息")
    @GetMapping("{aid}")
    public ResultVo DeleteById(@PathVariable("aid") Long aid) {
        boolean save = animeNameService.removeById(aid);
        if (save) {
            animeNameService.deleteAid(aid);
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }

    @Value("${file-save-AnimeCoverPath}")
    private String AnimeCoverPath;

    @ApiOperation("上传动漫封面")
    @PostMapping("uploadAnimeCover")
    public ResultVo uploadAnimeCover(MultipartFile file, HttpServletRequest request) {
        String filePath = AnimeCoverPath;
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        if (!suffix.endsWith(".png") && !suffix.endsWith(".jpg") && !suffix.endsWith(".webp")) {
            return ResultUtils.error("文件类型不对（支持 .png / .jpg 图片类型）");
        }
        String newFileName= UUID.randomUUID().toString().replaceAll("-", "")+suffix;
        //4.创建这个新文件
        File newFile = new File(filePath + newFileName);
        //5.复制操作
        try {
            file.transferTo(newFile);
            //协议 :// ip地址 ：端口号 / 文件目录
            String url = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + "/zy/video_web/anime_cover/"  + newFileName;
            log.info("图片上传，访问URL:" + url);
            return ResultUtils.success("成功",url);
        } catch (IOException e) {
            return ResultUtils.error("失败");
        }
    }

}
