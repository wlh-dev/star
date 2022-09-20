package com.star.wlh.mongo.entity;

import cn.hutool.json.JSONObject;
import lombok.Data;
import org.bson.types.ObjectId;

/**
 * @author : wlh
 * @date Date : 2022年09月20日 11:15
 */

@Data
public class ResSubObject {
	private ObjectId id;
	private String tenantId;
	private String ciId;
	private String subClassCode;
	private String parentId;
	private String identifier;
	private Integer objectVersion;
	private Boolean onlyOne;
	private JSONObject attrValues;

}
