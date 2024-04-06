package com.dongguo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.dongguo.cloud.mapper") //import tk.mybatis.spring.annotation.MapperScan;
public class AccountService2003Application {

    public static void main(String[] args) {
        SpringApplication.run(AccountService2003Application.class, args);
    }

}
