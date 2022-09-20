package com.star.wlh.mongo.repository.impl;

import com.star.wlh.mongo.entity.SourceType;
import com.star.wlh.mongo.repository.ResObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : wlh
 * @date Date : 2022年08月17日 17:05
 */
@Component
public class ResObjectRepositoryImpl implements ResObjectRepository {
	/**
	 * 注入MongoTemplate
	 */
	private final MongoTemplate mongoTemplate;

	@Autowired
	public  ResObjectRepositoryImpl(@Qualifier(value = "pacificMongoTemplate") MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	@Override
	public <T> List<T> find(Query query, Class<T> entityClass,String collectionName) {
		return mongoTemplate.find(query,entityClass,collectionName);
	}

	@Override
	public <T> T findOne(Query query, Class<T> entityClass,String collectionName) {
		return mongoTemplate.findOne(query,entityClass,collectionName);
	}




	@Override
	public AggregationResults<SourceType> aggregate(Aggregation aggregate) {
		return  mongoTemplate.aggregate(aggregate, "resObject", SourceType.class);
	}

	@Override public <T> T save(T objectToSave,String collectionName)  {
		return mongoTemplate.save(objectToSave,collectionName);
	}

	@Override public void updateMulti(Query query, Update update,String collectionName) {
		mongoTemplate.updateMulti(query,update, collectionName);
	}
}
