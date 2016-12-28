package com.example;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 项目打包成war格式，初始化服务
 *
 * SpringBootServletInitialize子类，覆盖configure()方法
 * 来指定Spring配置类
 * Created by shichp on 2016/12/28.
 */

public class ReadingListServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootDemoApplication.class);
    }
}
