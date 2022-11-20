package com.star.wlh.quartz.init;

import cn.hutool.core.collection.CollUtil;
import com.star.wlh.mysql.entity.UserEntity;
import com.star.wlh.mysql.mapper.UserMapper;
import com.star.wlh.quartz.entity.QuartzJob;
import com.star.wlh.quartz.mapper.QuartzJobMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : wlh
 * @date Date : 2022年09月02日 15:50
 */
@Component
@Order(value = 2)
public class InitCronProcessJobHandler implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(InitCronProcessJobHandler.class);
	@Autowired
	private UserMapper loginUserDao;

	@Autowired
	public void setQuartzJobMapper(QuartzJobMapper quartzJobMapper) {
		this.quartzJobMapper = quartzJobMapper;
	}

	private QuartzJobMapper quartzJobMapper;

	@Override
	public void run(String... args) throws Exception {
		List<UserEntity> users = loginUserDao.query();
		logger.info("users:{}", users);
		List<QuartzJob> query = quartzJobMapper.query();
		logger.info("数据库中定时任务数据有:{}", query.size());
		if (CollUtil.isEmpty(query)){
			logger.info("定时任务数据库中无数据");
		}
	}

}
