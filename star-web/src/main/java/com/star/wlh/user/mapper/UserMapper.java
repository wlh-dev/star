package com.star.wlh.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.star.wlh.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
