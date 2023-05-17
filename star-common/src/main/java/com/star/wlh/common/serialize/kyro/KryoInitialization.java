package com.star.wlh.common.serialize.kyro;

import com.star.wlh.common.initialization.InitializationRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 初始化kryo序列化的初始化工作
 *
 * @author : wlh
 * @date Date : 2022年09月21日 18:06
 */

@Component public class KryoInitialization implements InitializationRegister {
	private static final Logger logger = LoggerFactory.getLogger(KryoInitialization.class);

	@Override public void register() {
		logger.info("开始注册序列化Kryo");
		KryoFactory.init(100);
	}
}
