package com.star.wlh.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.rholder.retry.*;
import com.star.wlh.user.dto.UserConvertor;
import com.star.wlh.user.dto.UserDTO;
import com.star.wlh.user.entity.UserEntity;
import com.star.wlh.user.mapper.UserMapper;
import com.star.wlh.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service(value = "userServiceMyBatis")
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Override
    public UserEntity findById(String id) {
        UserEntity userEntity = userMapper.selectByUserId(id);
        logger.debug("查询到数据为:{}", userEntity);
        return userEntity;
    }

    @Override
    public List<UserEntity> findAllByPage(long current, long size) {

        Page<UserEntity> page = new Page<>();
        page.setSize(size);
        Page<UserEntity> userEntityPage = userMapper.selectPage(new Page<>(current, size), new QueryWrapper<>());
        long pages = userEntityPage.getPages();
        long total = userEntityPage.getTotal();
        logger.info("pages:{},current:{},total:{},size:{}", pages, userEntityPage.getCurrent(), total, userEntityPage.getSize());
        List<UserEntity> records = userEntityPage.getRecords();
        logger.info("records:{}", records);
        return records;
    }

    @Override
    public UserEntity retry() {
        Callable<UserEntity> callable = () -> {
            logger.info("执行查询数据");
            return userMapper.selectByUserId("001");
        };
        Retryer<UserEntity> retry = RetryerBuilder.<UserEntity>newBuilder()
                .retryIfExceptionOfType(IOException.class)
                .retryIfExceptionOfType(ArithmeticException.class)
                .retryIfRuntimeException()
                .retryIfResult(Objects::isNull)
                .withWaitStrategy(WaitStrategies.fibonacciWait(100, 3, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();
        try {
            return retry.call(callable);
        } catch (RetryException | ExecutionException e) {
            logger.error("error", e);
        }
        return null;
    }

    @Override
    public UserEntity insert(UserDTO userDTO) {
        UserEntity userEntity = UserConvertor.userDTOConvertUserEntity(userDTO);
        try {
            userMapper.save(userEntity);
        } catch (Exception e) {
            logger.error("插入失败");
            return null;
        }
        return userEntity;
    }
}
