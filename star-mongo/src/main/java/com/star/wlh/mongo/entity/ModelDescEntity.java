package com.star.wlh.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 存放类型、模版、分层的描述信息
 * @author zhanglb 
 * Create at 2021年10月14日 下午4:17:39
 */
@Document(collection = "modelDescEntity")
@CompoundIndexes({@CompoundIndex(name = "tenant_code_type", unique = true, def = "{'tenantId':1, 'code':1, 'type':1}")})
public class ModelDescEntity implements Serializable {
	/**
	 * <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;

	private String tenantId;
  @Indexed
	private String modelId;

	private String code;
	
	private String type;
	
	private String desc = "";
	
	public static String TYPE_LAYER = "layer";
	public static String TYPE_CLASS = "class";
	public static String TYPE_TEMPLETE = "templete";
	
	public ModelDescEntity() {
		
	}
	
	public ModelDescEntity(String code, String type, String desc, String modelId){
		this.code = code;
		this.type = type;
		this.desc = desc;
		this.modelId = modelId;
	}
	
	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return tenantId
	 */
	public String getTenantId() {
		return tenantId;
	}
	/**
	 * @param tenantId
	 */
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	/**
	 * @return code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return modelId
	 */
	public String getModelId() {
		return modelId;
	}

	/**
	 * @param modelId
	 */
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	
	
}
