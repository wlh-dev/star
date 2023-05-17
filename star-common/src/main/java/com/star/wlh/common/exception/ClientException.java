package com.star.wlh.common.exception;

import java.util.Arrays;

/**
 * 由于客户端（API 调用者）原因而中断业务处理，并抛出的异常。<br>
 * 常见的原因有：
 * <ul>
 * <li>提交的参数不合法</li>
 * <li>违反业务处理规则</li>
 * </ul>
 * 
 * @author hesy
 */
public class ClientException extends StarException {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 异常编码，一般为简短的错误描述
	 */
	protected String code;
	
	/**
	 * 参数列表
	 */
	protected Object[] params;  // NOSONAR 这里为了保证兼容旧API，暂不调整为 Serializable 类型，同时 Object 也更清晰，传参时不容易混淆
	
	public ClientException() {
		super("");
	}

	public ClientException(String message) {
		this(message, message, (Object[]) null);
	}
	
	public ClientException(String code, Object[] params) {
		super((params == null || params.length == 0) ? code : String.join(" : ",  code, Arrays.toString(params)));
		this.code = code;
		this.params = params;
	}

	public ClientException(String message, String code, Object...params) {
		super(message, null);
		this.code = code;
		this.params = params;
	}
	
	public ClientException setParams(Object...params) {
		this.params = params;
		return this;
	}
	
	public String getCode() {
		return code;
	}
	
	public Object[] getParams() {
		return params;
	}
	
	public static ClientException create(String code, Object...params) {
		return new ClientException(code, params);
	}
	
	@Override
	public String getMessage() {
		String message = super.getMessage();
		if (message == null && code != null) {
			return (params == null || params.length == 0) ? this.code : String.join(" : ",  this.code, getStringParams(params));
		}
    return message;
	}
	
	private String getStringParams(Object[] params) {
		if (params == null || params.length == 0) {
			return "";
		}
		return params.length == 1 ? params[0].toString() : Arrays.toString(params);
	}
	
}
