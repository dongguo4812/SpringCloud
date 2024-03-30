package com.dongguo.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public Retryer retryer() {
        return Retryer.NEVER_RETRY; //Feign不进行重试的实现

        /**
         * period 初始间隔时间为100毫秒
         * maxPeriod 重试间最大间隔时间为1000毫秒（即1秒）
         * maxAttempts 最大请求次数为3
         * 这里的“最大请求次数为3”是指，在发生错误时，Feign发起请求的次数。
         * 第一次请求失败后，会等待100毫秒后重试，如果第二次请求仍然失败，它会等待更长的时间（但不会超过1000毫秒）再次重试，总共尝试3次。
         *
         * 在发生请求失败时，Feign会进行以下尝试：
         * 原始请求（计入重试次数）
         * 第一次重试（等待至少100毫秒）
         * 第二次重试（等待时间根据指数退避策略计算，但不会超过1000毫秒）
         */
//        return new Retryer.Default(100, 1, 3);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
