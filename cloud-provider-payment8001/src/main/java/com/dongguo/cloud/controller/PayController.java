package com.dongguo.cloud.controller;

import cn.hutool.core.bean.BeanUtil;
import com.dongguo.cloud.entity.DTO.PayDTO;
import com.dongguo.cloud.entity.PO.Pay;
import com.dongguo.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public String addPay(@RequestBody Pay pay) {
        int i = payService.add(pay);
        return "成功插入记录，返回值：" + i;
    }

    @DeleteMapping(value = "/del/{id}")
    @Operation(
            summary = "删除",
            description = "删除支付流水"
    )
    public Integer deletePay(@PathVariable("id") Integer id) {
        return payService.delete(id);
    }

    @PutMapping(value = "/update")
    @Operation(
            summary = "修改",
            description = "修改支付流水"
    )
    public String updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = BeanUtil.toBean(payDTO, Pay.class);
        int i = payService.update(pay);
        return "成功修改记录，返回值：" + i;
    }

    @GetMapping(value = "/get/{id}")
    @Operation(
            summary = "按照ID查流水",
            description = "查询支付流水"
    )
    public Pay getById(@PathVariable("id") Integer id) {
        return payService.getById(id);
    }

    @GetMapping(value = "/getAll")
    @Operation(
            summary = "查询流水",
            description = "查询所有支付流水"
    )
    public List<Pay> getAll() {
        return payService.getAll();
    }
}