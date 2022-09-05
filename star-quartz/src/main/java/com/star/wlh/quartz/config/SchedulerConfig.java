package com.star.wlh.quartz.config;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * @author wlh
 */
@Configuration
public class SchedulerConfig {

    private final JobFactory jobFactory;
    @Autowired
    public SchedulerConfig(JobFactory jobFactory){
        this.jobFactory = jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/spring-quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        Properties object = propertiesFactoryBean.getObject();
        if (object != null) {
            factory.setQuartzProperties(object);
        }
        factory.setJobFactory(jobFactory);
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        factory.setWaitForJobsToCompleteOnShutdown(true);
        factory.setOverwriteExistingJobs(false);
        factory.setStartupDelay(10);
        return factory;
    }

    @Bean(name = "scheduler")
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }

}
