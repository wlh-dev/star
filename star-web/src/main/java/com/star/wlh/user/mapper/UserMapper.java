package com.star.wlh.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.star.wlh.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    public UserEntity selectByUserId(String userId);

    void save(UserEntity userEntity);
}
