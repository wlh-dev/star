package com.star.wlh.common.serialize.kyro;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.star.wlh.common.serialize.Serialization;
import com.star.wlh.common.serialize.SerializationFactory.Serializations;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author wlh
 */
public class KryoSerialization implements Serialization {

	@Override public Serializations getCode() {
		return Serializations.KRYO;
	}

	@Override public void serialize(Object object, OutputStream outStream) {
		Output output = new Output(outStream);
		try {
			KryoFactory.execute(kryo -> {
				kryo.writeObject(output, object);
				return null;
			});
			output.flush();
		}finally {
			output.close();
		}
	}

	@Override public <T> T deserialize(InputStream inputStream, Class<T> type) {
		try (Input input = new Input(inputStream)) {
			return KryoFactory.execute(kryo -> type.cast(kryo.readClassAndObject(input)));
		}
	}

	@Override public <T> T deserialize(byte[] data, Class<T> type) {
		try (Input input = new Input(data)) {
			return KryoFactory.execute(kryo -> type.cast(kryo.readClassAndObject(input)));
		}
	}
}