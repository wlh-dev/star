package com.star.wlh.exception;

import com.star.wlh.user.config.Result;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Classname GlobalExceptionHandler
 * @Description TODO
 * @Date 2023/6/6 22:30
 * @Created by wlh
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandler(Exception e) {
        return Result.fail(e.getMessage());
    }
    @ExceptionHandler(BindException.class)
    public Result<String> bindExceptionHandler(BindException e) {
        return Result.fail(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}
