package com.dongguo.cloud.config;

import feign.RetryableException;
import feign.Retryer;

import java.time.LocalDateTime;

/**
 * 自定义超时重试类
 */
public class CustomRetryer implements Retryer {
    private final int maxAttempts;  // 最大尝试次数
    private final long backoff;  // 重试间隔时间
    int attempt; // 当前重试次数

    public CustomRetryer() {
        this.maxAttempts = 3;
        this.backoff = 1000L;
        this.attempt = 0; //这里默认为0
    }

    @Override
    public void continueOrPropagate(RetryableException e) {
        if (attempt++ >= maxAttempts) {
            throw e;
        }
        long interval = this.backoff * attempt;// 指数增长
        System.out.println(LocalDateTime.now() + " | 执行第" + attempt + "次重试, 重试间隔时间：" + interval);
        try {
            Thread.sleep(interval);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Retryer clone() {
        return new CustomRetryer();
    }
}