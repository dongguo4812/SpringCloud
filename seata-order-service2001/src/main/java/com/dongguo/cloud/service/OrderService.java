package com.dongguo.cloud.service;


import com.dongguo.cloud.entity.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);

}