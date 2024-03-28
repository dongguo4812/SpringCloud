package com.dongguo.cloud.exception;


import com.dongguo.cloud.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.dongguo.cloud.resp.ReturnCodeEnum.RC500;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 默认全局异常处理。
     *
     * @param e the e
     * @return ResultData
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> exception(Exception e) {
        System.out.println("----come in GlobalExceptionHandler");
        log.error("全局异常信息exception:{}", e.getMessage(), e);
        return Result.fail(RC500.getCode(), e.getMessage());
    }
}