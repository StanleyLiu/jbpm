package com.zl.bgec.basicapi.common.exception;

public class BaseException extends RuntimeException{
	private String code;
	private String content;
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
	public BaseException( String code, String content) {  
        super();  
        this.code = code;  
        this.content = content;  
    } 
	public BaseException(String message, Throwable cause, String code, String content) {  
        super(message, cause);  
        this.code = code;  
        this.content = content;  
    }  
}
