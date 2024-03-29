package com.dongguo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient //该注解用于向注册中心注册服务
@EnableFeignClients//启用feign客户端,定义服务+绑定接口，以声明式的方法优雅而简单的实现服务调用
public class FeignOrder80Application {

    public static void main(String[] args) {
        SpringApplication.run(FeignOrder80Application.class, args);
        System.out.println("http://127.0.0.1:80/swagger-ui.html");
    }

}
