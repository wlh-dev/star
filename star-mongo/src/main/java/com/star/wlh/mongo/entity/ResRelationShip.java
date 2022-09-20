package com.star.wlh.mongo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.star.wlh.mongo.base.Constants;

import java.util.Date;
import java.util.StringJoiner;

/**
 *
 * @Author fengh
 * @Date 2021/10/22 15:50
 * @Description
 */
public class ResRelationShip {
    private Object id;
	private String tenantId;
	private String srcId;
	private String srcClzCode;
	private String dstId;
	private String dstClzCode;
	private String typeCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public ResRelationShip(){}

	public ResRelationShip(String srcId, String srcClzCode, String dstId, String dstClzCode, String typeCode) {
		this.srcId = srcId;
		this.srcClzCode = srcClzCode;
		this.dstId = dstId;
		this.dstClzCode = dstClzCode;
		this.typeCode = typeCode;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getSrcId() {
		return srcId;
	}

	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}

	public String getSrcClzCode() {
		return srcClzCode;
	}

	public void setSrcClzCode(String srcClzCode) {
		this.srcClzCode = srcClzCode;
	}

	public String getDstId() {
		return dstId;
	}

	public void setDstId(String dstId) {
		this.dstId = dstId;
	}

	public String getDstClzCode() {
		return dstClzCode;
	}

	public void setDstClzCode(String dstClzCode) {
		this.dstClzCode = dstClzCode;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String clzKey() {
		return new StringJoiner(Constants.KEY_LINE).add(srcClzCode).add(dstClzCode).add(typeCode).toString();
	}

	public String key() {
		return new StringJoiner(Constants.KEY_LINE).add(srcId).add(dstId).add(typeCode).toString();
	}

	public String unique1() {
		return this.unique(srcId, srcClzCode, dstId, dstClzCode, typeCode);
	}

	public String unique2() {
		return this.unique(dstId, dstClzCode, srcId, srcClzCode, typeCode);
	}

	public String unique(String srcId, String srcClzCode, String dstId, String dstClzCode, String typeCode) {
		return new StringJoiner(Constants.KEY_LINE)
				.add(srcId).add(srcClzCode)
				.add(dstId).add(dstClzCode)
				.add(typeCode).toString();
	}
}
