package com.star.wlh.mongo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wlh
 * @version 1.0
 * @date 2022/08/17 3:51 下午
 * @description 业务pacific数据源
 **/
@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb.pacific")
@Component
public class PacificMongoConfig extends AbstractMongoConfigure {
	@Primary
	@Override
	@Bean(name = "pacificMongoTemplate")
	public MongoTemplate getMongoTemplate() {
		return new MongoTemplate(mongoDatabaseFactory());
	}
}
