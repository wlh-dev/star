package com.star.wlh.user.service.impl;

import com.github.rholder.retry.*;
import com.star.wlh.user.entity.UserEntity;
import com.star.wlh.user.mapper.UserMapper;
import com.star.wlh.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
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
    public UserEntity retry() {
        Callable<UserEntity> callable = ()-> {
            logger.info("执行查询数据");
            return userMapper.selectByUserId("001");
        };
        Retryer<UserEntity> retryer = RetryerBuilder.<UserEntity>newBuilder()
                .retryIfExceptionOfType(IOException.class)
                .retryIfExceptionOfType(ArithmeticException.class)
                .retryIfRuntimeException()
                .retryIfResult(Objects::isNull)
                .withWaitStrategy(WaitStrategies.fibonacciWait(100,3, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();
        try {
            return retryer.call(callable);
        } catch (RetryException | ExecutionException e) {
            logger.error("error",e);
        }
        return null;
    }
}
