package com.star.wlh.common.exception;

import java.lang.reflect.Field;

public class StarException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public StarException(String message) {
        super(message);
    }

    public StarException(String message, Throwable cause) {
        super(message, cause);
    }

    public void setMessage(String message) {
        // 通过反射来实现
        try {
            Field filed = Throwable.class.getDeclaredField("detailMessage");
            if (filed != null) {
                filed.setAccessible(true);
                filed.set(this, message);
            }
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            throw new RuntimeException("Failed to set message: " + message, e); // NOSONAR 这里无法再抛出自定义异常
        }
    }
}
