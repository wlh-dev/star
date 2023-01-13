package com.star.wlh.threadpool;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.star.wlh.common.utils.CommonTools;

import java.util.concurrent.*;

public class _01_SupplyAsync {
    static ThreadFactory threadFactory = ThreadFactoryBuilder.create().setNamePrefix("线程名:").build();
    static ThreadPoolExecutor pool = new ThreadPoolExecutor(
            10, 20, 0, TimeUnit.MICROSECONDS,
            new ArrayBlockingQueue<>(100),
            threadFactory,
            new ThreadPoolExecutor.AbortPolicy()
    );

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CommonTools.printTimeAndCurrentThread("小白进入餐厅");
        CommonTools.printTimeAndCurrentThread("小白开始点餐");
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            CommonTools.printTimeAndCurrentThread("厨师开始做番茄炒蛋");
            CommonTools.sleep(1000L);
            return "番茄炒蛋";
        }, pool).thenCompose(disk -> CompletableFuture.supplyAsync(() -> {
            CommonTools.printTimeAndCurrentThread("服务员打饭");
            CommonTools.sleep(1000L);
            int i = 10 / 0;
            return "服务员端上米饭+" + disk;
        }, pool)).exceptionally((e) -> "发生错误了");
        CommonTools.printTimeAndCurrentThread(result.get());
    }


}

