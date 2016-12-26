package com.example;

import com.example.service.CryptService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Data
@Slf4j
public class SpringbootDemoApplication implements CommandLineRunner{

	@Autowired
	private Property property;

	@Autowired
	private CryptService service;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}


	@Override
	public void run(String... strings) throws Exception {
	//	service.decryptAll(property);
		log.info("*************************************");
		log.info("AliyunOssEndpoint : " + property.getAliyunOssEndpoint());
		log.info("AliyunOssUserBucket : " + property.getAliyunOssUserBucket());
		log.info("OssAccessKeySecret : " + property.getOssAccessKeySecret());
		log.info("*************************************");


	}
}
