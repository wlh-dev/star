package com.star.wlh.designpattern.singleton;

import com.star.wlh.common.utils.ThreadPoolUtils;

import java.util.concurrent.ThreadPoolExecutor;

public class SingletonPatternMain {
    public static void main(String[] args) {
        ThreadPoolUtils.poolSlaver.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 10; i++) {
            ThreadPoolUtils.poolMaster.execute(() -> {
                SingletonPatternByEnum instance =  SingletonPatternByEnum.INSTANCE;
                System.out.println(instance);
                instance.doSomething();
            });
        }
        int max = Math.max(1, 2);
    }
}
