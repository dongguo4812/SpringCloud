package com.dongguo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Order80Application {

    public static void main(String[] args) {
        SpringApplication.run(Order80Application.class, args);
        System.out.println("http://127.0.0.1:80/swagger-ui.html");
    }
}
