package com.star.wlh.user;

import com.star.wlh.UserApplication;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * test with spring integration tests
 */
@SpringBootTest(classes = UserApplication.class)
@DisplayName("基础测试")
public class BaseTest {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("beforeAll");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("afterAll");
    }

    @Test
    @DisplayName("基准测试")
    public void baseTest() {
        System.out.println("基础测试");
    }
}
