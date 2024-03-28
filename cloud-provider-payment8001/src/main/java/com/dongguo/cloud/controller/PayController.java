package com.dongguo.cloud.controller;

import cn.hutool.core.bean.BeanUtil;
import com.dongguo.cloud.entity.DTO.PayDTO;
import com.dongguo.cloud.entity.PO.Pay;
import com.dongguo.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pay")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping(value = "/add")
    public String addPay(@RequestBody Pay pay) {
        int i = payService.add(pay);
        return "成功插入记录，返回值：" + i;
    }

    @DeleteMapping(value = "/del/{id}")
    public Integer deletePay(@PathVariable("id") Integer id) {
        return payService.delete(id);
    }

    @PutMapping(value = "/update")
    public String updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = BeanUtil.toBean(payDTO, Pay.class);
        int i = payService.update(pay);
        return "成功修改记录，返回值：" + i;
    }

    @GetMapping(value = "/get/{id}")
    public Pay getById(@PathVariable("id") Integer id) {
        return payService.getById(id);
    }

    @GetMapping(value = "/getAll")
    public List<Pay> getAll() {
        return payService.getAll();
    }
}