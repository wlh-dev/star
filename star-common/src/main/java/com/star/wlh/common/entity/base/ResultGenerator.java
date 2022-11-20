package com.star.wlh.common.entity.base;

import cn.hutool.http.HttpStatus;

public class ResultGenerator {
    /**  --------------------------success------------------------------        */
    public static <T> Result<T> success() {
        return success(ResultCodeEnum.SUCCESS);
    }

    public static <T> Result<T> data(T data) {
        return success("success",data);
    }

    public static <T> Result<T> success(String message) {
        return success(message, null);
    }
    public static <T> Result<T> success(ResultCodeEnum resultCodeEnum) {
        return success(resultCodeEnum.getCode(), resultCodeEnum.getName());
    }

    public static <T> Result<T> success(String message, T data) {
        return success(HttpStatus.HTTP_OK,message, data);
    }

    public static <T> Result<T> success(int code, String message) {
        return success(code, message, null);
    }

    public static <T> Result<T> success(int code, String message, T data) {
        return new Result<T>(code, true, message, data);
    }

    /**  --------------------------failure------------------------------        */

    public static <T> Result<T> failure() {
        return failure(ResultCodeEnum.FAILURE);
    }

    public static <T> Result<T> failure(T data) {
        return failure("failure",data);
    }

    public static <T> Result<T> failure(String message) {
        return failure(message, null);
    }
    public static <T> Result<T> failure(ResultCodeEnum resultCodeEnum) {
        return failure(resultCodeEnum.getCode(), resultCodeEnum.getName());
    }

    public static <T> Result<T> failure(String message, T data) {
        return failure(500,message, data);
    }

    public static <T> Result<T> failure(int code, String message) {
        return failure(code, message, null);
    }

    public static <T> Result<T> failure(int code, String message, T data) {
        return new Result<T>(code, false, message, data);
    }
}
