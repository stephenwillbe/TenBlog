package com.dagougou.tenblog.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author stephen
 * @Date 2020/3/8
 * @Version 1.0
 **/
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    //统一映射视图
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }
    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }
    //文件上传配置
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }
    //消息转换器
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
         //字符串转换器
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("utf-8"));
        converters.add(converter);
    }
}
