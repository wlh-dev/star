package com.star.wlh.mongo.entity;

import cn.hutool.json.JSONUtil;
import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * @author : wlh
 * @date Date : 2022年08月17日 16:55
 */

@DefaultSerializer(CompatibleFieldSerializer.class)
@Document(collation = "resObject")
public class ResObject {
	@Id
	private String id;
	private String classCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	@Override public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ResObject resObject = (ResObject) o;
		return Objects.equals(id, resObject.id) && Objects.equals(classCode, resObject.classCode);
	}

	@Override public int hashCode() {
		return Objects.hash(id, classCode);
	}

	@Override public String toString() {
		return JSONUtil.toJsonStr(this);
	}
}
