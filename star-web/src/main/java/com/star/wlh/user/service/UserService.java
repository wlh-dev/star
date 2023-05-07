package com.star.wlh.user.service;

import com.star.wlh.user.entity.UserEntity;

public interface UserService {
	UserEntity findById(String id);

	@Deprecated
	UserEntity retry();
}
