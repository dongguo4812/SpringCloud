package com.dongguo.cloud.controller;


import com.dongguo.cloud.entity.Order;
import com.dongguo.cloud.resp.Result;
import com.dongguo.cloud.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/order/create")
    public Result create(Order order) {
        orderService.create(order);
        return Result.success(order);
    }
}