package com.star.wlh.common.dao.mongo;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

public class GenericMongoTemplate {

	//mongodb+srv://[username:password@]host[/[database][?options]
	private static final String MONGODB_URI = "mongodb://localhost:27017?mydb";
	private static final String DATABASE_NAME = "mydb";

	private static MongoTemplate mongoTemplate;

	private GenericMongoTemplate() {
	}

	public static MongoTemplate getMongoTemplate() {
		if (mongoTemplate == null) {
			mongoTemplate = new MongoTemplate(new SimpleMongoClientDatabaseFactory(DATABASE_NAME));
		}
		return mongoTemplate;
	}
}