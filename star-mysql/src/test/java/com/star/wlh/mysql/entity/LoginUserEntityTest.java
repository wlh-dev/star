package com.star.wlh.mysql.entity;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
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
	@Test
	public void stringTest(){
		String linux = parseDdl("Linux");
		jsonTest();
		System.out.println(linux);
	}

	private String parseDdl(String tableName) {
		return "INSERT INTO "+tableName+"(id,body,is_active,is_delete,uyun_update_time) values (?,?,?,?,?)";
	}
	private void jsonTest(){
		String str = "{\"id\":\"760ebc594d75df76621a826c\",\"tenantId\":\"e10adc3949ba59abbe56e057f20f88dd\",\"classCode\":\"Switch\",\"className\":\"交换机\",\"outerObjectId\":null,\"objectVersion\":3,\"state\":\"APPLY\",\"createTime\":\"2022-08-3111:36:13\",\"updateTime\":\"2022-08-3114:46:46\",\"subUpdateTime\":null,\"sources\":[\"ADS\"],\"tags\":null,\"resOwners\":[\"CMDB\",\"MONITOR\",\"AUTOMATION\",\"NETWORK\"],\"serial_number\":\"test-07212\",\"is_active\":\"N\",\"device_vendor\":\"test-10726\",\"device_code\":{\"sequence\":22,\"name\":\"交换机\"},\"device_origin_code\":{\"sequence\":22,\"name\":\"交换机\"},\"network_domain\":{\"name\":\"默认域\",\"id\":\"630212524d75dfe19d398ab8\"}},{\"id\":\"630262534d75df82fbeae4a1\",\"tenantId\":\"e10adc3949ba59abbe56e057f20f88dd\",\"classCode\":\"Switch\",\"className\":\"交换机\",\"outerObjectId\":null,\"objectVersion\":17,\"state\":\"APPLY\",\"createTime\":\"2022-08-2200:50:27\",\"updateTime\":\"2022-08-2510:48:28\",\"subUpdateTime\":null,\"sources\":[\"ADS\",\"N-SDAN\",\"DATAMNG\",\"CMDB\",\"user\"],\"tags\":[\"A\"],\"resOwners\":[\"CMDB\",\"MONITOR\",\"AUTOMATION\",\"NETWORK\"],\"serial_number\":\"test-0726\",\"is_active\":\"Y\",\"device_vendor\":\"test-10726\",\"device_code\":{\"sequence\":1,\"name\":\"交换机\"},\"device_origin_code\":{\"sequence\":1,\"name\":\"交换机\"},\"network_domain\":{\"name\":\"默认域\",\"id\":\"630212524d75dfe19d398ab8\"},\"device_status_code\":\"1\"}";
		JSONObject parse = JSONUtil.parseObj(str);
		System.out.println(parse);
	}

	private LoginUserEntity initLoginUserEntity() {
		return new LoginUserEntity("001" ,"张三","judy","zhangsan.com","admin","admin","admin");
	}
}
