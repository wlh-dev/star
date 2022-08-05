package com.star.wlh.mysql.test;

import com.star.wlh.mysql.entity.UserEntity;
import com.star.wlh.mysql.mapper.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

/**
 * @author : wlh
 * @date Date : 2022年08月06日 02:45
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserTest.class);

	@Autowired
	private UserDao userDao;
	@Test
	public void queryTest(){
		List<UserEntity> query = userDao.query();
		Assert.assertNull(query);
	}
	@Test
	public void insertTest(){
		UserEntity userEntity = new UserEntity();
		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		LOGGER.info("uuid:{}",uuid);
		userEntity.setUserId(uuid);
		userEntity.setLoginName(uuid);
		userEntity.setUserName(uuid);
		userEntity.setEnglishName(uuid);
		userEntity.setAccessKey(uuid);
		userEntity.setPassword(uuid);
		userEntity.setAccessKeySecret(uuid);
		userDao.insert(userEntity);
	}
}
