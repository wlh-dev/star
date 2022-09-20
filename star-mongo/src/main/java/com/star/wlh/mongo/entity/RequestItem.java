package com.star.wlh.mongo.entity;

import lombok.Data;

import java.util.List;

/**
 * @author : wlh
 * @date Date : 2022年09月20日 10:21
 */
@Data
public class RequestItem {
	private List<Item> items;
}
