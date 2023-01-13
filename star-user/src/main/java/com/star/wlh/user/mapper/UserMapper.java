package com.star.wlh.user.mapper;

import com.star.wlh.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : wlh
 * @date Date : 2022年08月06日 02:06
 */
@Mapper
public interface UserMapper {
	/**
	 * 查询全部数据
	 *
	 * @return
	 */
	List<UserEntity> query();

	/**
	 * 插入用户
	 *
	 * @param entity
	 */
	void insert(UserEntity entity);

	UserEntity findById(UserEntity userId);
}
