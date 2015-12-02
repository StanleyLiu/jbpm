package com.zl.bgec.basicapi.common.oauth2;


public class UserDefaultAddressRestResponse {
	public String version;
	public String errorScope;
	public Integer errorCode;
	public String errorDescription;
	public UserServiceAddressDTO response;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getErrorScope() {
		return errorScope;
	}
	public void setErrorScope(String errorScope) {
		this.errorScope = errorScope;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public UserServiceAddressDTO getResponse() {
		return response;
	}
	public void setResponse(UserServiceAddressDTO response) {
		this.response = response;
	}
	
	
	
}
