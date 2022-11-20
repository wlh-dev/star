package com.star.wlh.common.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 提供统一的配置服务。
 */
@Component
public class ConfigService {

	private final ConfigValue configValue;

	/**
	 * 把配置数据对象包装成静态常量，以供静态方法使用
	 */
	private static ConfigValue commonConfigValue;

	public ConfigService(ConfigValue configValue) {
		this.configValue = configValue;
	}

	@PostConstruct
	public void init() {
		commonConfigValue = configValue;
	}

	public static ConfigValue getInstance() {
		return commonConfigValue;
	}

}
