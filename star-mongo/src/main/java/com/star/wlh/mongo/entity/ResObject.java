package com.star.wlh.mongo.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author fengh
 * @Date 2021/4/12 19:08
 * @Description
 */
@Data
@Document(collection = "resObject")
public class ResObject {

	@Id
	private ObjectId id;
	private String tenantId;
	private String classCode;
	private boolean deleted;
	private String keyIdentifiersText;
	private List<String> keyIdentifiers;
	private List<Source> sources;
	private AttrValues attrValues;
	private List<Tag> tags;
	private Date createTime;
	private String createSource;
	private Date updateTime;

	@Data
	public static class Change {
		private String state;
		private boolean conflict;
		private Date time;
	}

	@Data
	public static class ResState {
		private int state;
	}

	@Data
	public static class Source {
		private String code;
		private Date time;
	}

	@Data
	public static class ValueStatstc {
		private int auto;
		private int total;
		private int filled;
	}

	public static class AttrValues extends HashMap<String, AttrValue> {

	}

	@Data
	public static class AttrValue {
		@Field("T")
		private Object t;
		@Field("V")
		private Object v;
		@Field("S")
		private String s;
	}
}
