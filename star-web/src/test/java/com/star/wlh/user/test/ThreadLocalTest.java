package com.star.wlh.user.test;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.star.wlh.common.utils.TimeUtils;
import com.star.wlh.user.BaseTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class ThreadLocalTest extends BaseTest {
	public static final ThreadFactory factory = ThreadFactoryBuilder.create().setNamePrefix("pool_").build();

	private static final Logger logger = LoggerFactory.getLogger(ThreadLocalTest.class);
	ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 0, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<>(10), factory,
			new ThreadPoolExecutor.AbortPolicy());
	@Test
	public void testThreadLocal() throws ExecutionException, InterruptedException {
		ThreadLocal<Map<String, String>> local = ThreadLocal.withInitial(HashMap::new);
		CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
				Map<String, String> map = local.get();
						logger.info("开始放置任何数据到当前线程的map");
					for (int i = 0; i < 200; i++) {
						map.put(i + "", i + "");
					}
					logger.info("添加结束，当前线程中的数据是：{}", map);
		}, pool);

		CompletableFuture<Void> future2 =CompletableFuture.runAsync(() -> {
			Map<String, String> map = local.get();
				logger.info("开始放置任何数据到当前线程的map");
			for (int i = 0; i < 200; i++) {
				map.put(i + "", i + "");
			}
			logger.info("添加结束，当前线程中的数据是：{}", map);
		},pool);

		TimeUtils.sleep(2000);
		Map<String, String> mainMap = local.get();
		logger.info("主线程中的数据：{}",mainMap);
		future1.get();
		future2.get();



	}

}
