package com.dongguo.cloud.entity.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "支付交易-数据传输对象")
public class PayDTO implements Serializable
{
    @Schema(title = "id")
    private Integer id;
    //支付流水号
    @Schema(title = "支付流水号")
    private String payNo;
    //订单流水号
    @Schema(title = "订单流水号")
    private String orderNo;
    //用户账号ID
    @Schema(title = "用户账号ID")
    private Integer userId;
    //交易金额
    @Schema(title = "交易金额")
    private BigDecimal amount;
}