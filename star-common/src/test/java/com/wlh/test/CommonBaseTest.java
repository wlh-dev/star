package com.wlh.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : wlh
 * @date Date : 2022年09月21日 19:39
 */

@SpringBootTest(classes = TestApplication.class) @RunWith(SpringRunner.class) public class CommonBaseTest {
	@Test public void baseTest() {
		Assert.assertFalse(false);
	}
}
