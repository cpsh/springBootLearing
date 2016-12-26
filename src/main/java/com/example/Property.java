package com.example;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by nick on 16/4/13.
 */
@Component("property")
//@PropertySource("file:startup.properties")
@PropertySource("classpath:startup_new.properties")
@Getter
public class Property {

    @Value("${aliyun.oss.endpoint}")
    private String aliyunOssEndpoint;

    @Value("${aliyun.oss.user.bucket}")
    private String aliyunOssUserBucket;

    @Value("${aliyun.queueName}")
    private String queueName;

    @Value("${aliyun.oss.accessKeyId}")
    private String ossAccessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String ossAccessKeySecret;

    @Value("${datasource.username}")
    private String dataSourceUsername;

    @Value("${datasource.password}")
    private String dataSourcePassword;

    @Value("${datasource.url}")
    private String dataSourceUrl;

    @Value("${datasource.driver}")
    private String dataSourceDriver;

}
