package com.dongguo.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.dongguo.cloud.resp.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Resilience4j CircuitBreaker 的例子
 */
@RestController
public class PayCircuitController {
    @GetMapping(value = "/pay/circuit/{id}")
    public Result myCircuit(@PathVariable("id") Integer id) {
        if (id < 0) throw new RuntimeException("----circuit id 不能负数");
        if (id >= 9999) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Result.success("Hello, circuit! inputId:  " + id + " \t " + DateUtil.date());
    }

    @GetMapping(value = "/pay/bulkhead/{id}")
    public Result myBulkhead(@PathVariable("id") Integer id) {
        if (id < 0) throw new RuntimeException("----bulkhead id 不能负数");
        if (id >= 9999) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Result.success("Hello, bulkhead! inputId:  " + id + " \t " + DateUtil.date());
    }

    @GetMapping(value = "/pay/bulkheadPool/{id}")
    public Result myBulkheadPool(@PathVariable("id") Integer id) {
        if (id < 0) throw new RuntimeException("----bulkheadPool id 不能负数");
        if (id >= 9999) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Result.success("Hello, bulkheadPool! inputId:  " + id + " \t " + DateUtil.date());
    }

    @GetMapping(value = "/pay/ratelimit/{id}")
    public Result myRatelimit(@PathVariable("id") Integer id) {
        return Result.success("Ratelimit inputId:  " + id + " \t " + DateUtil.date());
    }
}