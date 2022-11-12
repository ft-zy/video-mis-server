package com.zy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class VideoConfig implements WebMvcConfigurer {

    @Value("${file-save-imgPath}")
    private String ImgSavePath;

    @Value("${file-save-videoPath}")
    private String VideoSavePath;

    @Value("F:/video_web/SlideShow/")
    private String SlideShowPath;

    @Value("${file-save-Portrait}")
    private String PortraitPath;

    @Value("${file-save-AnimePath}")
    private String AnimePath;

    @Value("${file-save-AnimeCoverPath}")
    private String Anime_Cover_Path;

    @Value("${file-save-TvPath}")
    private String TvPath;

    @Value("${file-save-TvCoverPath}")
    private String Tv_Cover_Path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/video_web/img/**")
                .addResourceLocations("file:" + ImgSavePath);
        registry.addResourceHandler("/video_web/video/**")
                .addResourceLocations("file:" + VideoSavePath);
        registry.addResourceHandler("/video_web/anime/**")
                .addResourceLocations("file:" + AnimePath);
        registry.addResourceHandler("/video_web/anime_cover/**")
                .addResourceLocations("file:" + Anime_Cover_Path);
        registry.addResourceHandler("/video_web/tv/**")
                .addResourceLocations("file:" + TvPath);
        registry.addResourceHandler("/video_web/tv_cover/**")
                .addResourceLocations("file:" + Tv_Cover_Path);
        registry.addResourceHandler("/video_web/SlideShow/**")
                .addResourceLocations("file:" + SlideShowPath);
        registry.addResourceHandler("/video_web/Portrait/**")
                .addResourceLocations("file:" + PortraitPath);

    }
}
