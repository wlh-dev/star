package com.star.wlh.mongo.entity;

import lombok.Data;

/**
 * @author : wlh
 * @date Date : 2022年09月20日 10:24
 */
@Data
public class Item {
	private String item;
	private Boolean success;
	private String message;
	private Object data;
}
