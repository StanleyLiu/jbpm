package com.zl.bgec.basicapi.common.oauth2;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 * @author elians
 *         <ul>
 *         <li>id:用户id</li>
 *         <li>accountName:用户名</li>
 *         <li>nickName:用户昵称</li>
 *         <li>avatarUrl:用户头像url</li>
 *         <li>avatarUri:用户头像url</li>
 *         <li>statusLine:用户状态</li>
 *         <li>gender:用户性别.0代表未知，1为男性，2为女性</li>
 *         <li>birthday:用户生日</li>
 *         <li>homeTown:家乡id</li>
 *         <li>hometownName:名字</li>
 *         <li>company:公司</li>
 *         <li>school:学校</li>
 *         <li>occupation:职业</li>
 *         <li>communityId:小区ID</li>
 *         <li>communityName:小区名</li>
 *         <li>addressId:地址id</li>
 *         <li>address:地址</li>
 *         <li>phones:手机</li>
 *         <li>emails:邮箱</li>
 *         <li>regionId:城市ID</li>
 *         <li>regionName:城市名</li>
 *         <li>regionPath:城市路径</li>
 *         <li>uuid:用户唯一的标识</li>
 *         </ul>
 **/
public class UserInfo {

    private Long id;
    private String accountName;
    private String nickName;
    private String avatarUrl;
    private String statusLine;
    private Byte gender;
    private String birthday;
    private Long homeTown;
    private String hometownName;
    private String company;
    private String school;
    private String occupation;
    private Long communityId;
    private String communityName;
    private Long addressId;
    private String address;
    private Long regionId;
    private String regionName;
    private String regionPath;
    private String avatarUri;
    private Integer namespaceId;
    private String namespaceUserToken;
    

    private String uuid;

    private List<String> phones;

    private List<String> emails;

    public UserInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Long getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(Long homeTown) {
        this.homeTown = homeTown;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getHometownName() {
        return hometownName;
    }

    public void setHometownName(String hometownName) {
        this.hometownName = hometownName;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public String getAvatarUri() {
        return avatarUri;
    }

    public void setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

	@Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

}
