package com.star.wlh.mong;

import com.star.wlh.mongo.MongoApplication;
import com.star.wlh.mongo.entity.ResObject;
import com.star.wlh.mongo.repository.ResObjectRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author : wlh
 * @date Date : 2022年08月17日 16:44
 */

@SpringBootTest(classes = MongoApplication.class)
@RunWith(SpringRunner.class)
public class ResObjectRepositoryTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ResObjectRepositoryTest.class);

	@Autowired
	private ResObjectRepository resObjectRepository;

	@Test
	public  void  queryTest(){
		Criteria criteria = Criteria.where("classCode").is("Switch");

		Query query = new Query(criteria);

		List<ResObject> resObject = resObjectRepository.find(query);
		LOGGER.info("resObject:{}",resObject);
		Assert.assertNotNull(resObject);

	}


}
