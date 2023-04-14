package com.star.wlh.user.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;
import java.util.Iterator;

@JsonComponent
public class UserEntityDeserializer extends JsonObjectDeserializer<UserEntity> {
    private static final Logger logger = LoggerFactory.getLogger(UserEntityDeserializer.class);



    @Override
    protected UserEntity deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) throws IOException {
        return deserializeResObject(null, codec, tree);
    }

    private UserEntity deserializeResObject(UserEntity object, ObjectCodec codec, JsonNode tree) {
        if (object==null){
            object = new UserEntity();
        }
        for (Iterator<String> it = tree.fieldNames(); it.hasNext();) {
            String fieldName = it.next();
            switch (fieldName) {
                case "userId":
                    object.setUserId(super.nullSafeValue(tree.get(fieldName),String.class));
                    break;
                default:

            }
        }
        return object;
    }
}
