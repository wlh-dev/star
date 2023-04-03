package com.star.wlh.user.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.Nonnull;

// @Component
public class BeanPostProcessorConfig implements BeanPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(BeanPostProcessorConfig.class);
    public static Long start = 0L;

    @Override
    public Object postProcessBeforeInitialization(@Nonnull Object bean, @Nonnull String beanName) throws BeansException {
        start = System.currentTimeMillis();
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(@Nonnull Object bean, @Nonnull String beanName) throws BeansException {
        Long end = System.currentTimeMillis();
        logger.info("beanName:{} 的加载时间是:{} 毫秒", beanName, end - start);
        return bean;
    }

}
