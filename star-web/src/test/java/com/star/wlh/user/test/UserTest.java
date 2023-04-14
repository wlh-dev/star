package com.star.wlh.user.test;

import com.star.wlh.user.BaseTest;
import com.star.wlh.user.config.Result;
import com.star.wlh.user.entity.UserEntity;
import com.star.wlh.user.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);
    @Autowired
    private UserMapper userMapper;
    @Test
    public void userTest() {
        List<String> list = new ArrayList<>();
        list.add("001");
        UserEntity userEntity = userMapper.selectById("001");
        logger.info("查询到的数据是:{}", userEntity);
    }

    /**
     * 根据ID查询
     * {@link com.star.wlh.user.service.UserService#findById(String)}
     */
    @Test
    @DisplayName("根据ID查询")
    public void findByIdTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId("001");

        UserEntity query = userMapper.selectById(userEntity);
         logger.info("查询到的数据是:{}",query);
    }

    @Test
    @DisplayName("测试事务")
    public void userTransactionalTest() {


    }
}
