package com.star.wlh.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author : wlh
 * @date Date : 2022年09月01日 15:12
 */
@SpringBootApplication
@EnableScheduling
public class QuartzApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuartzApplication.class,args);
	}
}
