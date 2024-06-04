package com.fufufu.exception;

import com.fufufu.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public <T> Result<T> handleException(Exception e){
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage())? e.getMessage() : "操作失败");
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseBody
    public <T> Result<T> handleTokenExpiredException(TokenExpiredException e) {
        // 打印异常信息或记录到日志中，根据实际需求来决定
        e.printStackTrace();
        // 返回一个自定义的错误响应，告知客户端token已经过期
        return Result.error("Token已过期，请重新登录");
    }
}
