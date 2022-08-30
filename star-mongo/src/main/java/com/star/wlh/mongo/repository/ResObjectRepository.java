package com.star.wlh.mongo.repository;

import com.star.wlh.mongo.entity.ResObject;
import com.star.wlh.mongo.entity.SourceType;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @author : wlh
 * @date Date : 2022年08月17日 17:04
 */

public interface ResObjectRepository {
	/**
	 * 查询列表
	 * @param query
	 * @return
	 */
	List<ResObject> find(Query query);

	AggregationResults<SourceType> aggregate(Aggregation aggregate);
}
