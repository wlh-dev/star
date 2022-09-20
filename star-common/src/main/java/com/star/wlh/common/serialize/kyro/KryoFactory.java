package com.star.wlh.common.serialize.kyro;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.util.Pool;
/**
 * @author : wlh
 * @date Date : 2022年09月20日 18:41
 */

public class KryoFactory {
	private static Pool<Kryo> pool = new Pool<Kryo>(true,false,100) {
		@Override protected Kryo create() {
			return new Kryo();
		}
	};


}
