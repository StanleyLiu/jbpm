package com.zl.bgec.basicapi.common.oauth2;


public class UserInfoFroBiz {
	
	private Long id;
	private String nickName;
	private Integer namespaceId;
	private String namespaceUserToken;
	private String telePhone;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getNamespaceId() {
		return namespaceId;
	}
	public void setNamespaceId(Integer namespaceId) {
		this.namespaceId = namespaceId;
	}
	public String getNamespaceUserToken() {
		return namespaceUserToken;
	}
	public void setNamespaceUserToken(String namespaceUserToken) {
		this.namespaceUserToken = namespaceUserToken;
	}
	public String getTelePhone() {
		return telePhone;
	}
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
}
