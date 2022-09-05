package com.star.wlh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(QuartzApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(QuartzApplication.class,args);
		logger.info("---------------------------------------------------QuartzApplication Start Success-----------------------------------------------------------------");
	}
}
