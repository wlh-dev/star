package com.star.wlh.common.initialization;

import com.google.common.base.Splitter;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class MyInitializer implements ApplicationContextInitializer<AnnotationConfigApplicationContext> {

    @Override
    public void initialize(AnnotationConfigApplicationContext applicationContext) {

    }

    @Bean
    public void myInitializerTest() {
        Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();
    }

}
