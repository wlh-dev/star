package com.star.wlh.designpattern.singleton;

import org.apache.commons.math3.analysis.function.Sin;

/**
 * 枚举类实现单例模式
 */
public class Singleton {

    public static Singleton getInstance() {
        return SingletonEnum.INSTANCE.getInstance();
    }

    private enum SingletonEnum {

        INSTANCE;
        private final Singleton instance;

        private Singleton getInstance() {
            return instance;
        }

        SingletonEnum() {
            instance = new Singleton();
        }
    }
}
