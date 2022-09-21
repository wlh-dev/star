package com.star.wlh.common.serialize.kyro;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.pool.KryoCallback;
import com.esotericsoftware.kryo.pool.KryoPool;
import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer;
import com.google.common.collect.ImmutableMap;
import de.javakaffee.kryoserializers.ArraysAsListSerializer;
import de.javakaffee.kryoserializers.CollectionsEmptyListSerializer;
import de.javakaffee.kryoserializers.CollectionsEmptyMapSerializer;
import de.javakaffee.kryoserializers.CollectionsEmptySetSerializer;
import de.javakaffee.kryoserializers.CollectionsSingletonListSerializer;
import de.javakaffee.kryoserializers.CollectionsSingletonMapSerializer;
import de.javakaffee.kryoserializers.CollectionsSingletonSetSerializer;
import de.javakaffee.kryoserializers.SynchronizedCollectionsSerializer;
import de.javakaffee.kryoserializers.UnmodifiableCollectionsSerializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * kryo工厂类
 * @author : wlh
 * @date Date : 2022年09月20日 18:41
 */

public class KryoFactory {

	private static final KryoPool POOL = new KryoPool.Builder(KryoFactory::createKryo).softReferences().build();

	private KryoFactory() {
	}

	/**
	 * 创建 Kryo 实例。
	 *
	 * @return Kryo
	 */
	private static Kryo createKryo() {
		Kryo kryo = new Kryo();
		registerClasses(kryo);
		return kryo;
	}

	public static void init(int kryoInstancesCapacity) {
		for (int i = 0; i < kryoInstancesCapacity; i++) {
			execute(kryo -> null);
		}
	}

	/**
	 * @param callback callback
	 * @param <T>      t
	 * @return <T>
	 */
	public static <T> T execute(KryoCallback<T> callback) {
		Kryo borrow = POOL.borrow();
		try {
			return callback.execute(borrow);
		} finally {
			POOL.release(borrow);
		}

	}

	/**
	 * 注册类
	 *
	 * @param kryo kryo
	 */
	public static void registerClasses(Kryo kryo) {
		kryo.setDefaultSerializer(TaggedFieldSerializer.class);
		// kryo对多引用对象的优化
		kryo.setReferences(true);
		kryo.register(ImmutableMap.class);
		// 增加以下类型的自定义序列化实现
		kryo.register(Arrays.asList("").getClass(), new ArraysAsListSerializer());
		kryo.register(Collections.emptyList().getClass(), new CollectionsEmptyListSerializer());
		kryo.register(Collections.emptyMap().getClass(), new CollectionsEmptyMapSerializer());
		kryo.register(Collections.emptySet().getClass(), new CollectionsEmptySetSerializer());
		kryo.register(Collections.singletonList("").getClass(), new CollectionsSingletonListSerializer());
		kryo.register(Collections.singleton("").getClass(), new CollectionsSingletonSetSerializer());
		kryo.register(Collections.singletonMap("", "").getClass(), new CollectionsSingletonMapSerializer());
		UnmodifiableCollectionsSerializer.registerSerializers(kryo);
		SynchronizedCollectionsSerializer.registerSerializers(kryo);

		// 扩展的注册
		ServiceLoader<KryoClassRegistry> registries = ServiceLoader.load(KryoClassRegistry.class);
		List<KryoClassRegistry> list = new ArrayList<>();
		for (KryoClassRegistry registry : registries) {
			list.add(registry);
		}

		// 先排序，确保不同的实现类注册的顺序一致
		list.sort(Comparator.comparingInt(KryoClassRegistry::getPriority));
		for (KryoClassRegistry registry : list) {
			registry.register(kryo);
		}
	}

}
