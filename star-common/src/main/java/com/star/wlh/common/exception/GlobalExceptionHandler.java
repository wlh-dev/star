package com.star.wlh.common.exception;

import com.star.wlh.common.response.ResponseResult;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 统一全局异常
 * @Date 2023/6/6 22:30
 * @Auther by wlh
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 最终异常
     *
     * @param exception 最终异常
     * @return Result.fail(e.getMessage ());
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult<String> exceptionHandler(Exception exception) {
        return ResponseResult.fail(exception.getMessage());
    }

    /**
     * 请求参数绑定到java bean上失败时抛出
     *
     * @param exception 绑定Bean对象异常
     * @return Result.fail(e.getMessage ());
     */
    @ExceptionHandler(BindException.class)
    public ResponseResult<String> bindExceptionHandler(BindException exception) {
        return ResponseResult.fail(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 空指针异常
     *
     * @param exception 空指针异常
     * @return Result.fail(e.getMessage ());
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseResult<String> nullPointExceptionHandler(NullPointerException exception) {
        return ResponseResult.fail(exception.getMessage());
    }
    @ExceptionHandler
    public ResponseResult<String> licenseExceptionHandler(LicenseException exception){
        return ResponseResult.fail(exception.getMessage());
    }
}
