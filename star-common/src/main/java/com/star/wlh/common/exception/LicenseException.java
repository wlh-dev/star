package com.star.wlh.common.exception;

/**
 * 标识因为许可原因而抛出的异常。
 */
public class LicenseException extends ClientException {
	private static final long serialVersionUID = -2966840952809936602L;

	public LicenseException() {
		super("Please check your License");
	}
	public LicenseException(String messageCode, Object...params) {
		super(messageCode, params);
	}
	
}
