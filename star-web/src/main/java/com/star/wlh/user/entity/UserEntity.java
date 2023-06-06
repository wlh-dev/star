package com.star.wlh.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.star.wlh.config.MybatisJsonTypeHandler;
import com.star.wlh.user.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;
import org.apache.kafka.common.protocol.types.Field;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author : wlh
 * @date Date : 2022年08月06日 00:53
 */
@JsonIgnoreProperties
@Data
@NoArgsConstructor
@AllArgsConstructor
@DefaultSerializer(CompatibleFieldSerializer.class)
@TableName(schema = "study",value = "user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = -6564022808907262054L;
	/**
	 * 用户id
	 */
	@TableId()
	@TableField(value = "USER_ID",jdbcType = JdbcType.VARCHAR)
	private String userId;
	/**
	 * 用户名称
	 */
	@TableField(value = "NICK_NAME",typeHandler = MybatisJsonTypeHandler.class)
	private String nickName;
	/**
	 * 用户英文名称
	 */
	@TableField(value = "REAL_NAME",typeHandler = MybatisJsonTypeHandler.class)
	private String realName;
	/**
	 * 登陆名称
	 */
	@TableField(value = "LOGIN_NAME",typeHandler = MybatisJsonTypeHandler.class)
	private String loginName;
	/**
	 * 登录密码
	 */
	@TableField(value = "PASSWORD",typeHandler = MybatisJsonTypeHandler.class)
	private String password;
	/**
	 * 性别
	 */
	@TableField(value = "GENDER")
	private GenderEnum gender;

}
