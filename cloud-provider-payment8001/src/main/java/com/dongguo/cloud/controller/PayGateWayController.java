package com.dongguo.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.dongguo.cloud.entity.PO.Pay;
import com.dongguo.cloud.resp.Result;
import com.dongguo.cloud.service.PayService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

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

    @GetMapping(value = "/pay/gateway/filter")
    public Result<String> getGatewayFilter(HttpServletRequest request) {
        String result = "";
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headName = headers.nextElement();
            String headValue = request.getHeader(headName);
            System.out.println("请求头名: " + headName + "\t\t\t" + "请求头值: " + headValue);
            if (headName.equalsIgnoreCase("X-Request-name1")
                    || headName.equalsIgnoreCase("X-Request-name2")) {
                result = result + headName + "\t " + headValue + " ";
            }
        }
        System.out.println("=============================================");
        String name = request.getParameter("name");
        System.out.println("request Parameter name: "+name);

        String age = request.getParameter("age");
        System.out.println("request Parameter age: "+age);
        System.out.println("=============================================");
        return Result.success("GatewayFilter 过滤器： " + result + " \t " + DateUtil.now());
    }
}