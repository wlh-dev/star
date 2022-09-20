package com.star.wlh.mongo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : wlh
 * @date Date : 2022年09月06日 19:04
 */
@Data
public class PrimarySwitchEntity implements Serializable {
	private String mongoId;
	private String serialNumber;
	private String deviceSerialNumber;
}
