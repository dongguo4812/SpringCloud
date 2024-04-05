package com.dongguo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SentinelGateway9099Application {

    public static void main(String[] args) {
        SpringApplication.run(SentinelGateway9099Application.class, args);
    }

}
