package com.star.wlh.quartz.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.quartz.utils.ConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author : wlh
 * @date Date : 2022年09月01日 19:56
 */
@Configuration public class DruidPoolingConnectionProvider implements ConnectionProvider {
	private static final Logger logger = LoggerFactory.getLogger(DruidPoolingConnectionProvider.class);

	@Resource
	private DruidDataSource druidDataSource;

	@Override public Connection getConnection() throws SQLException {
		logger.info("druidDataSource connection ! ");
		return druidDataSource.getConnection();
	}

	@Override public void shutdown() {
		logger.info("druidDataSource closed ! ");
		druidDataSource.close();
	}

	@Override public void initialize() {
		//已有连接池不需要再进行实现方法
		String name = druidDataSource.getClass().getName();
		logger.info("使用连接池名称:{}",name);
	}
}
