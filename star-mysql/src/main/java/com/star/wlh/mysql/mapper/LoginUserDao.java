package com.star.wlh.mysql.mapper;

import com.star.wlh.mysql.entity.LoginUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : wlh
 * @date Date : 2022年08月06日 02:06
 */
@Mapper
public interface LoginUserDao {
	/**
	 * 查询全部数据
	 * @return
	 */
	List<LoginUserEntity> query();

	/**
	 * 插入用户
	 * @param entity
	 */
	void insert(LoginUserEntity entity);
}
