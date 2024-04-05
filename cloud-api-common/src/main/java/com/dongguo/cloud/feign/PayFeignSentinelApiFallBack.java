package com.dongguo.cloud.feign;

import com.dongguo.cloud.resp.Result;
import org.springframework.stereotype.Component;
import static com.dongguo.cloud.resp.ReturnCodeEnum.RC500;

@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi {
    @Override
    public Result getPayByOrderNo(String orderNo) {
        return Result.fail(RC500.getCode(), "调用服务宕机或不可用，FallBack服务降级");
    }
}