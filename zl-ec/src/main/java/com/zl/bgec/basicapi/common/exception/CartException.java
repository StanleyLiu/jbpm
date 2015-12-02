package com.zl.bgec.basicapi.common.exception;

public class CartException extends BaseException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8964297001603735378L;

	public CartException(String code, String content) {
		super(code, content);
	}

}
