package com.dongguo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.dongguo.cloud.mapper") //import tk.mybatis.spring.annotation.MapperScan;
public class Payment8001Application {

	public static void main(String[] args) {
		SpringApplication.run(Payment8001Application.class, args);
		System.out.println("http://127.0.0.1:8001/swagger-ui.html");
	}

}
