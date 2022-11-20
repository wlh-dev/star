package com.star.wlh.mong;

import com.star.wlh.MongoApplication;
import com.star.wlh.mongo.entity.ResObject;
import com.star.wlh.mongo.repository.ResObjectRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author : wlh
 * @date Date : 2022年08月17日 16:44
 */

@SpringBootTest(classes = MongoApplication.class)
public class ResObjectRepositoryTest {
	private static final Logger logger = LoggerFactory.getLogger(ResObjectRepositoryTest.class);

	@Autowired
	private ResObjectRepository resObjectRepository;

	@Test
	public  void  queryTest(){
		Criteria criteria = Criteria.where("classCode").is("Switch");

		Query query = new Query(criteria);

		List<ResObject> resObject = resObjectRepository.find(query, ResObject.class, "reObject");
		logger.info("resObject:{}", resObject);
		Assert.isNull(resObject);

	}


}
