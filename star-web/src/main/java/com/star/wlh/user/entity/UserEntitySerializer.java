package com.star.wlh.user.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectSerializer;

import java.io.IOException;

@JsonComponent
public class UserEntitySerializer extends JsonObjectSerializer<UserEntity> {
    private static final Logger logger = LoggerFactory.getLogger(UserEntitySerializer.class);

    @Override
    protected void serializeObject(UserEntity value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        if (value == null) {
            return;
        }
        try {
            writeResObject(value, jgen);
        } catch (IOException e) {
            logger.error("Failed to serialize resObject", e);
            throw e;
        }
    }
    private void writeResObject(UserEntity value, JsonGenerator jgen) throws IOException {
        jgen.writeObjectField("userId", value.getId());
        /*jgen.writeObjectField(ResAttrFieldCodes.BASE_FIELD_TENANT, value.getTenantId());
        jgen.writeObjectField(ResAttrFieldCodes.BASE_FIELD_CLASS_CODE, value.getClassCode());
        if (value.getClassName() != null) {
            jgen.writeObjectField(ResAttrFieldCodes.BASE_FIELD_CLASS_NAME, value.getClassName());
        }
        jgen.writeObjectField(ResAttrFieldCodes.BASE_FIELD_VERSION, value.getObjectVersion());
        jgen.writeObjectField("state", value.getChangeState());
        if (value.getCreateTime() != null) {
            jgen.writeObjectField(ResAttrFieldCodes.BASE_FIELD_CREATE_TIME,
                    DateUtils.getDateFormat(DateUtils.DATE_TIME_FORMAT).format(value.getCreateTime()));
        }
        if (value.getUpdateTime() != null) {
            jgen.writeObjectField(ResAttrFieldCodes.BASE_FIELD_UPDATE_TIME,
                    DateUtils.getDateFormat(DateUtils.DATE_TIME_FORMAT).format(value.getUpdateTime()));
        }
        if (value.getOuterObjectId() != null) {
            jgen.writeObjectField(ResAttrFieldCodes.BASE_FIELD_OUTER_OBJECT_ID, value.getOuterObjectId());
        }
        List<ResSource> sources = value.getSources();
        if (sources != null) {
            jgen.writeObjectField(ResAttrFieldCodes.BASE_FIELD_SOURCES, sources.stream().map(ResSource::getCode).toArray());
        }
        List<Tag> tags = value.getTags();
        if (tags != null) {
            jgen.writeObjectField(ResAttrFieldCodes.BASE_FIELD_TAGS, tags.stream().map(Tag::getIdentifer).toArray());
        }
        jgen.writeObjectField(ResAttrFieldCodes.BASE_FIELD_OWNERS, value.getResOwners());

        // 扩展属性
        value.getAttrValues().forEach((code, attrVal) -> {
            if (attrVal != null && attrVal.getValue() != null) {
                try {
                    jgen.writeObjectField(code, attrVal.getValue());
                } catch (Exception e) {
                    logger.warn("Error to write attr: {}", attrVal.toString(), e);
                }
            }
        });*/
    }
}
