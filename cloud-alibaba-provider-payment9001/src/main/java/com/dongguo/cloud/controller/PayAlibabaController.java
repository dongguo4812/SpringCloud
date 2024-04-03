package com.dongguo.cloud.controller;

import com.dongguo.cloud.resp.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayAlibabaController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/pay/nacos/{id}")
    public Result getPayInfo(@PathVariable("id") Integer id) {
        return Result.success("nacos registry, serverPort: " + serverPort + "\t id:" + id);
    }
}