package com.dongguo.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.dongguo.cloud.resp.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayMicrometerController {
    /**
     * Micrometer(Sleuth)进行链路监控的例子
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    public Result myMicrometer(@PathVariable("id") Integer id) {
        return Result.success("Micrometer inputId:  " + id + " \t    服务返回:" + DateUtil.date());
    }
}
