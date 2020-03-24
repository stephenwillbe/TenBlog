package com.dagougou.tenblog;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Description: TODO
 * @Author stephen
 * @Date 2020/3/8
 * @Version 1.0
 **/
public class WebInitServlet extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TenblogApplication.class);
    }
}
