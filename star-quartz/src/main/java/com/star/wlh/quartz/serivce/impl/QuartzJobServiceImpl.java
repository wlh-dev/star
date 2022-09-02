package com.star.wlh.quartz.serivce.impl;

import com.star.wlh.quartz.entity.QuartzJob;
import com.star.wlh.quartz.mapper.QuartzJobMapper;
import com.star.wlh.quartz.serivce.QuartzJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : wlh
 * @date Date : 2022年09月01日 15:57
 */
@Service public class QuartzJobServiceImpl implements QuartzJobService {
	private final QuartzJobMapper quartzJobMapper;

	/**
	 * 正常状态
	 */
	public static final Integer STATUS_NORMAL = 0;

	/**
	 * 禁用状态
	 */
	public static final Integer STATUS_DISABLE = -1;

	@Autowired public QuartzJobServiceImpl(QuartzJobMapper quartzJobMapper) {
		this.quartzJobMapper = quartzJobMapper;
	}

	@Override public List<QuartzJob> query() {
		return quartzJobMapper.query();
	}

	@Override public Boolean saveAndScheduleJob(QuartzJob quartzJob) {
		return null;
	}
}
