package com.star.wlh.user.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MonitorAop {
	private static final Logger logger = LoggerFactory.getLogger(MonitorAop.class);

	@Before(value = "execution(public * *(..)) && @annotation(monitorAnnotation)")
	public void onMonitor( JoinPoint joinPoint,MonitorAnnotation monitorAnnotation){
		logger.info("进入切面，获取到有该注解的类");
		MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
		String[] parameterNames = methodSignature.getParameterNames();
		for (String parameterName : parameterNames) {
			logger.info("parameter {}" , parameterName);
		}
		Object[] args = joinPoint.getArgs();
		logger.info("参数：{}",args);
		Object[] args1 = joinPoint.getArgs();
	}
}
