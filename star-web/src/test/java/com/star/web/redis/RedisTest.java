package com.star.web.redis;

import com.star.web.WebApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : wlh
 * @date Date : 2022年06月20日 01:03
 * @desc 需要在测试类中指定启动类
 */

@SpringBootTest(classes = WebApplication.class)
public class RedisTest {

		@Test
		public void redisTest(){
				System.out.println("test");
		}
}
