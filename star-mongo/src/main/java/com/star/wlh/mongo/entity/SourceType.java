package com.star.wlh.mongo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : wlh
 * @date Date : 2022年08月24日 10:46
 */

@Data
public class SourceType implements Serializable {
	private String classCode;
	private String createSource;
}
