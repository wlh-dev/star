package com.star.wlh.common.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Function;

import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;


/**
 * 查询条件对象，默认开启分页查询，每页记录数为 50。
 * 
 * <pre> 
 * 使用示例：
 * {@code
 * class FilterObject { // 查询目标对象，演示用
 * 	int numField;
 * 	String strField;
 * 	InnerObject embededField;
 * }
 * 
 * class InnerObject {  // 嵌套对象，演示用
 * 	String field1;
 * }
 * 
 * // 那么想查询 FilterObject 中，numField 的值等于 15、strField 的值等于 value1 或 value2 、且 embededField 的 field1 的值包含 value1  的对象，
 * // 则对应查询条件的构造代码可以写成如下所示：
 * 
 * QueryParams<FilterObject> query = QueryParams.<FilterObject>builder()
 * 	.withPaging(1, 100)   							// 分页查询，从第 1 页开始，每页 100 条
 * 	.withCounting()								// 统计记录总数，会影响查询性能，请慎重使用
 * 	.withOrdering(SortDirection.ASC, "numField")				// 按 numField 升序排序，会影响查询性能，请慎重使用
 * 	.addParam("numField", 15)  						// 不提供 QueryOperator，默认为 EQ ，即等于操作
 * 	.addParam("strField", QueryOperator.IN, "value1", "value2")  		// IN 查询
 * 	.addParam("embededField.field1", QueryOperator.CONTAIN_CS, "value1") 	// 嵌套对象属性查询，模糊匹配
 * 	.end();
 * }
 * </pre>
 * 
 * @param <T> 查询目标对象的类型
 */
