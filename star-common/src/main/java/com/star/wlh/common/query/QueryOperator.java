package com.star.wlh.common.query;

/**
 * 查询条件的比较符，和 <code> QueryParam </code> 配合使用。
 *
 * @author hesy
 */
public enum QueryOperator {

	/**
	 * 等于，如用于匹配数组，则只有元素个数和顺序都匹配就满足
	 */
	EQ("equal", true),

	/**
	 * 不等于，如用于匹配数组，则只要元素个数或顺序不匹配就满足
	 */
	NOT_EQ("not_equal", true),

	/**
	 * 在...之内：匹配数组，数组内元素个数不限
	 */
	IN("in", true),

	/**
	 * 不在...之内：匹配数组，数组内元素个数不限
	 */
	NOT_IN("not_in", true),

	/**
	 * 匹配数组内所有元素，数组内元素个数不限
	 */
	ALL_EXISTS("all_exists_in_array", true),

	/**
	 * 在...范围内（闭区间）：匹配数组，数组内元素个数必须为两个，第一个参数为下限值，第二个参数为上限值
	 */
	RANGE("between_two_values", true),

	/**
	 * 大于：匹配单个值
	 */
	GT("greater_than", false),

	/**
	 * 大于等于：匹配单个值
	 */
	GTE("greater_equal_than", false),

	/**
	 * 小于：匹配单个值
	 */
	LT("less_than", false),

	/**
	 * 小于等于：匹配单个值
	 */
	LTE("less_equal_than", false),

	/**
	 * 字符串包含判断，大小写敏感：匹配单个值 <br>
	 * 注：该操作符的底层实现为全模糊查询，在大数据量的情况下性能很差，建议改用 {@link PREFIX }
	 */
	CONTAIN_CS("contain_case_sensitive", false),

	/**
	 * 字符串包含判断，大小写不敏感：匹配单个值 <br>
	 * 注：该操作符的底层实现为全模糊查询，在大数据量的情况下性能很差，建议改用 {@link PREFIX }
	 */
	CONTAIN_CI("contain_case_insensitive", false),

	/**
	 * 为 null 判断：不需要提供值
	 */
	IS_NULL("is_null", false),

	/**
	 * 非 null 判断：不需要提供值
	 */
	IS_NOT_NULL("is_not_null", false),

	/**
	 * 判断匹配数组内元素的个数
	 */
	ARRAY_SIZE_EQ("array_size_eq", false),

	/**
	 * 字符串的正则匹配<br>
	 * 注：该操作符的底层实现为正则查询，在大数据量的情况下请使用 ^ 打头的正则，否则性能很差
	 */
	REGEX("text_regex_match", false),

	/**
	 * 属性是否存在的判断（即使值为 null 也算存在），可选值：true 或 false
	 */
	EXISTS("field_exists", false),

	/**
	 * 匹配以指定文本为前缀（大小写敏感）的字符串：
	 */
	PREFIX("start_with", false);

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 是否支持多值
	 */
	private boolean multiValueSupported;

	private QueryOperator(String name, boolean multiValueSupported) {
		this.name = name;
		this.multiValueSupported = multiValueSupported;
	}

	/**
	 * 当前查询操作符是否支持多个值作为条件。
	 *
	 * @return 如果支持返回 true
	 */
	public boolean isMultiValueSupported() {
		return multiValueSupported;
	}

	public String getName() {
		return name;
	}

}