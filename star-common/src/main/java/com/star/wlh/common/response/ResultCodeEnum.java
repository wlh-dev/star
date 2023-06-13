package com.star.wlh.common.response;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 *
 */
@Getter
public enum ResultCodeEnum {
    /**
     * 成功
     */
    SUCCESS(2000,"成功"),
    /**
     * 失败
     */
    FAIL(5001, "失败"),
    /**
     * 服务端异常
     */
    SERVICE_ERROR(5000, "服务异常"),
    DATA_ERROR(2004, "数据异常"),
    ILLEGAL_REQUEST(2005, "非法请求"),
    REPEAT_SUBMIT(2006, "重复提交"),
    ARGUMENT_VALID_ERROR(2010, "参数校验异常"),

    LOGIN_AUTH(2008, "未登陆"),
    PERMISSION(2009, "没有权限"),
    ACCOUNT_ERROR(2014, "账号不正确"),
    PASSWORD_ERROR(2015, "密码不正确"),
    LOGIN_MOBLE_ERROR( 2016, "账号不正确"),
    ACCOUNT_STOP( 2017, "账号已停用"),
    NODE_ERROR( 2018, "该节点下有子节点，不可以删除"),
    /**
     * 方法未实现
     */
    INTERFACE_NOT_IMPLEMENTS(7000,"实现类未实现该方法")
    ;

    private Integer code;

    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
