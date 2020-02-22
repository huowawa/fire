/*
 * Copyright (c)
 */
package com.soft.fire.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.soft.fire.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义MVC配置
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-02-09 18:21
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器
        registry.addInterceptor(myInterceptor())
                //添加拦截路径 拦截所有请求
                .addPathPatterns("/**")
                //白名单 排除拦截路径
                .excludePathPatterns("/login", "/api", "/index",
                        "/**/*.js", "/**/*.css", "/**/*.jpg", "/**/*.png");
    }

    @Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //新增自定义的HttpMessageConverter
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //创建FastJson配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //定制Json序列化策略
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteMapNullValue);

        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        // 处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);

        converters.add(fastJsonHttpMessageConverter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         *静态资源请求/person/** 映射到类路径下的/static/person/ 目录
         */
        registry.addResourceHandler("/person/**")
                .addResourceLocations("classpath:/static/person/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //配置允许跨域的路径
        registry.addMapping("/**")
                //配置允许访问的跨域资源的请求域名
                .allowedOrigins("*")
                //配置允许访问该跨域资源服务器的请求方法
                .allowedHeaders("GET,POST")
                //配置允许请求 头部head的访问
                .allowedHeaders("*");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        //字符串类型转为日期类型
        registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
    }
}