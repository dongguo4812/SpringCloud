package com.dongguo.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.dongguo.cloud.entity.DTO.PayDTO;
import com.dongguo.cloud.resp.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static com.dongguo.cloud.resp.ReturnCodeEnum.RC500;

@RestController
public class PayAlibabaController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/pay/nacos/{id}")
    public Result getPayInfo(@PathVariable("id") Integer id) {
        return Result.success("nacos registry, serverPort: " + serverPort + "\t id:" + id);
    }

    @GetMapping("/pay/nacos/get/{orderNo}")
    @SentinelResource(value = "getPayByOrderNo", blockHandler = "handlerBlockHandler")
    public Result getPayByOrderNo(@PathVariable("orderNo") String orderNo) {
        //模拟从数据库查询出数据并赋值给DTO
        PayDTO payDTO = new PayDTO();

        payDTO.setId(1024);
        payDTO.setOrderNo(orderNo);
        payDTO.setAmount(BigDecimal.valueOf(9.9));
        payDTO.setPayNo("pay:" + IdUtil.fastUUID());
        payDTO.setUserId(1);
        return Result.success("查询返回值：" + payDTO);
    }

    public Result handlerBlockHandler(@PathVariable("orderNo") String orderNo, BlockException exception) {
        return Result.fail(RC500.getCode(), "getPayByOrderNo服务不可用，" +
                "触发sentinel流控配置规则");
    }

    //fallback服务降级方法纳入到Feign接口统一处理，全局一个
//    public Result myFallBack(@PathVariable("orderNo") String orderNo, Throwable throwable) {
//        return Result.fail(RC500.getCode(), "异常情况：" + throwable.getMessage());
//    }

}