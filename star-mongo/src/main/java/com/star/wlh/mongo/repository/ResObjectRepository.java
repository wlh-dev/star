package com.star.wlh.mongo.repository;

import com.star.wlh.mongo.entity.SourceType;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * @author : wlh
 * @date Date : 2022年08月17日 17:04
 */

public interface ResObjectRepository {

	/**
	 *
	 * @param query
	 * @param entityClass
	 * @param collectionName
	 * @param <T>
	 * @return
	 */
	<T> List<T> find(Query query,Class<T> entityClass,String collectionName);

	/**
	 *
	 * @param query
	 * @param entityClass
	 * @param collectionName
	 * @param <T>
	 * @return
	 */
	public <T> T findOne(Query query, Class<T> entityClass,String collectionName);

	/**
	 *
	 * @param aggregate
	 * @return
	 */
	AggregationResults<SourceType> aggregate(Aggregation aggregate);

	/**
	 *
	 * @param objectToSave
	 * @param collectionName
	 * @param <T>
	 * @return
	 */
	<T> T save(T objectToSave, String collectionName);

	/**
	 *
	 * @param query
	 * @param update
	 * @param collectionName
	 */
	void updateMulti(Query query, Update update, String collectionName);
}
