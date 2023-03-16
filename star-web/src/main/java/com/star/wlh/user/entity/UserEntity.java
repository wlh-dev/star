package com.star.wlh.user.entity;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author : wlh
 * @date Date : 2022年08月06日 00:53
 */
@DefaultSerializer(CompatibleFieldSerializer.class)
@TableName(schema = "study",value = "user")
public class UserEntity implements Serializable {
	/**
	 * 用户id
	 */
	@TableId
	private String userId;
	/**
	 * 用户名称
	 */
	private String nickName;
	/**
	 * 用户英文名称
	 */
	private String realName;
	/**
	 * 登陆名称
	 */
	private String loginName;

	public UserEntity() {
	}

	@Override public String toString() {
		return new StringJoiner(", ", UserEntity.class.getSimpleName() + "[", "]").add("userId='" + userId + "'")
				.add("nickName='" + nickName + "'").add("realName='" + realName + "'").add("loginName='" + loginName + "'").toString();
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserEntity that = (UserEntity) o;
		return Objects.equals(userId, that.userId) && Objects.equals(nickName, that.nickName) && Objects.equals(realName, that.realName)
				&& Objects.equals(loginName, that.loginName);
	}

	@Override public int hashCode() {
		return Objects.hash(userId, nickName, realName, loginName);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public UserEntity(String userId, String nickName, String realName, String loginName) {
		this.userId = userId;
		this.nickName = nickName;
		this.realName = realName;
		this.loginName = loginName;
	}
}
