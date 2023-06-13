package com.star.wlh.designpattern.singleton;

import com.star.wlh.common.utils.ThreadPoolUtils;

import java.util.concurrent.ThreadPoolExecutor;

public class SingletonPatternMain {
    public static void main(String[] args) {
        ThreadPoolUtils.poolSlaver.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 10; i++) {
            ThreadPoolUtils.poolMaster.execute(() -> {
                Singleton instance = Singleton.SingletonEnum.SINGLETON.getInstance();
            });
        }
        int max = Math.max(1, 2);
    }
}
