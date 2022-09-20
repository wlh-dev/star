package com.star.wlh.common.serialize.jackson;

import com.star.wlh.common.serialize.Serialization;
import com.star.wlh.common.serialize.SerializationFactory.Serializations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author : wlh
 * @date Date : 2022年09月20日 18:32
 */

public class JacksonSerialization implements Serialization {
	private static final Logger logger = LoggerFactory.getLogger(JacksonSerialization.class);

	@Override public Serializations getCode() {
		return Serializations.JACKSON;
	}

	@Override public void serialize(Object input, OutputStream outStream) {

	}

	@Override public <T> T deserialize(InputStream inputStream, Class<T> type) {
		return null;
	}

	@Override public <T> T deserialize(byte[] data, Class<T> type) {
		return null;
	}
}
