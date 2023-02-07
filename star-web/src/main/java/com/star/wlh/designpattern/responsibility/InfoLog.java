package com.star.wlh.designpattern.responsibility;

public class InfoLog extends LoggerHandler{
    public InfoLog(int level) {
        this.level = level;
    }
    @Override
    protected void write(String message) {
        System.out.println("打印日志---->"+message);
    }
}
