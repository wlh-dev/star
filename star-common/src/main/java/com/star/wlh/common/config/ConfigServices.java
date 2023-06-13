/*
package com.star.wlh.common.config;


import java.util.ArrayList;
import java.util.List;

public class ConfigServices {
	
	*/
/*
	 * 是否启用配置服务
	 *//*

	private static boolean configServiceEnabled = Boolean.parseBoolean(System.getProperty(Constants.SYS_PROPS_CONFIG_SERVICE_ENABLED, "true"));
	
	private static final String NS_COMMON = "common.properties";
	private static final String NS_PACIFIC = System.getProperty("pacific.common.config.namespace", "platform-store-res.properties"); // 支持替换
	private static String[] nsCommonGroups;

	private ConfigServices() {}
	
	static {
		String groupFile = System.getProperty("common.group.files"); // 多实例支持
		if (groupFile != null) {
			nsCommonGroups = groupFile.split(",");
		}
	}
	
	*/
/**
	 * 判断是否启用配置服务。
	 * 
	 * @return 如果启用返回 true
	 *//*

	public static boolean isConfigServiceEnabled() {
		return configServiceEnabled;
	}

	*/
/**
	 * 设置是否启用配置服务。
	 * 
	 * @param configServiceEnabled true：启用，false：禁用
	 *//*

	public static void setConfigServiceEnabled(boolean configServiceEnabled) {
		ConfigServices.configServiceEnabled = configServiceEnabled;
	}

	*/
/**
	 * 获取指定配置的值。
	 * 
	 * @param configKey 配置KEY
	 * @return 配置值
	 *//*

	public static Object getConfigValue(Class<?> valueClass, String configKey) {
		if (!ConfigServices.configServiceEnabled) {
			return null;
		}
		
		String value = ConfigService.getConfig(NS_PACIFIC).getProperty(configKey, null);
		if (value == null) {
			value = getCommonConfigValue(configKey);
		}
		if (value instanceof String) {
			String realKey = value;
			if (realKey.contains("${") && realKey.contains("}")) {
				value = resolvePlaceholderValue(value);
			}
		}
		return convertValue(valueClass, value);
	}
	
	private static String getCommonConfigValue(String commonPropKey) {
		String commonPropValue = null;
		if (nsCommonGroups != null) {
			for (String namespace : nsCommonGroups) {
				commonPropValue = ConfigService.getConfig(namespace).getProperty(commonPropKey, null);
				if (commonPropValue != null) {
					return commonPropValue;
				}
			}
		}
		return ConfigService.getConfig(NS_COMMON).getProperty(commonPropKey, null);
	}
	
	private static String resolvePlaceholderValue(String value) {
		List<String> valueWithPlaceholders = retrievePlaceholders(value);
		if (valueWithPlaceholders.size() == 1) {
			return valueWithPlaceholders.get(0);
		}
		Object[] args = new Object[valueWithPlaceholders.size() - 1];
		for (int i = 1; i < valueWithPlaceholders.size(); i++) {
                args[i - 1] = getConfigValue(String.class, valueWithPlaceholders.get(i));
		}
		return String.format(valueWithPlaceholders.get(0), args);
	}
	
	private static List<String> retrievePlaceholders(String value) {
		List<Character> placedValue = new ArrayList<>();
		StringBuilder wholeString = new StringBuilder();
		List<String> result = new ArrayList<>();
		result.add(""); // 占位
		
		boolean inPlaceholder = false; // 是否为占位参数值的一部分
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			
			if (c == '$' && chars[i + 1] == '{') {
				i++; // 跳过两个字符
				if (inPlaceholder) {
					// 暂不考虑嵌套
				}
				inPlaceholder = true;
			} else if (c == '}') {
				if (inPlaceholder) {
					wholeString.append("%s");
					result.add(toString(placedValue));
					placedValue.clear();
				}
				inPlaceholder = false;
			} else {
				if (inPlaceholder) {
					placedValue.add(c);
				} else {
					wholeString.append(c);
				}
			}
		}
		
		result.set(0, wholeString.toString());
		return result;
	}
	
	private static String toString(List<Character> charList) {
		StringBuilder result = new StringBuilder(charList.size());
		charList.forEach(c -> result.append(c.charValue()));
		return result.toString();
	}
	
	*/
/**
	 * 将值转换为指定的类型。
	 * 
	 * @param valueClass 值类型
	 * @param value 值
	 * @return 转换后的值
	 *//*

	public static Object convertValue(Class<?> valueClass, String value) {
		if (value == null || valueClass == String.class) {
			return value;
		}
		if (valueClass == boolean.class || valueClass == Boolean.class) {
			return Boolean.parseBoolean(value);
		} else if (valueClass == Integer.class || valueClass == int.class) {
			return Integer.parseInt(value);
		} else if (valueClass == Long.class || valueClass == long.class) {
			return Long.parseLong(value);
		} else if (valueClass == Double.class || valueClass == double.class) {
			return Double.parseDouble(value);
		} else if (valueClass == Byte.class || valueClass == byte.class) {
			return Byte.parseByte(value);
		} else if (valueClass == Short.class || valueClass == short.class) {
			return Short.parseShort(value);
		}  else if (valueClass == Float.class || valueClass == float.class) {
			return Float.parseFloat(value);
		}
		return value;
	}
	
}
*/
