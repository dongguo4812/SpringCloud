package com.dongguo.cloud.controller;


import com.dongguo.cloud.entity.Order;
import com.dongguo.cloud.resp.Result;
import com.dongguo.cloud.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "订单", description = "订单控制器接口")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/order/create")
    @Operation(summary = "创建订单")
    public Result create(Order order) {
        orderService.create(order);
        return Result.success(order);
    }
}