package com.star.wlh.common.serialize;

import java.util.EnumMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * 一个简单的序列化工厂类。
 *
 * @author hesy
 */
public class SerializationFactory {
	private Map<Serializations, Serialization> serializations;

	/**
	 * 可用的序列化方案。
	 */
	public enum Serializations {
		/**
		 * kryo序列化
		 */
		KRYO,
		/**
		 * jackson序列化
		 */
		JACKSON
	}

	private static class InstanceHolder {
		private static final SerializationFactory INSTANCE = new SerializationFactory();

		private InstanceHolder() {
		}
	}

	private SerializationFactory() {
		serializations = new EnumMap<>(Serializations.class);
		ServiceLoader<Serialization> result = ServiceLoader.load(Serialization.class);
		for (Serialization s : result) {
			serializations.put(s.getCode(), s);
		}
	}

	/**
	 * 获取序列化方案。
	 *
	 * @param serialization 序列化方案的编码，由实现者确定
	 * @return 序列化方案
	 */
	public static Serialization getSerialization(Serializations serialization) {
		return InstanceHolder.INSTANCE.serializations.get(serialization);
	}

	/**
	 * 返回默认的序列化方案。
	 *
	 * @return 序列化方案
	 */
	public static Serialization getDefault() {
		return InstanceHolder.INSTANCE.serializations.get(Serializations.KRYO);
	}
}
