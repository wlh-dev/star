package com.star.wlh.common.query;

import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 查询结果对象，包含分页信息。
 * 
 * @author hesy Create at 2016年5月10日 下午1:27:13
 */
@DefaultSerializer(CompatibleFieldSerializer.class)
public class QueryResult<T extends Serializable> implements Iterable<T>, Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 总记录数
	 */
	private long totalRecords = -1;

	/**
	 * 当前页记录列表
	 */
	private List<T> dataList;

	/**
	 * 用于存储一些底层存储库分页查询时的游标，例如 elasticsearch 中的 scroll_id
	 */
	private String cursorId;

	public QueryResult() {
		this.dataList = Collections.emptyList();
	}

	public QueryResult(List<T> dataList) {
		if (dataList != null) {
			this.dataList = new ArrayList<>(dataList);
		} else {
			this.dataList = new ArrayList<>();
		}
	}

	/**
	 * 返回符合查询条件的对象总数。
	 * 
	 * @return 对象总数，未统计总数时返回 -1
	 */
	public long getTotalRecords() {
		return totalRecords;
	}

	/**
	 * 设置符合查询条件的对象总数。
	 * 
	 * @param totalRecords 对象总数
	 */
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * 返回不可修改的数据列表。
	 * 
	 * @return 数据列表
	 */
	public List<T> getDataList() {
		return Collections.unmodifiableList(dataList);
	}

	/**
	 * 设置数据列表。
	 * 
	 * @param dataList 数据列表
	 */
	public void setDataList(List<T> dataList) {
		if (dataList != null) {
			this.dataList = new ArrayList<>(dataList);
		} else {
			this.dataList = new ArrayList<>();
		}
	}

	/**
	 * 返回列表中的元素个数。
	 * 
	 * @return 元素个数
	 */
	public int size() {
		return dataList.size();
	}

	/**
	 * 判断是否为空列表。
	 * 
	 * @return 如果是返回 {@code true }，否则返回 {@code false }
	 */
	public boolean isEmpty() {
		return dataList.isEmpty();
	}

	/**
	 * 返回列表中的最后一个对象。
	 * 
	 * @return 最后的对象
	 */
	public T getLast() {
		return dataList.isEmpty() ? null : dataList.get(dataList.size() - 1);
	}
	
	public String getCursorId() {
		return cursorId;
	}

	public void setCursorId(String cursorId) {
		this.cursorId = cursorId;
	}

	@Override
	public Iterator<T> iterator() {
		return dataList.iterator();
	}

}
