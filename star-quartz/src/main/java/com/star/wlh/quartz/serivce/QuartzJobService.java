package com.star.wlh.quartz.serivce;

import com.star.wlh.quartz.entity.QuartzJob;

import java.util.List;

/**
 * @author : wlh
 * @date Date : 2022年09月01日 15:53
 */

public interface QuartzJobService {
	/**
	 * 查询所有
	 * @return
	 */
	List<QuartzJob> query();

	/**
	 * 保存定时任务
	 * @param quartzJob
	 * @return
	 */
	Boolean saveAndScheduleJob(QuartzJob quartzJob);

}
