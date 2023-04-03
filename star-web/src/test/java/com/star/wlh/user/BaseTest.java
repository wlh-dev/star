package com.star.wlh.user;

import com.star.wlh.UserApplication;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * test with spring integration tests
 */
@SpringBootTest(classes = UserApplication.class)
@DisplayName("基础测试")
public class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeAll
    public static void beforeAll() {
        logger.info("beforeAll");
    }

    @AfterAll
    public static void afterAll() {
        logger.info("afterAll");
    }

    @Test
    @DisplayName("基准测试")
    public void baseTest() {
        logger.info("基础测试");
    }

}
