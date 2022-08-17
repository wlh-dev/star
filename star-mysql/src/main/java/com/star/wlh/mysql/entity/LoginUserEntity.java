package com.star.wlh.mysql.entity;

import cn.hutool.json.JSONUtil;
import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author : wlh
 * @date Date : 2022年08月06日 00:53
 */
@DefaultSerializer(CompatibleFieldSerializer.class)
public class LoginUserEntity implements Serializable {
	private String userId;
	private String userName;
	private String englishName;
	private String loginName;
	private String password;
	private String accessKey;
	private String accessKeySecret;

	public LoginUserEntity() {
	}

	public LoginUserEntity(String userId, String userName, String englishName, String loginName, String password, String accessKey,
					String accessKeySecret) {
		this.userId = userId;
		this.userName = userName;
		this.englishName = englishName;
		this.loginName = loginName;
		this.password = password;
		this.accessKey = accessKey;
		this.accessKeySecret = accessKeySecret;
	}

	@Override public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LoginUserEntity that = (LoginUserEntity) o;
		return Objects.equals(userId, that.userId) && Objects.equals(userName, that.userName) && Objects.equals(englishName, that.englishName)
						&& Objects.equals(loginName, that.loginName) && Objects.equals(password, that.password) && Objects.equals(accessKey,
						that.accessKey) && Objects.equals(accessKeySecret, that.accessKeySecret);
	}

	@Override public int hashCode() {
		return Objects.hash(userId, userName, englishName, loginName, password, accessKey, accessKeySecret);
	}

	@Override public String toString() {
		return JSONUtil.toJsonStr(this);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
}
