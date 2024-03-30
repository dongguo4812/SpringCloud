package com.dongguo.cloud.controller;

import cn.hutool.core.bean.BeanUtil;
import com.dongguo.cloud.entity.DTO.PayDTO;
import com.dongguo.cloud.entity.PO.Pay;
import com.dongguo.cloud.resp.Result;
import com.dongguo.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/pay")
@Tag(
        name = "支付模块",
        description = "支付控制器接口")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping(value = "/add")
    @Operation(
            summary = "新增",
            description = "新增支付流水,json串做参数"
    )
    public Result addPay(@RequestBody Pay pay) {
        int result = payService.add(pay);
        return Result.success("成功插入记录，返回值：" + result);
    }

    @DeleteMapping(value = "/del/{id}")
    @Operation(
            summary = "删除",
            description = "删除支付流水"
    )
    public Result deletePay(@PathVariable("id") Integer id) {
        int result = payService.delete(id);
        return Result.success(result);
    }

    @PutMapping(value = "/update")
    @Operation(
            summary = "修改",
            description = "修改支付流水"
    )
    public Result updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = BeanUtil.toBean(payDTO, Pay.class);
        int result = payService.update(pay);
        return Result.success("成功修改记录，返回值：" + result);
    }

    @GetMapping(value = "/get/{id}")
    @Operation(
            summary = "按照ID查流水",
            description = "查询支付流水"
    )
    public Result getById(@PathVariable("id") Integer id) {
        Pay pay = payService.getById(id);
        //模拟超时场景
        try {
            System.out.println("调用时间-----:" + LocalDateTime.now());
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Result.success(pay);
    }

    @GetMapping(value = "/getAll")
    @Operation(
            summary = "查询流水",
            description = "查询所有支付流水"
    )
    public Result getAll() {
        List<Pay> payList = payService.getAll();
        return Result.success(payList);
    }

    @Value("${server.port}")
    private String port;
    @Value("${active.info}")
    private String info;
    @GetMapping(value = "/get/info")
    private Result getInfoByConsul() {
        return Result.success("info: " + info + "," + "port: " + port);
    }
}