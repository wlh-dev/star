package com.star.wlh.common.serialize.kyro;

import com.esotericsoftware.kryo.Kryo;

/**
 * Kryo 类注册。
 *
 * @author wlh
 */
public interface KryoClassRegistry {

	/**
	 * 指定优先级，优先级高（返回值小）的先被调用
	 * @return int
	 */
	int getPriority();
	
	/**
	 * 向 Kryo 注册类。
	 * 
	 * @param kryo Kryo
	 */
	void register(Kryo kryo);
	
}
