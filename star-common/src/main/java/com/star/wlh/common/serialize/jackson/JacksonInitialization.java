package com.star.wlh.common.serialize.jackson;

import com.star.wlh.common.initialization.InitializationRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author : wlh
 * @date Date : 2022年09月21日 18:06
 */

@Component
public class JacksonInitialization implements InitializationRegister {
	private static final Logger logger = LoggerFactory.getLogger(JacksonInitialization.class);
	@Override public void register() {
		logger.info("开始注册序列化Jackson");
	}
}
