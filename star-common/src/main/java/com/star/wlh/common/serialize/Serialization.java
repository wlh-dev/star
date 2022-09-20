package com.star.wlh.common.serialize;

import com.star.wlh.common.serialize.SerializationFactory.Serializations;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 定义一种序列化、反序列化的方案。
 *
 * @author hesy
 */
public interface Serialization {

	/**
	 * 返回序列化方案的标识。
	 *
	 * @return 编码
	 */
	Serializations getCode();

	/**
	 * 对对象执行序列化操作。
	 *
	 * @param input     待序列化的对象
	 * @param outStream 待序列化的数据流
	 */
	void serialize(Object input, OutputStream outStream);

	/**
	 * 执行反序列化操作。
	 *
	 * @param inputStream 数据流
	 * @param type        期望的对象类型
	 * @return 反序列化后得到的对象
	 */
	<T> T deserialize(InputStream inputStream, Class<T> type);

	/**
	 * 执行反序列化操作。
	 *
	 * @param data 数据
	 * @param type 期望的对象类型
	 * @return 反序列化后得到的对象
	 */
	<T> T deserialize(byte[] data, Class<T> type);

}
