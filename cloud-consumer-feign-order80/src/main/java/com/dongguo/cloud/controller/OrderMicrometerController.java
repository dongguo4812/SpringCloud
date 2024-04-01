package com.dongguo.cloud.controller;


import com.dongguo.cloud.feign.PayFeignApi;
import com.dongguo.cloud.resp.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Micrometer 替代 Sleuth
 */
@RestController
@Tag(name = "订单链路追踪模块", description = "订单控制器接口")
public class OrderMicrometerController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/feign/micrometer/{id}")
    @Operation(summary = "链路追踪")
    public Result myMicrometer(@PathVariable("id") Integer id) {
        return payFeignApi.myMicrometer(id);
    }
}
