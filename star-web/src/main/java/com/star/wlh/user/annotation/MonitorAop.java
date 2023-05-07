package com.star.wlh.user.annotation;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class MonitorAop {
	private static final Logger logger = LoggerFactory.getLogger(MonitorAop.class);
	public static final ThreadFactory factoryMaster = ThreadFactoryBuilder.create().setNamePrefix("master_").build();
	public static final ThreadPoolExecutor poolMaster = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MICROSECONDS,
			new ArrayBlockingQueue<>(1024), factoryMaster, new ThreadPoolExecutor.AbortPolicy());

	@AfterReturning(value = "execution(public * *(..)) && @annotation(monitorAnnotation)")
	public void onMonitor( JoinPoint joinPoint,MonitorAnnotation monitorAnnotation){
		MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
		String[] parameterNames = methodSignature.getParameterNames();
		for (String parameterName : parameterNames) {
			logger.info("parameter {}" , parameterName);
		}
		poolMaster.execute(new Runnable() {
			@Override
			public void run() {

			}
		});
	}
}
