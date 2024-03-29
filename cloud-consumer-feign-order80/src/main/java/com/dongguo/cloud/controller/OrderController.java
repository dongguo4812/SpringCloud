package com.dongguo.cloud.controller;

import com.dongguo.cloud.entity.DTO.PayDTO;
import com.dongguo.cloud.feign.PayFeignApi;
import com.dongguo.cloud.resp.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "订单模块", description = "订单控制器接口")
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/feign/pay/add")
    @Operation(summary = "新增", description = "新增支付流水,json串做参数")
    public Result addOrder(@RequestBody PayDTO payDTO) {
        System.out.println("第一步：模拟本地addOrder新增订单成功(省略sql操作)，第二步：再开启addPay支付微服务远程调用");
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping("/feign/pay/get/{id}")
    @Operation(summary = "按照ID查流水", description = "查询支付流水")
    public Result getPayInfo(@PathVariable("id") Integer id) {
        System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");
        return payFeignApi.getById(id);
    }

    @DeleteMapping("/feign/pay/del/{id}")
    @Operation(summary = "删除", description = "删除支付流水")
    public Result deletePay(@PathVariable("id") Integer id) {
        return payFeignApi.getById(id);
    }

    @PutMapping("/feign/pay/update")
    @Operation(summary = "修改", description = "修改支付流水")
    public Result updatePay(@RequestBody PayDTO payDTO) {
        return payFeignApi.updatePay(payDTO);
    }

    /**
     * openfeign天然支持负载均衡演示
     *
     * @return
     */
    @GetMapping(value = "/feign/pay/get/info")
    @Operation(summary = "验证负载均衡")
    public Result getInfoByConsul() {
        return payFeignApi.getInfoByConsul();
    }
}