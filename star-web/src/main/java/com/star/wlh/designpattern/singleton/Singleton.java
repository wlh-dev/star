package com.star.wlh.designpattern.singleton;

import org.apache.commons.math3.analysis.function.Sin;

/**
 *
 */
public class Singleton {


    private static Singleton getInstance() {
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
