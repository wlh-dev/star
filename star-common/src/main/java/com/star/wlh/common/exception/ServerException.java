package com.star.wlh.common.exception;

/**
 * 标识由于服务端自身原因而导致的异常。
 */
public class ServerException extends StarException {
    private static final long serialVersionUID = 1L;

    public ServerException() {
        super("");
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

}