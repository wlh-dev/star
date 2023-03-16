package com.star.wlh.user.test;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.star.wlh.common.utils.TimeUtils;
import com.star.wlh.user.BaseTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class ThreadPoolTest extends BaseTest {
	private static final Logger logger = LoggerFactory.getLogger(ThreadPoolTest.class);

	public static final ThreadFactory factory = ThreadFactoryBuilder.create().setNamePrefix("pool_").build();

	@Test public void abortPolicyTest() {
		try {
			ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 0, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<>(10), factory,
					new ThreadPoolExecutor.AbortPolicy());
			for (int i = 0; i < 15; i++) {
				pool.execute(() -> {
					String name = Thread.currentThread().getName();
					System.out.println(name + "执行任务");
					TimeUtils.sleep(3000);
				});

			}
		} catch (RejectedExecutionException e) {
			logger.error("数据报错,拒绝执行", e);
		}
		TimeUtils.sleep(10000);
	}

	@Test public void threadStartTest() {
		Thread thread = factory.newThread(new Runnable() {
			@Override public void run() {
				logger.info("开始执行方法");
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		});
		thread.start();
		thread.start();

	}

}
