package com.star.wlh.common.utils;

import java.util.concurrent.ThreadFactory;

/**
 * @author wlh
 * @date 2022/09/3 下午12:38
 * @desc 创建线程池的工厂对象
 */
public class ThreadFactoryUtils implements ThreadFactory {

	/**
	 * 该方法用来创建线程
	 *
	 * @param runnable runnable
	 * @return Thread
	 */
	@Override public Thread newThread(Runnable runnable) {
		return new Thread(runnable);
	}
}
