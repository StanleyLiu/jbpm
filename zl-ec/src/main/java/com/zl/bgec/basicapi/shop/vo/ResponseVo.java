package com.zl.bgec.basicapi.shop.vo;

import java.util.List;

public class ResponseVo {
	public String version;
	public int errorCode;
	public List<CategoryDTO> response;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public List<CategoryDTO> getResponse() {
		return response;
	}
	public void setResponse(List<CategoryDTO> response) {
		this.response = response;
	}
	
	
}
