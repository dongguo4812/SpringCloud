package com.dongguo.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 统一返回对象类
 * @param <T>
 */
@Data
@Accessors(chain = true)
public class Result<T> {

    private String code;
    /**
     * 结果状态 ,具体状态码参见枚举类ReturnCodeEnum.java
     */
    private String message;
    private T data;
    private long timestamp;


    public Result() {
        this.timestamp = System.currentTimeMillis();
    }
    public static <T> Result<T> success() {
        return success(null);
    }
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ReturnCodeEnum.RC200.getCode());
        result.setMessage(ReturnCodeEnum.RC200.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(String code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}