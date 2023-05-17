package com.star.wlh.common.exception;

/**
 * 标识因为许可原因而抛出的异常。
 */
public class LicenseException extends ClientException {  // NOSONAR 必须有这么多层次的继承
	private static final long serialVersionUID = -2966840952809936602L;

	public LicenseException() {
		super("");
	}
	public LicenseException(String messageCode, Object...params) {
		super(messageCode, params);
	}
	
}
