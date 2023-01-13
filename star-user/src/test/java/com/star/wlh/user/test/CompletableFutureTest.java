package com.star.wlh.user.test;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.star.wlh.common.utils.CommonTools;
import com.star.wlh.user.BaseTest;
import com.star.wlh.user.entity.UserEntity;
import com.star.wlh.user.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.*;

public class CompletableFutureTest extends BaseTest {
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
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId("001");
            UserEntity entity = userMapper.findById(userEntity);
            return entity.getUserName();
        }, poolMaster).thenApplyAsync((userName) -> {
            CommonTools.printTimeAndCurrentThread("执行第2步");
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId("002");
            UserEntity entity = userMapper.findById(userEntity);
            return userName + "_" + entity.getUserName();
        }, poolMaster).thenApplyAsync((userName) -> {
            CommonTools.printTimeAndCurrentThread("执行第3步");
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId("003");
            UserEntity entity = userMapper.findById(userEntity);
            return userName + "_" + entity.getUserName();
        }, poolMaster);
        String result;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        CommonTools.printTimeAndCurrentThread(result);

    }

    @DisplayName("RunAsync测试")
    @Test
    public void completableRunAsyncTest() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            CommonTools.printTimeAndCurrentThread("执行第1步");
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId("001");
            UserEntity entity = userMapper.findById(userEntity);
            CommonTools.printTimeAndCurrentThread(entity.getUserName());
        }, poolMaster).thenRunAsync(() -> {
            CommonTools.printTimeAndCurrentThread("执行第2步");
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId("002");
            UserEntity entity = userMapper.findById(userEntity);
            CommonTools.printTimeAndCurrentThread(entity.getUserName());
        }, poolMaster).thenRunAsync(() -> {
            CommonTools.printTimeAndCurrentThread("执行第3步");
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId("003");
            UserEntity entity = userMapper.findById(userEntity);
            CommonTools.printTimeAndCurrentThread(entity.getUserName());
        }, poolMaster);
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("comprehensive test")
    @Test
    public void anyOfFutureWithRunTest()  {
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
        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(firstFuture, secondFuture,thirdFuture);
        anyOfFuture.whenCompleteAsync((result,throwable)->{
            CommonTools.printTimeAndCurrentThread("上个任务结果：" + result);
            CommonTools.printTimeAndCurrentThread("上个任务抛出异常：" + throwable);
        },poolSlaver).exceptionally((throwable)-> "error");
        try {
            anyOfFuture.get();
            // CommonTools.printTimeAndCurrentThread(result.toString());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void anyOfFutureWithSupplyTest()  {
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
}
