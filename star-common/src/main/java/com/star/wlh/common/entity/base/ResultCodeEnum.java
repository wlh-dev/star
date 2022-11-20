package com.star.wlh.common.entity.base;

public enum ResultCodeEnum {

    /**
     * 请求成功
     */
    SUCCESS(200,"请求成功"),
    /**
     * 系统错误请求失败
     */
    FAILURE(500,"系统错误");
    private int code;
    private String name;

    ResultCodeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
