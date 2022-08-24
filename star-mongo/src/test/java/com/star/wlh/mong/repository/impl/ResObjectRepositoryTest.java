package com.star.wlh.mong.repository.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.star.common.utils.ConstantsFields;
import com.star.wlh.mongo.MongoApplication;
import com.star.wlh.mongo.entity.ResObject;
import com.star.wlh.mongo.repository.ResObjectRepository;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author : wlh
 * @date Date : 2022年08月17日 16:44
 */

@SpringBootTest(classes = MongoApplication.class) @RunWith(SpringRunner.class) public class ResObjectRepositoryTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ResObjectRepositoryTest.class);

	@Autowired private ResObjectRepository resObjectRepository;

	@Test public void queryTest() {
		String source = "SDAN";
		List<String> sources = Arrays.asList(source.split(","));
		Query query = initMongoQuery("Switch", sources, 2000, "000000000000000000000000");
		Query queryBySource = initMongoQueryBySource("Switch", sources, 2000, "000000000000000000000000");

		LOGGER.info("query:{}", query);
		LOGGER.info("queryBySource:{}", queryBySource);
		//initSwitchResObject(source);
		List<ResObject> resObjects = resObjectRepository.find(queryBySource);
		LOGGER.info("resObjects:{}",resObjects.size());
	}

	private static void initSwitchResObject(String source) {
		String url = "http://10.1.60.114/store/openapi/v2/resources/batch_save?apikey=5f18514fe82f11ea90dc005056981a0d&source="+source;
		JSONArray resObjectList = new JSONArray();
		for (int i = 10; i < 20; i++) {
			JSONObject entries = new JSONObject();
			entries.set("serial_number", "test_serial_number_" + i);
			entries.set("classCode", "Switch");
			entries.set("is_active", "Y");
			entries.set("desc", "test_desc_" + i);
			entries.set("gateway", "test_gateway_" + i);
			entries.set("net_sys_code", "test_net_sys_code_" + i);
			resObjectList.add(entries);

		}
		LOGGER.info("url=:{}",url);
		LOGGER.info("resObjectList:{}", resObjectList);
		String body = HttpRequest.post(url).body(resObjectList.toJSONString(0)).execute().body();
		LOGGER.info("body:{}", body);
	}

	private static Query initMongoQuery(String classCode, List<String> sources, int pageSize, String minId) {
		Criteria criteriaA = new Criteria();
		criteriaA.andOperator(Criteria.where(ConstantsFields.CLASS_CODE).is(classCode),
						Criteria.where(ConstantsFields.CREATE_SOURCE).in(sources), Criteria.where(ConstantsFields.ATTR_VALUES_IS_ACTIVE_V).is("Y"),
						Criteria.where(ConstantsFields.TENANT_ID).is("e10adc3949ba59abbe56e057f20f88dd"),
						Criteria.where(ConstantsFields.MONGO_ID).gt(new ObjectId(minId)));
		Criteria criteriaB = new Criteria();
		criteriaB.andOperator(Criteria.where(ConstantsFields.CLASS_CODE).is(classCode),
						Criteria.where(ConstantsFields.ATTR_VALUES_IS_ACTIVE_V).is("Y"),
						Criteria.where(ConstantsFields.TENANT_ID).is("e10adc3949ba59abbe56e057f20f88dd"),
						Criteria.where(ConstantsFields.MONGO_ID).gt(new ObjectId(minId)),
						Criteria.where(ConstantsFields.SOURCES).elemMatch(Criteria.where(ConstantsFields.CODE).in(sources)));
		Criteria criteria = new Criteria().orOperator(criteriaA, criteriaB);
		Query query = new Query(criteria);
		query.limit(pageSize);
		query.with(Sort.by(Sort.Order.asc(ConstantsFields.TENANT_ID)));
		return query;
	}

	private static Query initMongoQueryBySource(String classCode, List<String> sources, int pageSize, String minId) {
		Criteria orCriteria = new Criteria();
		orCriteria.orOperator(Criteria.where(ConstantsFields.SOURCES).elemMatch(Criteria.where(ConstantsFields.CODE).in(sources)),
						Criteria.where(ConstantsFields.CREATE_SOURCE).in(sources));
		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where(ConstantsFields.CLASS_CODE).is(classCode),
						Criteria.where(ConstantsFields.ATTR_VALUES_IS_ACTIVE_V).is("Y"),
						Criteria.where(ConstantsFields.TENANT_ID).is("e10adc3949ba59abbe56e057f20f88dd"),
						Criteria.where(ConstantsFields.MONGO_ID).gt(new ObjectId(minId)), orCriteria);

		Query query = new Query(criteria);
		query.limit(pageSize);
		query.with(Sort.by(Sort.Order.asc(ConstantsFields.TENANT_ID)));
		return query;
	}
}
