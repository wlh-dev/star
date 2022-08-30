package com.star.wlh.mongo.repository.impl;

import com.star.wlh.mongo.entity.ResObject;
import com.star.wlh.mongo.entity.SourceType;
import com.star.wlh.mongo.repository.ResObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Query;
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
	public List<ResObject> find(Query query) {
		return mongoTemplate.find(query,ResObject.class);
	}
	@Override
	public AggregationResults<SourceType> aggregate(Aggregation aggregate) {
		return  mongoTemplate.aggregate(aggregate, "resObject", SourceType.class);
	}
}
