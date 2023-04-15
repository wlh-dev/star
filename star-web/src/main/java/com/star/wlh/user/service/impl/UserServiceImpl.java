package com.star.wlh.user.service.impl;

import com.star.wlh.user.entity.UserEntity;
import com.star.wlh.user.mapper.UserMapper;
import com.star.wlh.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource
	private UserMapper userMapper;
	@Override public UserEntity findById(String id) {
		UserEntity userEntity = userMapper.selectByUserId(id);
		logger.debug("查询到数据为:{}",userEntity);
		return userEntity;
	}
}
