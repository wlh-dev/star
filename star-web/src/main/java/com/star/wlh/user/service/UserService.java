package com.star.wlh.user.service;

import com.star.wlh.common.exception.InterfaceNotImplemented;
import com.star.wlh.user.dto.UserDTO;
import com.star.wlh.user.entity.UserEntity;

import java.util.List;

public interface UserService {
	UserEntity findById(String id);

    /**
     * <p>
     *      现不推荐使用该方法
     * </p>
     *      @return {@link com.star.wlh.user.entity.UserEntity}
     * <p>
     *     推荐使用方法 {@link com.star.wlh.user.service.impl.UserServiceImpl#deleteByName(String)}
     * </p>
     *
     */
	@Deprecated
	UserEntity retry();
    UserEntity insert(UserDTO userDTO);

	List<UserEntity> findAllByPage(long current, long size);

    default UserEntity deleteByName(String name){
        throw new InterfaceNotImplemented();
    }
}
