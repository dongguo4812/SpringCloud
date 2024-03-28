package com.dongguo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Order80Application {

    public static void main(String[] args) {
        SpringApplication.run(Order80Application.class, args);
        System.out.println("http://127.0.0.1:80/swagger-ui.html");
    }
}
