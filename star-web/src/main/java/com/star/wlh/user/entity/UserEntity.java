package com.star.wlh.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.star.wlh.user.enums.GenderEnum;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author : wlh
 * @date Date : 2022年08月06日 00:53
 */
@JsonIgnoreProperties
@DefaultSerializer(CompatibleFieldSerializer.class)
@TableName(schema = "study",value = "user")
public class UserEntity implements Serializable {
	/**
	 * 用户id
	 */
	@TableId
	private Serializable userId;
	/**
	 * 用户名称
	 */
	@FieldSerializer.Optional("ignored")
	private Serializable nickName;
	/**
	 * 用户英文名称
	 */
	private Serializable realName;
	/**
	 * 登陆名称
	 */
	private Serializable loginName;
	private Serializable password;
	private GenderEnum gender;

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("UserEntity{");
		sb.append("userId='").append(userId).append('\'');
		sb.append(", nickName='").append(nickName).append('\'');
		sb.append(", realName='").append(realName).append('\'');
		sb.append(", loginName='").append(loginName).append('\'');
		sb.append(", password='").append(password).append('\'');
		sb.append(", gender=").append(gender.toString());
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserEntity that = (UserEntity) o;
		return Objects.equals(userId, that.userId) && Objects.equals(nickName, that.nickName) && Objects.equals(realName, that.realName) && Objects.equals(loginName, that.loginName) && Objects.equals(password, that.password) && gender == that.gender;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, nickName, realName, loginName, password, gender);
	}

	public Serializable getUserId() {
		return userId;
	}

	public void setUserId(Serializable userId) {
		this.userId = userId;
	}

	public Serializable getNickName() {
		return nickName;
	}

	public void setNickName(Serializable nickName) {
		this.nickName = nickName;
	}

	public Serializable getRealName() {
		return realName;
	}

	public void setRealName(Serializable realName) {
		this.realName = realName;
	}

	public Serializable getLoginName() {
		return loginName;
	}

	public void setLoginName(Serializable loginName) {
		this.loginName = loginName;
	}

	public Serializable getPassword() {
		return password;
	}

	public void setPassword(Serializable password) {
		this.password = password;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public UserEntity() {
	}

	public UserEntity(String userId, String nickName, String realName, String loginName, String password, GenderEnum gender) {
		this.userId = userId;
		this.nickName = nickName;
		this.realName = realName;
		this.loginName = loginName;
		this.password = password;
		this.gender = gender;
	}
}
