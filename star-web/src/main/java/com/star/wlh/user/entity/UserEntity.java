package com.star.wlh.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.star.wlh.config.MybatisJsonTypeHandler;
import com.star.wlh.user.convert.EnumConvert;
import com.star.wlh.user.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.util.StringUtils;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author : wlh
 * @date Date : 2022年08月06日 00:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(schema = "study", value = "user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -6564022808907262054L;
    /**
     * 用户id
     */
    @TableId()
    private String id;
    /**
     * 用户名称
     */
    @TableField(value = "username")
    private String username;
    /**
     * 用户英文名称
     */
    @TableField(value = "password")
    private String password;
    /**
     * 登录密码
     */
    @TableField(value = "email")
    private String email;
    /**
     * 性别
     */
    @TableField(value = "gender")
    private GenderEnum gender;

    /**
     * 生日
     */
    @TableField(value = "birth", jdbcType = JdbcType.TIMESTAMP)
    private Date birth;

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
    public void setGender(String gender) {
        if (StringUtils.hasLength(gender)){

        }else {
            this.gender = GenderEnum.UNKNOWN;
        }

    }

}
