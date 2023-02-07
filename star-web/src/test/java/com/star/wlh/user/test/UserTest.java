package com.star.wlh.user.test;

import com.star.wlh.user.BaseTest;
import com.star.wlh.user.entity.UserEntity;
import com.star.wlh.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Autowired
    private UserMapper userMapper;
    @Test
    public void userTest() {
        List<UserEntity> query = userMapper.query();
        logger.info("查询到的数据是:{}",query);
    }
    @Test
    public void findByIdTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId("001");
        UserEntity query = userMapper.findById(userEntity);
        logger.info("查询到的数据是:{}",query);
    }
}
