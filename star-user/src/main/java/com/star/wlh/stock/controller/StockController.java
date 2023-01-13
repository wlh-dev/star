package com.star.wlh.stock.controller;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.star.wlh.stock.service.StockService;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("stock")
public class StockController {
    private static ThreadFactory factory = ThreadFactoryBuilder.create().setNamePrefix("stock").build();
    private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MICROSECONDS,
            new ArrayBlockingQueue<>(1024), factory, new ThreadPoolExecutor.AbortPolicy());
    @Autowired
    private StockService  stockService;
    @RequestMapping("multiply")
    public void multiplyTest() {
        for (int i = 0; i < 10; i++) {
            poolExecutor.execute(() -> stockService.sell());
        }
    }
}
