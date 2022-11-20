package com.star.wlh.common.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : wlh
 * @date Date : 2022年09月22日 15:04
 */

public class ThreadPoolUtils {
	/**
	 * 系统可用计算资源
	 */
	private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

	/**
	 * 核心线程数
	 */
	private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));

	/**
	 * 最大线程数
	 */
	private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;

	/**
	 * 空闲线程存活时间
	 */
	private static final int KEEP_ALIVE_SECONDS = 30;

	/**
	 * 工作队列
	 */
	private static final BlockingQueue<Runnable> POOL_WORK_QUEUE = new LinkedBlockingQueue<>(128);

	/**
	 * 工厂模式
	 */
	private static final ThreadFactoryUtils MY_THREAD_FACTORY = new ThreadFactoryUtils();

	/**
	 * 饱和策略
	 */
	private static final RejectedExecutionHandler THREAD_REJECTED_EXECUTION_HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();

	/**
	 * 线程池对象
	 */
	private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = null;

	/**
	 * 声明式定义线程池工具类对象静态变量，在所有线程中同步
	 */
	private static volatile ThreadPoolUtils threadPoolUtils = null;

}
