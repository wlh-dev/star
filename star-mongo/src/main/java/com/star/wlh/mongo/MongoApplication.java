package com.star.wlh.mongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author : wlh
 * @date Date : 2022年08月05日 17:33
 */

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableMongoRepositories(basePackages = "com.star.wlh.mongo")
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
