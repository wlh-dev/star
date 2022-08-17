package com.star.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : wlh
 * @date Date : 2022年08月06日 02:13
 */

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
/*	@Resource
	private LoginUserDao userDao;*/

	/**
	 * 查询用户
	 * @return
	 */
	/*@RequestMapping("/query")
	public List<LoginUserEntity> query(){
		return userDao.query();
	}*/

}
