package com.star.wlh.designpattern.responsibility;

import lombok.Setter;

public abstract class LoggerHandler {
    /**
     * 日志级别 1
     */
    public static final  int INFO = 1;
    /**
     * 日志级别 2
     */
    public static final int DEBUG = 2;
    /**
     * 日志级别 3
     */
    public static final int ERROR = 3;

    protected  int level;

    // 责任链中的下一个元素
    @Setter
    protected LoggerHandler nextLoggerHandler;

    public void logMessage(int level,String message){
        if (this.level<=level){
            write(message);
        }
        if (nextLoggerHandler!=null){
            nextLoggerHandler.logMessage(level,message);
        }
    }

    protected abstract void write(String message);
}
