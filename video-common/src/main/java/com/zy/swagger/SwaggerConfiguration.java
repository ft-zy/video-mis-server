package com.zy.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
@EnableKnife4j
public class SwaggerConfiguration {
    @Bean
    public Docket restApi(Environment environment) {
        // 设置要显示swagger的环境
        Profiles of = Profiles.of("dev", "test");
        // 判断当前是否处于该环境,通过 enable() 接收此参数判断是否要显示
        boolean isShow = true;//= environment.acceptsProfiles(of);

        return new Docket(DocumentationType.SWAGGER_2)
                // 配置分组
                .groupName("标准接口")
                //是否开启 (true 开启  false隐藏。生产环境建议隐藏)
                .enable(isShow)
                .apiInfo(apiInfo("zy 视频播放网站 Swagger文档 Api", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                //通过.select()方法，去配置扫描接口
                .select()
                //扫描的路径包,设置basePackage会将包下的所有被@Api标记类的所有方法作为api . 配合@EnableOpenApi 找到API位置，不需要再在启动类上配置
                //.apis(RequestHandlerSelectors.basePackage("com.zy.web"))
                //指定路径处理,PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfoBuilder()
                .title(title)
                .termsOfServiceUrl("http://localhost:8081/dashboard")
                .contact(new Contact("zy", "http://localhost:8081/dashboard", "2401332784@qq.com"))
                .version(version)
                .build();
    }


}
