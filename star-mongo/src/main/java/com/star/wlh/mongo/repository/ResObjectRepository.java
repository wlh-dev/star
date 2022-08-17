package com.star.wlh.mongo.repository;

import com.star.wlh.mongo.entity.ResObject;
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
}
