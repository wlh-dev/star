package com.star.wlh.common.exception;

import java.util.Arrays;

/**
 * 标识由于客户端提交的参数非法而导致的异常。
 * 
 * @author hesy
 */
public class InvalidArgumentException extends ClientException {  // NOSONAR 必须有这么多层次的继承
	private static final long serialVersionUID = 1L;
	public static final String CODE_ARG_REQUIRED = "pacific.common.argument.required";
	public static final String CODE_ARG_INVALID = "pacific.common.argument.invalid";
	
	public InvalidArgumentException(String code, Object...params) {
		super(code, params);
	}
	
	/**
	 * 标识指定的参数值缺失。
	 * 
	 * @param argName 参数名
	 * @return 异常
	 */
	public static InvalidArgumentException requireArg(String argName) {
		return new InvalidArgumentException(CODE_ARG_REQUIRED, argName);
	}
	
	/**
	 * 标识指定的参数值不合法。
	 * 
	 * @param argName 参数名
	 * @param argVal 参数值
	 * @return 异常
	 */
	public static InvalidArgumentException invalidArgValue(String argName, Object argVal) {
		if (argVal == null) {
			return requireArg(argName);
		}
		return new InvalidArgumentException(CODE_ARG_INVALID, argName, argVal);
	}
	
	/**
	 * 直接根据消息创建。
	 * 
	 * @param message 消息内容
	 * @param args 参数值
	 * @return 异常
	 */
	public static InvalidArgumentException fromMessage(String message, Object...args) {
		return new InvalidArgumentException(String.format(message, args));
	}

	@Override
	public InvalidArgumentException setParams(Object...params) {
		this.params = params;
		return this;
	}
	
	/*
	 * 设置消息内容。
	 * 
	 * @deprecated 下个版本删除
	 */
	@SuppressWarnings({ "unused" })
	private void setMessage(String code, Object...params) {
		if (params == null || params.length == 0) {
			return;
		}
		if (params.length == 1) {
			setMessage(code + ": " + params[0]);
		} else {
			setMessage(code + ": " + Arrays.toString(params));
		}
	}
}
