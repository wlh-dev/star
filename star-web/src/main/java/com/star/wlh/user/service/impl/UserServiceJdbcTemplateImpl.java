package com.star.wlh.user.service.impl;

import com.star.wlh.user.dto.UserDTO;
import com.star.wlh.user.entity.UserEntity;
import com.star.wlh.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.List;


@Service
public class UserServiceJdbcTemplateImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceJdbcTemplateImpl.class);
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserEntity findById(String id) {
        String sql = "select USER_ID,NICK_NAME from `user` where USER_ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new int[]{Types.VARCHAR}, (rs, rowNum) -> {
            UserEntity result = new UserEntity();
            result.setUserId(rs.getString("USER_ID"));
            result.setNickName(rs.getString("NICK_NAME"));
            return result;
        });
    }

    @Override
    public UserEntity retry() {
        logger.info("retry");
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
}
