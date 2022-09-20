package com.star.wlh.mongo.base;

/**
 * 定义一些常量。
 *
 * @author wlh
 */
public enum ConstantsFields {
	/**
	 * 唯一id
	 */
	ID("id", "ID"),
	/**
	 * mongoId
	 */
	MONGO_ID("_id", "mongoId"),
	/**
	 * 租户id
	 */
	TENANT_ID("tenantId", "租户id"),
	/**
	 * 创建来源
	 */
	CREATE_SOURCE("createSource", "创建来源"),
	/**
	 * 更新来源
	 */
	SOURCES("sources", "更新来源"),

	/**
	 * 配置项编码
	 */
	CLASS_CODE("classCode", "配置项编码"),

	/**
	 * 配置项名称
	 */
	CLASS_NAME("className", "配置项名称"),

	/**
	 * 比对时间
	 */
	CHECK_TIME("checkTime", "比对时间"),

	/**
	 * 领域名称
	 */
	FIELD_NAME("fieldName", "领域名称"),

	/**
	 * 领域编码
	 */
	FIELD_CODE("fieldCode", "领域编码"),
	/**
	 * 上游系统编码
	 */
	UPPER_SYSTEM_CODE("upperSystemCode", "上游系统编码"),

	/**
	 * 上游推送名称
	 */
	UPPER_SYSTEM_NAME("upperSystemName", "上游系统名称"),

	/**
	 * 关键属性值
	 */
	IDENTIFIER_VALUE("identifierValue", "关键属性值"),
	/**
	 * 比对总数
	 */
	CHECK_TOTAL("checkTotal", "比对总数"),
	/**
	 * 比对详情
	 */
	CHECK_DETAIL("checkDetail", "比对详情"),
	/**
	 * 不一致数量
	 */
	DIFFERENT("different", "不一致数量"),
	/**
	 * CMDB 配置管理库中的总数
	 */
	CMDB_TOTAL("cmdbTotal", "CMDB总数"),
	/**
	 * 上游推送总数
	 */
	UPPER_SYSTEM_TOTAL("upperSystemTotal", "上游推送总数"),

	/**
	 * 一致总数
	 */
	SAME_TOTAL("sameTotal", "一致总数"),

	/**
	 * CMDB 配置管理库中的数据比上游多的总数
	 */
	CMDB_MORE_TOTAL("cmdbMoreTotal", "CMDB比上游多"),

	/**
	 * 上游推送数据比CMDB配置管理库中多的总数
	 */
	UPPER_SYSTEM_MORE_TOTAL("upperSystemMoreTotal", "上游比CMDB多"),

	/**
	 * 关键属性编码
	 */
	IDENTIFIER_KEY("identifierKey", "关键属性编码"),

	/**
	 * 覆盖率
	 */
	COVERAGE_RATE("coverageRate", "覆盖率"),

	/**
	 * 编码
	 */
	CODE("code", "编码"),
	/**
	 * 是否有效
	 */
	ATTR_VALUES_IS_ACTIVE_V("attrValues.is_active.V", "是否有效");

	private final String key;
	private final String value;

	ConstantsFields(java.lang.String key, java.lang.String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