@DefaultSerializer(CompatibleFieldSerializer.class)
public class QueryParams<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 排序方向
	 */
	public enum SortDirection {
		ASC, DESC
	}

	/**
	 * 当属性 pageSize 被赋值为该值时，表明不分页<br>
	 * 注：在大数据量的情况下，不分页查询可能会导致查询很慢，或者服务进程 OOM 的等问题
	 */
	public static final int NO_PAGING = -1;

	/**
	 * 分页的开始页码
	 */
	public static final int START_PAGE = 1;
	
	/**
	 * 特殊查询字段：模板编码
	 */
	public static final String Q_FIELD_INF = "__qf_inf_code__";

	/**
	 * 是否统计满足条件的记录数<br>
	 * 注：在大数据量的情况下，指定为 true 可能会严重影响查询性能
	 */
	private boolean needCount = false;

	/**
	 * 起始页码，从 1 开始计算
	 */
	private int pageNum = START_PAGE;

	/**
	 * 每页记录数<br>
	 * 注：在大数据量的情况下，指定过大（例如 10000 ）的值可能会严重影响查询性能
	 */
	private int pageSize = 50;

	/**
	 * 排序字段<br>
	 * 注：在大数据量的情况下，如果指定的主排序字段未建立索引，会严重影响查询性能
	 */
	private SortField[] orderFields;

	/**
	 * 过滤条件项
	 */
	private QueryParamGroup queryItems;

	/**
	 * 期待返回的字段，推荐指定使用以缩减查询结果集
	 */
	private String[] expectedFields;
	
	/**
	 * 当查询资源时，资源的属性值是否返回文本形式的显示值
	 */
	private boolean returnTextValue = false;
	
	/**
	* 是否只查询逻辑删除的记录
	* 
	* @since 2.0.5
	*/
	private Boolean queryLogicallyDeleted = Boolean.FALSE;

	/**
	 * 指定需要使用的索引名
	 */
	private String hintIndex;

	public QueryParams() {
		this.queryItems = new QueryParamGroup();
	}

	public QueryParams(QueryParams<?> query) {
		this.orderFields = query.orderFields;
		this.pageNum = query.pageNum;
		this.pageSize = query.pageSize;
		this.queryItems = new QueryParamGroup(query.queryItems);
		this.expectedFields = query.expectedFields;
		this.needCount = query.needCount;
		this.queryLogicallyDeleted = query.queryLogicallyDeleted;
		this.hintIndex = query.hintIndex;
		this.returnTextValue = query.returnTextValue;
	}

	/**
	 * 创建一个参数构建器，用于快速、便利地组装 <code>QueryParams</code>。
	 * 
	 * @param <T> 待过滤对象的类型
	 * @return 构造器
	 */
	public static <T> QueryBuilder<T> builder() {
		return new QueryBuilder<>();
	}
	
	/**
	 * 创建一个空的查询条件对象，不包含任何查询条件，不开启分页查询。
	 * 
	 * @param <T> 查询目标对象的类型
	 * @return 查询条件对象
	 */
	public static <T> QueryParams<T> empty() {
		return QueryParams.<T>builder().withoutPaging().withCounting().end();
	}

	/**
	 * 创建一个空的查询条件对象，不包含任何查询条件，但开启分页查询。
	 * 
	 * @param <T> 查询目标对象的类型
	 * @return 查询条件对象
	 * @since 2.0.9
	 */
	public static <T> QueryParams<T> emptyWithPaging() {
		return QueryParams.<T>builder().withCounting().end();
	}

	/**
	 * 返回分页查询时，当前页的页码（从 1 开始）。
	 *
	 * @return 当前页的页码
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * 设置分页查询时，当前页的页码（从 1 开始）。
	 *
	 * @param pageNum 当前页的页码
	 */
	public void setPageNum(int pageNum) {
		if (pageNum <= 0) {
			this.pageNum = 1;
		} else {
			this.pageNum = pageNum;
		}
	}

	/**
	 * 返回分页查询时，每页记录数。
	 *
	 * @return 每页记录数，为 -1 表示不分页
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置分页查询时，每页的记录数。<br>
	 * 注：在大数据量的情况下，指定过大（例如 10000 ）的值可能会严重影响查询性能
	 *
	 * @param pageSize 每页记录数，为 -1 表示不分页
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 是否需要统计符合条件的记录总数。
	 *
	 * @return 如果是则返回 true，否则返回 false
	 */
	public boolean isNeedCount() {
		return needCount;
	}

	/**
	 * 设置是否需要统计符合条件的记录总数。<br>
	 * 注：在大数据量的情况下，指定为 true 可能会严重影响查询性能，请谨慎使用
	 *
	 * @param needCount 是否需要统计总数
	 */
	public void setNeedCount(boolean needCount) {
		this.needCount = needCount;
	}

	/**
	 * 返回排序条件。
	 *
	 * @return 排序条件，未指定时返回 null
	 */
	public SortField[] getOrder() {
		return orderFields == null ? null : orderFields.clone();
	}

	/**
	 * 设置排序条件。<br>
	 * 注：在大数据量的情况下，如果指定的主排序字段未建立索引，会严重影响查询性能
	 *
	 * @param orderFields 排序条件
	 */
	public void setOrder(SortField... orderFields) {
		this.orderFields = orderFields == null ? null : orderFields.clone();
	}

	/**
	 * 返回查询条件。
	 *
	 * @return 查询条件组
	 */
	public QueryParamGroup getQueryItems() {
		return queryItems;
	}

	/**
	 * 设置查询条件。
	 *
	 * @param queryItems 查询条件组
	 */
	public void setQueryItems(QueryParamGroup queryItems) {
		this.queryItems = queryItems;
	}

	/**
	 * 设置期望的字段，类似 SQL 中的 select 部分的字段名，默认为 null
	 *
	 * @param expectedFields 期望返回的字段
	 */
	public void setExpectedFields(String... expectedFields) {
		this.expectedFields = expectedFields == null ? null : expectedFields.clone();
	}

	/**
	 * 返回期望的字段。
	 *
	 * @return 期望返回的字段，未指定时返回 null，表示所有字段
	 */
	public String[] getExpectedFields() {
		return expectedFields == null ? null : expectedFields.clone();
	}

	/**
	 * 是否返回可读性强的属性值。
	 *
	 * @return 如果是则返回 true，否则返回 false
	 */
	public boolean isReturnTextValue() {
		return returnTextValue;
	}

	/**
	 * 设置为返回可读性强的属性值。
	 *
	 * @param returnTextValue 是否返回可读性强的属性值
	 */
	public void setReturnTextValue(boolean returnTextValue) {
		this.returnTextValue = returnTextValue;
	}

	/**
	 * 是否只查询逻辑删除的数据。
	 *
	 * @return 如果是返回 true，否则返回 false
	 */
	public boolean isQueryLogicallyDeleted() {
		return queryLogicallyDeleted != null && queryLogicallyDeleted.booleanValue();
	}

	/**
	 * 设置是否只查询逻辑删除的数据。
	 *
	 * @param queryLogicallyDeleted 返回 null (同时查询逻辑删除和非逻辑删除的) ， true (只查询逻辑删除)，或 false （只查询非逻辑删除的）
	 */
	public void setQueryLogicallyDeleted(Boolean queryLogicallyDeleted) {
		this.queryLogicallyDeleted = queryLogicallyDeleted;
	}

	/**
	 * 返回是否只查询逻辑删除的数据。
	 *
	 * @return 如果未设置返回 null，否则返回 true (只查询逻辑删除)，或 false （只查询非逻辑删除的）
	 */
	public Boolean getQueryLogicallyDeleted() {
		return queryLogicallyDeleted;
	}

	/**
	 * 返回设置的查询 hint。
	 *
	 * @return 查询 hint
	 */
	public String getHintIndex() {
		return hintIndex;
	}

	/**
	 * 设置用于优化查询的 hint。
	 *
	 * @param hintIndex 查询 hint
	 */
	public void setHintIndex(String hintIndex) {
		this.hintIndex = hintIndex;
	}

	/**
	 * 获取开始记录的位置。
	 * 
	 * @return 记录位置
	 */
	public int getStart() {
		// 不分页
		if (pageSize == NO_PAGING) {
			return 0;
		}
		return (this.pageNum - 1) * pageSize;
	}

	/**
	 * 是否需要排序，即：排序条件不为空。
	 * 
	 * @return 如果是返回<code>true</code>
	 */
	public boolean isNeedSort() {
		return orderFields != null && orderFields.length > 0;
	}

	/**
	 * 是否需要分页。
	 * 
	 * @return 如果是返回<code>true</code>
	 */
	public boolean isNeedPaging() {
		return !(pageSize == NO_PAGING || pageSize == Integer.MAX_VALUE);
	}
	
	/**
	 * 添加一个查询条件项。<br>
	 * 注：如果是从零开始构造一个查询对象，推荐使用 {@link #builder()}
	 * 
	 * @param item 查询条件项
	 * @return 查询对象
	 */
	public QueryParams<T> addQueryParam(QueryItem item) {
		if (queryItems == null) {
			queryItems = new QueryParamGroup();
		}
		queryItems.addItem(item);
		return this;
	}
	
	/**
	 * 向顶级条件组追加 AND 一个查询条件项。<br>
	 * 如果已有的顶层条件组是 OR 条件，则将在当前顶层条件之上再套一层 AND 条件组：之前的顶级条件组和待添加的条件项为它的两个子条件（关系为 AND）。
	 * 
	 * @param item 查询条件项
	 * @return 查询对象
	 * @since 2.9.0
	 */
	public QueryParams<T> appendAndQueryParam(QueryItem item) {
		if (queryItems == null) {
			queryItems = new QueryParamGroup();
		}
		if (queryItems.getCjt() == QueryParamGroup.Conjunction.OR) {
			queryItems = new QueryParamGroup(QueryParamGroup.Conjunction.AND, queryItems);
		}
		queryItems.addItem(item);
		return this;
	}
	
	/**
	 * 移除指定属性所对应的查询条件，如果有多个，移除第一个。
	 * 
	 * @param fieldName 属性资源名
	 * @return 返回被移除的查询条件，如果没有返回<code>null</code>
	 */
	public QueryParam removeFirstQueryItemByField(String fieldName) {
		return queryItems == null ? null : queryItems.removeFirstQueryItemByField(fieldName);
	}
	
	/**
	 * 移除指定属性所对应的查询条件。
	 * 
	 * @param fieldName 属性资源名
	 */
	public void removeQueryItemsByField(String fieldName) {
		if (queryItems != null) {
			queryItems.removeQueryItemsByField(fieldName);
		}
	}
	
	/**
	 * 查找指定属性所对应的查询条件，如果存在多个，返回第一个。
	 * 
	 * @param fieldName 属性字段名
	 * @return 查询条件，如果没有返回<code>null</code>
	 */
	public QueryParam findFirstQueryItemByField(String fieldName) {
		return queryItems == null ? null : queryItems.findFirstQueryItemByField(fieldName);
	}
	
	/**
	 * 查找指定属性所对应的所有查询条件。
	 * 
	 * @param fieldName 属性字段名
	 * @return 查询条件列表，如果没有返回<code>null</code>
	 */
	public List<QueryParam> findQueryItemsByField(String fieldName) {
		return queryItems == null ? null : queryItems.findQueryItemsByField(fieldName);
	}
	
	/**
	 * 替换指定字段的查询条件。
	 * 
	 * @param group 条件组
	 * @param fieldName 字段名
	 * @param newParamGenerator 负责生成新查询条件
	 */
	public static void replaceQueryParam(QueryParamGroup group, String fieldName, Function<QueryParam, QueryParam> newParamGenerator) {
		if (group == null || !group.hasQueryItem()) {
			return;
		}
		List<QueryItem> items = new ArrayList<>(group.getItems());
		for (int i = 0; i < items.size(); i++) {
			QueryItem item = items.get(i);
			if (item instanceof QueryParam) {
				QueryParam param = (QueryParam) item;
				if (param.getFieldName().equals(fieldName)) {
					items.set(i, newParamGenerator.apply(param));
				}
			} else {
				replaceQueryParam((QueryParamGroup) item, fieldName, newParamGenerator);
			}
		}
		group.setItems(items);
	}
	
	/**
	 * 获取所有查询条件字段的名称。
	 * 
	 * @return 查询条件字段名称集合
	 */
	public Set<String> getQueryFieldNames() {
		if (queryItems == null) {
			return Collections.emptySet();
		}
		return new HashSet<>(getQueryGroupFieldNames(queryItems));
	}

	private Set<String> getQueryGroupFieldNames(QueryParamGroup paramGroup) {
		Set<String> fieldNames = new HashSet<>();
		List<QueryItem> items = paramGroup.getItems();
		if (items == null) {
			return fieldNames;
		}
		for (QueryItem item : items) {
			if (item instanceof QueryParam) {
				fieldNames.add(QueryParam.class.cast(item).getFieldName());
			} else {
				fieldNames.addAll(getQueryGroupFieldNames((QueryParamGroup) item));
			}
		}
		return fieldNames;
	}
	
	
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(", ", "{", "}");
		sj.add("needCount : " + needCount);
		sj.add("pageNum : " + pageNum);
		sj.add("pageSize : " + pageSize);
		sj.add("expectedFields : " + Arrays.toString(expectedFields));
		sj.add("queryItems : " + queryItems);
		return sj.toString();
	}
	
}
