package com.dongguo.cloud.feign;

import com.dongguo.cloud.entity.DTO.PayDTO;
import com.dongguo.cloud.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "cloud-payment-service")
public interface PayFeignApi {
    /**
     * 新增一条支付相关流水记录
     *
     * @param payDTO
     * @return
     */
    @PostMapping("/pay/add")
    Result addPay(@RequestBody PayDTO payDTO);

    /**
     * 按照主键记录查询支付流水信息
     *
     * @param id
     * @return
     */
    @GetMapping("/pay/get/{id}")
    Result getById(@PathVariable("id") Integer id);

    /**
     * 按照主键记录删除支付流水信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/pay/del/{id}")
    Result deletePay(@PathVariable("id") Integer id);

    /**
     * 修改支付流水
     * @param payDTO
     * @return
     */
    @PutMapping("/pay/update")
    Result updatePay(@RequestBody PayDTO payDTO);
    /**
     * openfeign天然支持负载均衡演示
     *
     * @return
     */
    @GetMapping(value = "/pay/get/info")
    Result getInfoByConsul();

    /**
     * Resilience4j CircuitBreaker 的例子
     */
    @GetMapping(value = "/pay/circuit/{id}")
    Result myCircuit(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/bulkhead/{id}")
    Result myBulkhead(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/bulkheadPool/{id}")
    Result myBulkheadPool(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/ratelimit/{id}")
    Result myRatelimit(@PathVariable("id") Integer id);
}