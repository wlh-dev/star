package com.star.common.entity.redis;

import com.star.common.config.ConfigValue;
import com.star.web.WebApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author : wlh
 * @date Date : 2022年06月20日 01:03
 * @desc 需要在测试类中指定启动类
 */

@SpringBootTest(classes = WebApplication.class) public class RedisTest {

		@Resource private ConfigValue configValue;
		@Test public void redisTest() {
				String calcTopTenValueAttrCodes = configValue.getCalcTopTenValueAttrCodes();
				System.out.println(calcTopTenValueAttrCodes);
		}
}
