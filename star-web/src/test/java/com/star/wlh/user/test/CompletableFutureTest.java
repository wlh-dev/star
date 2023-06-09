package com.star.wlh.user.test;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.star.wlh.common.utils.CommonTools;
import com.star.wlh.common.utils.TimeUtils;
import com.star.wlh.user.BaseTest;
import com.star.wlh.user.entity.UserEntity;
import com.star.wlh.user.mapper.UserMapper;
import lombok.SneakyThrows;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.Beans;
import java.util.List;
import java.util.concurrent.*;

public class CompletableFutureTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(CompletableFutureTest.class);

    public static final ThreadFactory factoryMaster = ThreadFactoryBuilder.create().setNamePrefix("Master_").build();
    public static final ThreadFactory factorySlaver = ThreadFactoryBuilder.create().setNamePrefix("Slaver_").build();
    public static final ThreadPoolExecutor poolMaster = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MICROSECONDS,
            new ArrayBlockingQueue<>(1024), factoryMaster, new ThreadPoolExecutor.AbortPolicy());
    public static final ThreadPoolExecutor poolSlaver = new ThreadPoolExecutor(10, 30, 0, TimeUnit.MICROSECONDS,
            new ArrayBlockingQueue<>(1024), factorySlaver, new ThreadPoolExecutor.AbortPolicy());
    @Autowired
    private UserMapper userMapper;

    /**
     *
     */
    @DisplayName("SupplyAsync测试")
    @Test
    public void completableSupplyAsyncTest() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            CommonTools.printTimeAndCurrentThread("执行第1步");
            UserEntity userEntity = userMapper.selectById("001");
            return userEntity.getUsername();
        }, poolMaster).thenApplyAsync((userName) -> {
            CommonTools.printTimeAndCurrentThread("执行第2步");
            UserEntity userEntity = userMapper.selectById("001");
            return userName + "_" + userEntity.getUsername();
        }, poolMaster).thenApplyAsync((userName) -> {
            CommonTools.printTimeAndCurrentThread("执行第3步");
            UserEntity userEntity = userMapper.selectById("001");
            return userName + "_" + userEntity.getUsername();
        }, poolMaster);
        String result;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        CommonTools.printTimeAndCurrentThread(result);
    }

    @DisplayName("thenCombine测试")
    @Test
    public void completableThenCombine() {
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            TimeUtils.sleep(1000);
            return 10;
        }, poolMaster);
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            TimeUtils.sleep(2000);
            return 20;
        }, poolMaster);
        CompletableFuture<Integer> completableFuture3 = completableFuture1.thenCombineAsync(completableFuture2, (result1, result2) -> {
            return result1 * result2;
        }, poolMaster);
        CompletableFuture<Integer> completableFuture4 = completableFuture3.thenCombineAsync(completableFuture1, (result1, result2) -> {
            return result1 * result2;
        }, poolMaster);
        Integer join = completableFuture4.join();
        assert join == 2000 : "数据计算失败";

    }

    @DisplayName("RunAsync测试")
    @Test
    public void completableRunAsyncTest() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            CommonTools.printTimeAndCurrentThread("执行第1步");
            UserEntity userEntity = new UserEntity();
            userEntity.setId("001");
            //  UserEntity entity = userMapper.findById(userEntity);
            //  CommonTools.printTimeAndCurrentThread(entity.getRealName());
        }, poolMaster).thenRunAsync(() -> {
            CommonTools.printTimeAndCurrentThread("执行第2步");
            UserEntity userEntity = new UserEntity();
            userEntity.setId("002");
            // UserEntity entity = userMapper.findById(userEntity);
            //  CommonTools.printTimeAndCurrentThread(entity.getRealName());
        }, poolMaster).thenRunAsync(() -> {
            CommonTools.printTimeAndCurrentThread("执行第3步");
            UserEntity userEntity = new UserEntity();
            userEntity.setId("003");
            // UserEntity entity = userMapper.findById(userEntity);
            //  CommonTools.printTimeAndCurrentThread(entity.getRealName());
        }, poolMaster);
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("comprehensive test")
    @Test
    public void anyOfFutureWithRunTest() {
        //
        CompletableFuture<Void> firstFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("第一个任务");
        }, poolMaster);
        CompletableFuture<Void> secondFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("第二个任务");
        }, poolSlaver);
        CompletableFuture<Void> thirdFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("第三个任务");
        }, poolSlaver);
        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(firstFuture, secondFuture, thirdFuture);
        anyOfFuture.whenCompleteAsync((result, throwable) -> {
            CommonTools.printTimeAndCurrentThread("上个任务结果：" + result);
            CommonTools.printTimeAndCurrentThread("上个任务抛出异常：" + throwable);
        }, poolSlaver).exceptionally((throwable) -> "error");
        try {
            anyOfFuture.get();
            // CommonTools.printTimeAndCurrentThread(result.toString());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void anyOfFutureWithSupplyTest() {
        //
        CompletableFuture<Integer> firstFuture = CompletableFuture.supplyAsync(() -> {
            CommonTools.printTimeAndCurrentThread("第一个任务");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 200;
        }, poolMaster);
        CompletableFuture<Integer> secondFuture = CompletableFuture.supplyAsync(() -> {
            CommonTools.printTimeAndCurrentThread("第二个任务");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 300;
        }, poolSlaver);
        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(firstFuture, secondFuture);
        Object result;
        try {
            result = anyOfFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        CommonTools.printTimeAndCurrentThread(result.toString());
    }

    @Test
    public void test() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("第一个任务开始执行");
            TimeUtils.sleep(1000);
            System.out.println("第一个任务执行完成");
        }, poolSlaver);
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("第2个任务开始执行");
            TimeUtils.sleep(3000);
            System.out.println("第2个任务执行完成");
        }, poolSlaver);
        CompletableFuture<Void> future = CompletableFuture.allOf(future1, future2).whenCompleteAsync((unused, throwable) -> System.out.println("whenCompleteAsync中的throwable:" + throwable), poolSlaver).exceptionally((throwable) -> {
            System.out.println("exceptionally中的throwable:" + throwable);
            return null;
        });
        future.get();
    }

    @Test
    @DisplayName("测试")
    public void testFutureTask() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("come in here");
                return "hello Callable";
            }
        });
        Thread thread = new Thread(futureTask, "futureTask");
        thread.start();
        logger.info("task started,{}", futureTask.get());
        boolean done = futureTask.isDone();
    }

}
