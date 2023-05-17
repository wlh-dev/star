package com.star.wlh.common.serialize.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.wlh.common.exception.ServerException;
import com.star.wlh.common.serialize.Serialization;
import com.star.wlh.common.serialize.SerializationFactory.Serializations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author : wlh
 * @date Date : 2022年09月20日 18:32
 */

public class JacksonSerialization implements Serialization {
	private static final Logger logger = LoggerFactory.getLogger(JacksonSerialization.class);
	private ObjectMapper mapper = new ObjectMapper();
	@Override public Serializations getCode() {
		return Serializations.JACKSON;
	}

	@Override public void serialize(Object input, OutputStream outStream) {

	}

	@Override public <T> T deserialize(InputStream inputStream, Class<T> type) {
		try {
			return mapper.readValue(inputStream, type);
		} catch (IOException e) {
			throw new ServerException("Failed to deserialize", e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				logger.debug("Failed to close stream", e);
			}
		}
	}

	@Override public <T> T deserialize(byte[] inputStream, Class<T> type) {

		try {
			return mapper.readValue(inputStream, type);
		} catch (IOException e) {
			throw new ServerException("Failed to deserialize", e);
		}
	}
}
