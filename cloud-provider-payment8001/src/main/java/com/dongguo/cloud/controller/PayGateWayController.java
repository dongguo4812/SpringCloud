package com.dongguo.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.dongguo.cloud.entity.PO.Pay;
import com.dongguo.cloud.resp.Result;
import com.dongguo.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayGateWayController {
    @Resource
    private PayService payService;

    @GetMapping(value = "/pay/gateway/get/{id}")
    public Result<Pay> getPayById(@PathVariable("id") Integer id) {
        Pay pay = payService.getById(id);
        return Result.success(pay);
    }

    @GetMapping(value = "/pay/gateway/info")
    public Result<String> getGatewayInfo() {
        return Result.success("gateway：" + DateUtil.date());
    }
}