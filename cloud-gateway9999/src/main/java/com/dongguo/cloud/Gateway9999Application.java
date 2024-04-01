package com.dongguo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //服务注册和发现
public class Gateway9999Application {
    public static void main(String[] args) {
        SpringApplication.run(Gateway9999Application.class, args);
    }
}
