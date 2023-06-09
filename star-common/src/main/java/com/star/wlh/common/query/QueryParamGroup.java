package com.star.wlh.common.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;

/**
 * 查询条件组。
 * 
 * @author hesy
 */
@DefaultSerializer(CompatibleFieldSerializer.class)
public class QueryParamGroup implements QueryItem, Iterable<QueryItem> {
	private static final long serialVersionUID = 1L;

	/**
	 * 条件查询项间的关系
	 */
	public enum Conjunction { AND, OR }
	
	/**
	 * 集合条件，默认为 AND
	 */
	private Conjunction cjt = Conjunction.AND;
	
	/**
	 * 条件项集合
	 */
	private ArrayList<QueryItem> items;
	
	public QueryParamGroup() {
		// empty
	}
	
	public QueryParamGroup(List<QueryItem> items) {
		if (items != null && !items.isEmpty()) {
			this.items = new ArrayList<>(items);
		}
	}

	public QueryParamGroup(Conjunction cjt, List<QueryItem> items) {
		this.cjt = cjt;
		if (items != null && !items.isEmpty()) {
			this.items = new ArrayList<>(items);
		}
	}
	
	public QueryParamGroup(QueryItem...items) {
		if (items != null && items.length > 0) {
			this.items = new ArrayList<>(Arrays.asList(items));
		}
	}
	
	public QueryParamGroup(Conjunction cjt, QueryItem...items) {
		this.cjt = cjt;
		if (items != null && items.length > 0) {
			this.items = new ArrayList<>(Arrays.asList(items));
		}
	}
	
	public QueryParamGroup(QueryParamGroup group) {
		if (group != null) {
			this.cjt =  group.getCjt();
			this.items = group.hasQueryItem() ? new ArrayList<>(group.getItems()) : null;
		}
	}
	
	public void setCjt(Conjunction cjt) {
		this.cjt = cjt;
	}

	public void setItems(List<QueryItem> items) {
		this.items = items == null ? null : new ArrayList<>(items);
	}

	public Conjunction getCjt() {
		return cjt;
	}
	
	public List<QueryItem> getItems() {
		return items == null ? null : Collections.unmodifiableList(items);
	}
	
	public QueryParamGroup addItem(QueryItem item) {
		if (items == null) {
			items = new ArrayList<>();
		}
		items.add(item);
		return this;
	}
	
	public QueryParamGroup addItems(QueryItem...queryItems) {
		if (queryItems == null || queryItems.length == 0) {
			return this;
		}
		if (items == null) {
			items = new ArrayList<>();
		}
		for (QueryItem item : queryItems) {
			items.add(item);
		}
		return this;
	}
	
	
	@Override
	public Iterator<QueryItem> iterator() {
		return items == null ? null : items.iterator();
	}
	
	public boolean hasQueryItem() {
		return items != null && !items.isEmpty();
	}
	
	/**
	 * 移除指定属性所对应的查询条件，如果有多个，移除第一个。
	 * 
	 * @param fieldName 属性资源名
	 * @return 返回被移除的查询条件，如果没有返回<code>null</code>
	 */
	public QueryParam removeFirstQueryItemByField(String fieldName) {
		if (items == null) {
			return null;
		}
		QueryParam param = null;
		for (Iterator<QueryItem> it = items.iterator(); it.hasNext();) {
			QueryItem item = it.next();
			if (item instanceof QueryParamGroup) {
				QueryParamGroup group = (QueryParamGroup) item;
				param = group.removeFirstQueryItemByField(fieldName);
				if (!group.hasQueryItem()) {
					it.remove(); // 移除空的查询条件组
				}
				if (param != null) {
					return param;
				}
				continue;
			}
			param = (QueryParam) item;
			if (param.getFieldName().equals(fieldName)) {
				it.remove();
				return param;
			}
		}
		return null;
	}
	
	/**
	 * 移除指定属性所对应的查询条件。
	 * 
	 * @param fieldName 属性资源名
	 */
	public void removeQueryItemsByField(String fieldName) {
		if (items == null) {
			return;
		}
		for (Iterator<QueryItem> it = items.iterator(); it.hasNext();) {
			QueryItem item = it.next();
			if (item instanceof QueryParamGroup) {
				QueryParamGroup group = (QueryParamGroup) item;
				group.removeQueryItemsByField(fieldName);
				if (!group.hasQueryItem()) {
					it.remove();  // 移除空的查询条件组
				}
				continue;
			}
			QueryParam param = (QueryParam) item;
			if (param.getFieldName().equals(fieldName)) {
				it.remove();
			}
		}
	}

	/**
	 * 查找指定属性所对应的查询条件，如果存在多个，返回第一个。
	 * 
	 * @param fieldName 属性字段名
	 * @return 查询条件，如果没有返回<code>null</code>
	 */
	public QueryParam findFirstQueryItemByField(String fieldName) {
		if (items == null) {
			return null;
		}
		QueryParam param;
		for (Iterator<QueryItem> it = items.iterator(); it.hasNext();) {
			QueryItem item = it.next();
			if (item instanceof QueryParamGroup) {
				param = ((QueryParamGroup) item).findFirstQueryItemByField(fieldName);
				if (param != null) {
					return param;
				}
			} else {
				param = (QueryParam) item;
				if (param.getFieldName().equals(fieldName)) {
					return param;
				}
			}
		}
		return null;
	}
	
	/**
	 * 查找指定属性所对应的所有查询条件。
	 * 
	 * @param fieldName 属性字段名
	 * @return 查询条件列表，如果没有返回<code>null</code>
	 */
	public List<QueryParam> findQueryItemsByField(String fieldName) {
		if (items == null) {
			return null;
		}
		List<QueryParam> params = new ArrayList<>();
		for (Iterator<QueryItem> it = items.iterator(); it.hasNext();) {
			QueryItem item = it.next();
			if (item instanceof QueryParamGroup) {
				List<QueryParam> list = ((QueryParamGroup) item).findQueryItemsByField(fieldName);
				if (list != null) {
					params.addAll(list);
				}
			} else {
				QueryParam param = (QueryParam) item;
				if (param.getFieldName().equals(fieldName)) {
					params.add(param);
				}
			}
		}
		return params.isEmpty() ? null : params;
	}
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(", ", "{", "}");
		sj.add("cjt:" + cjt);
		if (items != null && !items.isEmpty()) {
			StringJoiner params = new StringJoiner(", ", "[", "]");
			items.forEach(param -> sj.add(String.valueOf(param)));
			sj.add("items : " + params.toString());
		}
		return sj.toString();
	}

}
