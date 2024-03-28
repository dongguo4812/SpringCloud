package com.dongguo.cloud.controller;

import com.dongguo.cloud.entity.DTO.PayDTO;
import com.dongguo.cloud.resp.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
@RequestMapping("/consumer/pay")
@Tag(
        name = "订单模块",
        description = "订单控制器接口")
public class OrderController {
    public static final String PAYMENT_SERVICE_URL = "http://localhost:8001";//先写死，硬编码
    @Autowired
    private WebClient webClient;

    /**
     * 一般情况下，通过浏览器的地址栏输入url，发送的只能是get请求
     * 我们底层调用的是post方法，模拟消费者发送get请求，客户端消费者
     * 参数可以不添加@RequestBody
     *
     * @param payDTO
     * @return
     */
    @PostMapping("/add")
    @Operation(
            summary = "新增",
            description = "新增支付流水,json串做参数"
    )
    public Result addOrder(@RequestBody PayDTO payDTO) {
        String url = PAYMENT_SERVICE_URL + "/pay/add";
        return webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(payDTO)
                .retrieve()
                .bodyToMono(Result.class)
                .block();
    }

    @GetMapping("/get/{id}")
    @Operation(
            summary = "按照ID查流水",
            description = "查询支付流水"
    )
    public Result getPayInfo(@PathVariable("id") Integer id) {
        String url = PAYMENT_SERVICE_URL + "/pay/get/" + id;
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Result.class)
                .block();
    }

    @GetMapping(value = "/del/{id}")
    @Operation(
            summary = "删除",
            description = "删除支付流水"
    )
    public Result deletePay(@PathVariable("id") Integer id) {
        String url = PAYMENT_SERVICE_URL + "/pay/del/" + id;
        return webClient.delete()
                .uri(url)
                .retrieve()
                .bodyToMono(Result.class)
                .block();
    }

    @PutMapping(value = "/update")
    @Operation(
            summary = "修改",
            description = "修改支付流水"
    )
    public Result updatePay(@RequestBody PayDTO payDTO) {
        String url = PAYMENT_SERVICE_URL + "/pay/update";
        return webClient.put()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON).bodyValue(payDTO)
                .retrieve()
                .bodyToMono(Result.class)
                .block();
    }
}