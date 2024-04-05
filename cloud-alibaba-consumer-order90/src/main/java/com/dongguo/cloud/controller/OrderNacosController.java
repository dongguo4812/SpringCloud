package com.dongguo.cloud.controller;

import com.dongguo.cloud.feign.PayFeignSentinelApi;
import com.dongguo.cloud.resp.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class OrderNacosController {
    @Resource
    private WebClient.Builder webClientBuilder;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("/consumer/pay/nacos/{id}")
    public Result paymentInfo(@PathVariable("id") Integer id) {
        String url = serverURL + "/pay/nacos/" + id;
        return webClientBuilder.build().get().uri(url).retrieve().bodyToMono(Result.class).block();
    }

    @Resource
    private PayFeignSentinelApi payFeignSentinelApi;

    @GetMapping(value = "/consumer/pay/nacos/get/{orderNo}")
    public Result getPayByOrderNo(@PathVariable("orderNo") String orderNo) {
        return payFeignSentinelApi.getPayByOrderNo(orderNo);
    }
}