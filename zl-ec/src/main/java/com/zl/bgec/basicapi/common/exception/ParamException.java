package com.zl.bgec.basicapi.common.exception;

public class ParamException extends BaseException{

	
	public ParamException(String code,
			String content) {
		super(code, content);
	}
	
	public ParamException(String message, Throwable cause, String code,
			String content) {
		super(message, cause, code, content);
	}

}
