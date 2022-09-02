package com.star.wlh.quartz.mapper;

import com.star.wlh.quartz.entity.QuartzJob;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : wlh
 * @date Date : 2022年09月01日 16:00
 */

@Mapper
public interface QuartzJobMapper {
	/**
	 * 查询集合
	 * @return List<QuartzJob>
	 */
	List<QuartzJob> query();

	/**
	 * 插入单个定时任务
	 * @param quartzJob 定时任务
	 */
	void insert(QuartzJob quartzJob);
}
