package com.star.wlh.mysql.entity;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : wlh
 * @date Date : 2022年09月05日 14:29
 */

public class LoginUserEntityTest {
	private static final Logger logger = LoggerFactory.getLogger(LoginUserEntityTest.class);
	@Test
	public  void  loginUserEntityTest(){
		LoginUserEntity loginUserEntity = initLoginUserEntity();
		Assert.assertNotNull("数据对象不能为空",loginUserEntity);
		logger.info("test:{}","test");
	}

	private LoginUserEntity initLoginUserEntity() {
		return new LoginUserEntity("001" ,"张三","judy","zhangsan.com","admin","admin","admin");
	}
}
