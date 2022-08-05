package com.star.web.controller;

import com.star.wlh.mysql.entity.UserEntity;
import com.star.wlh.mysql.mapper.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : wlh
 * @date Date : 2022年08月06日 02:13
 */

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Resource
	private UserDao userDao;

	/**
	 * 查询用户
	 * @return
	 */
	@RequestMapping("/query")
	public List<UserEntity> query(){
		return userDao.query();
	}

}
