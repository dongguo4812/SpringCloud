package com.dongguo.cloud.controller;

import com.dongguo.cloud.feign.PayFeignApi;
import com.dongguo.cloud.resp.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderGateWayController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/feign/pay/gateway/get/{id}")
    public Result getPayById(@PathVariable("id") Integer id) {
        return payFeignApi.getPayById(id);
    }

    @GetMapping(value = "/feign/pay/gateway/info")
    public Result getGatewayInfo() {
        return payFeignApi.getGatewayInfo();
    }
}
