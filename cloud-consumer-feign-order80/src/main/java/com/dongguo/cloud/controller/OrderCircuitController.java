package com.dongguo.cloud.controller;

import com.dongguo.cloud.feign.PayFeignApi;
import com.dongguo.cloud.resp.Result;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

import static com.dongguo.cloud.resp.ReturnCodeEnum.RC201;

@RestController
@Tag(name = "订单断路器模块", description = "订单控制器接口")
public class OrderCircuitController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    @Operation(summary = "测试断路器功能")
    public Result myCircuitBreaker(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }

    /**
     * myCircuitFallback就是服务降级后的兜底处理方法
     *
     * @param id
     * @param t
     * @return
     */
    public Result myCircuitFallback(Integer id, Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return Result.fail(RC201.getCode(), RC201.getMessage());
    }


    /**
     * 舱壁隔离
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadFallback", type = Bulkhead.Type.SEMAPHORE)
    @Operation(summary = "测试舱壁隔离功能")
    public Result myBulkhead(@PathVariable("id") Integer id) {
        return payFeignApi.myBulkhead(id);
    }

    public Result myBulkheadFallback(Throwable t) {
        return Result.fail(RC201.getCode(), "Bulkhead.Type.SEMAPHORE" + RC201.getMessage());
    }

    /**
     * 舱壁隔离,THREADPOOL
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/feign/pay/bulkheadPool/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadPoolFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<Result> myBulkheadPool(@PathVariable("id") Integer id) {

        return CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getId() + "执行 id  ：" + id);
                    return payFeignApi.myBulkheadPool(id);
                }

        );
    }

    public CompletableFuture<Result> myBulkheadPoolFallback(Integer id, Throwable t) {
        return CompletableFuture.supplyAsync(() -> Result.fail(RC201.getCode(), "Bulkhead.Type.THREADPOOL" + RC201.getMessage()));
    }
}