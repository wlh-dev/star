package com.star.wlh.common.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : wlh
 * @date Date : 2022年09月01日 18:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
	private int code;
	private boolean success;
	private String message;
	private T data;
}
