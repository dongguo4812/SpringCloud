package com.dongguo.cloud.StatusGatewayFilterFactory;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Component  //标注不可忘
public class UserTypeRoutePredicateFactory extends AbstractRoutePredicateFactory<UserTypeRoutePredicateFactory.Config> {

    /**
     * 空参构造方法，内部调用super
     */
    public UserTypeRoutePredicateFactory() {
        super(UserTypeRoutePredicateFactory.Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(UserTypeRoutePredicateFactory.Config config) {
        return serverWebExchange -> {
            //检查request的参数里面，userType是否为指定的值，符合配置就通过
            String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");

            if (userType == null) return false;

            //如果说参数存在，就和config的数据进行比较
            return userType.equals(config.getUserType());
        };
    }

    /**
     * apply方法所需要的静态内部类MyRoutePredicateFactory.Config
     */
    @Validated
    public static class Config {
        @Setter
        @Getter
        @NotEmpty
        private String userType; //用户类型
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }
}