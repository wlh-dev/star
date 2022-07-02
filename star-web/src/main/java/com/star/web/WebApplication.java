package com.star.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @author : wlh
 * @date Date : 2022年06月19日 23:08
 */
@SpringBootApplication
public class WebApplication {
		public static void main(String[] args) {
				SpringApplication.run(WebApplication.class,args);
		}
}
