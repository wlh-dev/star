package com.star.wlh.user.service.impl;

import com.star.wlh.common.exception.LicenseException;
import com.star.wlh.user.convert.EnumConvert;
import com.star.wlh.user.dto.UserDTO;
import com.star.wlh.user.entity.UserEntity;
import com.star.wlh.user.enums.GenderEnum;
import com.star.wlh.user.service.UserService;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.List;


@Service(value = "userServiceJdbcTemplate")
public class UserServiceJdbcTemplateImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceJdbcTemplateImpl.class);

    public static final EnumConvert enumConvert = new EnumConvert();
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserEntity findById(String id) {

        String sql = "select id, username, password, email, gender, birth from `user` where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new int[]{Types.VARCHAR}, (rs, rowNum) -> {
            UserEntity result = new UserEntity();
            result.setId(rs.getString("id"));
            result.setUsername(rs.getString("username"));
            result.setPassword(rs.getString("password"));
            result.setEmail(rs.getString("email"));
            String gender = rs.getNString("gender");
            GenderEnum convert = enumConvert.convert(gender == null ? "" : gender);
            result.setBirth(rs.getDate("birth"));
            result.setGender(convert);
            return result;
        });
    }

    @Override
    public UserEntity retry() {
        logger.info("retry");
        if (true) {
            throw new LicenseException();
        }
        return null;
    }

    @Override
    public UserEntity insert(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<UserEntity> findAllByPage(long current, long size) {
        return null;
    }

    @Override
    public UserEntity deleteByName(String name) {
        logger.info("接口实现了");
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        return userEntity;
    }
}
