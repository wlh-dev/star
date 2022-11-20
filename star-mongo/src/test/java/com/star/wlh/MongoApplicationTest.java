package com.star.wlh;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : wlh
 * @date Date : 2022年09月21日 19:39
 */

@SpringBootTest(classes = MongoApplication.class)
public class MongoApplicationTest {
	@BeforeEach
	public void beforeEach() {
		System.out.println("每个测试之前");
	}

	@BeforeAll
	public static void beforeAll() {
		System.out.println("所有测试之前");
	}

	@AfterAll
	public static void afterAll() {
		System.out.println("所有测试之后");
	}

	@AfterEach
	public void afterEach() {
		System.out.println("每个测试之后");
	}

	@Test
	public void baseTest() {
		System.out.println("基础测试成功");
	}
}
