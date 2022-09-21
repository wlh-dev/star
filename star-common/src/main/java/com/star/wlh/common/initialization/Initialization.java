package com.star.wlh.common.initialization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 执行初始化任务的类
 *
 * @author : wlh
 * @date Date : 2022年09月21日 17:55
 */
@Order(value = 2) @Component public class Initialization implements CommandLineRunner, ApplicationContextAware {
	private static final ThreadPoolExecutor POOL = new ThreadPoolExecutor(2, 10, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
					Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
	/**
	 * 用于保存接口实现类名及对应的类
	 */
	private ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory.getLogger(Initialization.class);

	@Override public void setApplicationContext(@Nonnull ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override public void run(String... args) {
		Collection<InitializationRegister> initializationRegisterLinkedList = new LinkedList<>(
						this.applicationContext.getBeansOfType(InitializationRegister.class).values());
		logger.info("<---多线程遍历执行初始化任务--->");
		for (InitializationRegister initializationRegister : initializationRegisterLinkedList) {
			logger.info("initialization name :{} ", initializationRegister.getClass().getName());
			POOL.execute(initializationRegister::register);
		}
		logger.info("<---遍历执行初始化任务完毕--->");
	}
}
