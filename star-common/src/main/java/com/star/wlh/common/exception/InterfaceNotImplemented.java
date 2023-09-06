package com.star.wlh.common.exception;

/**
 * @Classname InterfaceNotImplemented
 * @Description 
 * @Date 2023/6/9 20:49
 * @Created by wlh
 */
public class InterfaceNotImplemented extends RuntimeException{
    private String code;
    private String message;

    private static final long serialVersionUID = 1L;

    public InterfaceNotImplemented() {
        super("");
    }

    public InterfaceNotImplemented(String message, Throwable cause) {
        super(message, cause);
    }

}
