package com.star.wlh.designpattern.responsibility;

public abstract class LoggerHandler {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected  int level;

    // 责任链中的下一个元素
    protected LoggerHandler nextLoggerHandler;

    public void setNextLoggerHandler(LoggerHandler nextLoggerHandler) {
        this.nextLoggerHandler = nextLoggerHandler;
    }

    public void logMessage(int level,String message){
        if (this.level<=level){
            write(message);
        }
        if (nextLoggerHandler!=null){
            nextLoggerHandler.logMessage(level,message);
        }
    }
    abstract protected void write(String message);
}
