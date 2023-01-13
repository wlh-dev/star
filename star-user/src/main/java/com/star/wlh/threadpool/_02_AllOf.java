package com.star.wlh.threadpool;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.star.wlh.common.utils.CommonTools;

import java.util.concurrent.*;

public class _02_AllOf {
    static ThreadFactory threadFactory = ThreadFactoryBuilder.create().setNamePrefix("线程名:").build();
    static ThreadPoolExecutor pool = new ThreadPoolExecutor(
            10, 20, 0, TimeUnit.MICROSECONDS,
            new ArrayBlockingQueue<>(100),
            threadFactory,
            new ThreadPoolExecutor.AbortPolicy()
    );

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 只有都到了才开始
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            CommonTools.sleep(1000L);
            CommonTools.printTimeAndCurrentThread("小王到了");
            return "小王到了";
        }, pool);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            CommonTools.sleep(2000L);
            CommonTools.printTimeAndCurrentThread("小刘到了");
            return "小刘到了";
        }, pool);
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            CommonTools.sleep(3000L);
            CommonTools.printTimeAndCurrentThread("Boss到了");
            return "Boss到了";
        }, pool);

        CompletableFuture<String> res = CompletableFuture
                .allOf(future1, future2, future3)
                .thenApplyAsync((a) -> {
                    CommonTools.printTimeAndCurrentThread("完成异步任务获取返回值" + a);
                    return "都到了 准备开席";
                }, pool)
                .exceptionally((throwable) -> {
                    CommonTools.printTimeAndCurrentThread("产生异常信息" + throwable);
                    return "有人没到无法开席";
                });
        CommonTools.printTimeAndCurrentThread(res.get());
    }
}
