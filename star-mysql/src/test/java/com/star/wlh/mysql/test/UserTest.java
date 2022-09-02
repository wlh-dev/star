package com.star.wlh.mysql.test;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.star.wlh.mysql.entity.LoginUserEntity;
import com.star.wlh.mysql.mapper.LoginUserDao;
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
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : wlh
 * @date Date : 2022年08月06日 02:45
 */
@SpringBootTest @RunWith(SpringRunner.class) public class UserTest {
	private static final Logger logger = LoggerFactory.getLogger(UserTest.class);

	private static ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 20, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
					Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

	@Autowired private LoginUserDao userDao;

	@Test public void queryTest() {
		List<LoginUserEntity> query = userDao.query();
		Assert.assertNotEquals(query.size(), 1);
	}

	@Test public void insertTest() {
		LoginUserEntity userEntity = new LoginUserEntity();
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		logger.info("uuid:{}", uuid);
		userEntity.setUserId(uuid);
		userEntity.setLoginName(uuid);
		userEntity.setUserName(uuid);
		userEntity.setEnglishName(uuid);
		userEntity.setAccessKey(uuid);
		userEntity.setPassword(uuid);
		userEntity.setAccessKeySecret(uuid);
		userDao.insert(userEntity);
	}

	@Test public void batchSaveTest() {

	}

	public static void main(String[] args) {
		JSONObject postBody = new JSONObject();
		postBody.set("classCode", "Switch");
		postBody.set("serial_number", "test_0809");
		postBody.set("name", "test_0809");
		postBody.set("is_active", "Y");
		JSONArray postArray = new JSONArray();
		postArray.add(postBody);
		String url = "http://10.1.60.114/store/openapi/v2/resources/batch_save?apikey=5f18514fe82f11ea90dc005056981a0d&source=CLOUD";
		long start = System.currentTimeMillis();
		logger.info("postBody:{}",postArray);
		logger.info("开始执行并发请求:{}", start);
		for (int i = 0; i < 100; i++) {
			pool.execute(() -> {
				String body = HttpRequest.post(url).body(postArray.toString()).execute().body();
				logger.info("body:{}",body);
			});
		}
		long end = System.currentTimeMillis();
		logger.info("结束执行并发请求:{}", end);
		logger.info("执行时间是:{} 毫秒", end - start);
	}
}
