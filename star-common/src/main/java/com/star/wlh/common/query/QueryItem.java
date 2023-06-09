package com.star.wlh.common.query;

import java.io.Serializable;

import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;

/**
 * 查询条件项目，目前仅仅是一个标识接口。
 * 
 * @author hesy
 */
public interface QueryItem extends Serializable {
	
	final String ID_FIELD = "id"; // NOSONAR 为保持兼容，不能提取到枚举类中

}
