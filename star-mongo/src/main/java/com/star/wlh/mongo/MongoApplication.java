package com.star.wlh.mongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : wlh
 * @date Date : 2022年08月05日 17:33
 */

@SpringBootApplication
public class MongoApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(MongoApplication.class);

	public static void main(String[] args) {

		try{
			SpringApplication.run(MongoApplication.class, args);
		}catch (Exception ex){
			LOGGER.info("启动报错:",ex);
		}
	}

}
