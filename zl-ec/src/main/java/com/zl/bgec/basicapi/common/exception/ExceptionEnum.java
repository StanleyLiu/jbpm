package com.zl.bgec.basicapi.common.exception;

public enum ExceptionEnum {
	REQUEST_NOT_FOUND("1", "未知的请求"), STSTEM("2", "未知的系统异常"), NORMAL("3", "出现异常");
	
	private String code;
	private String content;
	
	private ExceptionEnum(String code,String content){
		this.code=code;
		this.content=content;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
