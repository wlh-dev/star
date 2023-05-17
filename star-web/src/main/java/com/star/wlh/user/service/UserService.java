package com.star.wlh.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.wlh.user.entity.UserEntity;

import java.util.List;

public interface UserService {
	UserEntity findById(String id);

	@Deprecated
	UserEntity retry();

	List<UserEntity> findAllByPage(long current, long size);
}
