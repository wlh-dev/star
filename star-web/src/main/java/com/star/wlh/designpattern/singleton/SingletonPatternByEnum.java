package com.star.wlh.designpattern.singleton;

public enum SingletonPatternByEnum {
    INSTANCE;
    public void doSomething(){
        System.out.println("执行方法");
    }
}
