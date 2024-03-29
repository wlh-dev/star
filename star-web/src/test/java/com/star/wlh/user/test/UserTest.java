package com.star.wlh.user.test;

import cn.hutool.extra.spring.SpringUtil;
import com.star.wlh.common.serialize.Serialization;
import com.star.wlh.common.serialize.SerializationFactory;
import com.star.wlh.user.BaseTest;
import com.star.wlh.user.entity.UserEntity;
import com.star.wlh.user.mapper.UserMapper;
import com.star.wlh.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

public class UserTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    @Qualifier("userServiceJdbcTemplate")
    private UserService userService;

    @Test
    public void userTest() {
        UserService userServiceJdbcTemplate = SpringUtil.getBean("userServiceJdbcTemplate", UserService.class);

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
        userEntity.setId("1");
        UserEntity query = userMapper.selectById(userEntity);
        logger.info("查询到的数据是:{}", query);
    }

    @Test
    @DisplayName("测试分页查询")
    public void userFindByPageTest() {
        List<UserEntity> records = userService.findAllByPage(1, 10);
        for (UserEntity record : records) {
            logger.info("查询数据结果：{}", record);
        }

    }

    @Test
    @DisplayName("根据ID查询")
    public void userFindByIdTest() {
        UserEntity user = userService.findById("1");
        logger.info("查询数据结果：{}", user);
    }

    @Test
    @DisplayName("序列化测试")
    public void serialTest() {
        Serialization aDefault = SerializationFactory.getDefault();
        SerializationFactory.Serializations code = aDefault.getCode();
        logger.info("code:{}", code);
    }

}
