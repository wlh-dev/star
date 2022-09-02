package com.star.wlh.quartz.entity;

import java.io.Serializable;

/**
 * @author : wlh
 * @date Date : 2022年09月01日 18:57
 */

public class Result<T> implements Serializable {
	private int code;
	private boolean success;
	private String message;
	private T data;

	public static <T> Result<T> success() {
		return success(null);
	}

	public static <T> Result<T> success(T data) {
		return success("success",data);
	}

	public static <T> Result<T> success(String message) {
		return success(message, null);
	}

	public static <T> Result<T> success(String message, T data) {
		return success(200,message, data);
	}

	public static <T> Result<T> success(int code, String message) {
		return success(code, message, null);
	}

	public static <T> Result<T> success(int code, String message, T data) {
		return new Result<T>(code, true, message, data);
	}

	public Result(int code, boolean success, String message, T data) {
		this.code = code;
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public static <T> Result<T> failure() {
		return failure(null);
	}

	public static <T> Result<T> failure(T data) {
		return failure("failure",data);
	}

	public static <T> Result<T> failure(String message) {
		return failure(message, null);
	}

	public static <T> Result<T> failure(String message, T data) {
		return failure(500,message, data);
	}

	public static <T> Result<T> failure(int code, String message) {
		return failure(code, message, null);
	}

	public static <T> Result<T> failure(int code, String message, T data) {
		return new Result<T>(code, false, message, data);
	}

}
