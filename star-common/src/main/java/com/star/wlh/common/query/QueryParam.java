package com.star.wlh.common.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;

/**
 * 查询条件项，由要查询的字段名、比较条件和值组成。
 */
public class QueryParam implements QueryItem {
	private static final long serialVersionUID = 1L;

	/**
	 * 属性字段，如果是嵌套对象的属性，请加上嵌套对象的字段名。<br>
	 * 
	 * <pre>
	 *  class Outer {
	 *     private String field1;
	 *     private Inner field2;
	 *  }
	 *  
	 *  class Inner {
	 *     private String innerFiled1;
	 *  }
	 *  
	 *  则 innerFiled1 的作为外部对象的查询条件时，field 应该写为：field2.innerField1
	 * </pre>
	 */
	private String field;

	/**
	 * 比较条件，默认为：EQ，即相等
	 */
	private QueryOperator queryOperator = QueryOperator.EQ;

	/**
	 * 条件值，只支持以下数据类型：
	 * <ul>
	 * <li>int</li>
	 * <li>long</li>
	 * <li>float</li>
	 * <li>double</li>
	 * <li>boolean</li>
	 * <li>String</li>
	 * <li>enum</li>
	 * </ul>
	 */
	private List<Serializable> values;

	/**
	 * 属性的数据类型，用于识别特殊字段类型的查询条件
	 */
	private String dataType;

	public QueryParam() {
		// empty
	}

	public QueryParam(String field, QueryOperator queryOperator) {
		this.field = field;
		this.queryOperator = queryOperator;
	}

	public QueryParam(String field, Serializable value) {
		this.field = field;
		this.values = asValueList(value);
	}
	
	public QueryParam(String field, QueryOperator queryOperator, Serializable value) {
		this.field = field;
		this.queryOperator = queryOperator;
		this.values = toParamValue(value);
	}

	public QueryParam(String field, QueryOperator queryOperator, Serializable[] values) {
		this.field = field;
		this.queryOperator = queryOperator;
		this.values = asValueList(values);
	}

	public QueryParam(String field, QueryOperator queryOperator, List<? extends Serializable> values) {
		this.field = field;
		this.queryOperator = queryOperator;
		this.values = values == null ? null : asValueList(values.toArray(new Serializable[values.size()]));
	}

	public String getFieldName() {
		return field;
	}

	public void setFieldName(String fieldName) {
		this.field = fieldName;
	}

	public QueryOperator getQueryOperator() {
		return queryOperator;
	}

	public void setQueryOperator(QueryOperator queryOperator) {
		this.queryOperator = queryOperator;
	}

	private List<Serializable> asValueList(Serializable...values) {
		return toParamValue(values);
	}
	
	public static List<Serializable> toParamValue(Serializable value) {
		if (value == null) {
			return null;
		}
		List<Serializable> valueList = new ArrayList<>();
		if (value.getClass().isArray()) {
			Collections.addAll(valueList, (Serializable[]) value);
		} else if (Collection.class.isInstance(value)) {
			for (Object val : (Collection<?>) value) {
				valueList.add((Serializable) val);
			}
		} else {
			valueList.add(value);
		}
		return valueList;
	}
	
	/**values
	 * 返回第一个值，如果值列表为空，将返回 {@code null}。
	 * 
	 * @return 第一个值
	 */
	public Serializable retrieveFirstValue() {
		return hasValue() ? values.get(0) : null;
	}

	/**
	 * 返回字符串形式的值列表，如果值不是字符串，则将被调用 {@code toString() } 方法。
	 * 
	 * @return 字符串列表
	 */
	public List<String> toStringValues() {
		if (!hasValue()) {
			return null;
		}
		List<String> stringVals = new ArrayList<>();
		for (Serializable val : values) {
			stringVals.add(val == null ? null : val.toString());
		}
		return stringVals;
	}

	public List<Serializable> getValues() {
		return values == null ? null : Collections.unmodifiableList(values);
	}

	public void setValues(List<Serializable> values) {
		this.values = values == null ? null : new ArrayList<>(values);
	}

	/**
	 * 允许通过 Object 来传递值列表。
	 * 
	 * @param values 值列表
	 */
	public void setValue(List<Object> values) {
		if (values == null) {
			return;
		}
		List<Serializable> valueList = new ArrayList<>();
		for (Object object : values) {
			if (Serializable.class.isInstance(object)) {
				valueList.add((Serializable) object);
			}
		}
		this.values = valueList;
	}
	
	/**
	 * 判断是否有值。
	 * 
	 * @return 如果有值返回 {@code true}
	 */
	public boolean hasValue() {
		return (values != null && !values.isEmpty());
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Override
	public String toString() {
		return "{" + field + " " + Objects.toString(this.getQueryOperator(), "EQ") + " " + this.toStringValues() + "}";
	}

}
