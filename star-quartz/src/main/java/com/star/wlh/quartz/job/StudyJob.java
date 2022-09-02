package com.star.wlh.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : wlh
 * @date Date : 2022年09月01日 15:15
 */

public class StudyJob implements Job {
	private static final Logger logger = LoggerFactory.getLogger(StudyJob.class);

	@Override public void execute(JobExecutionContext context) {
		logger.info("JobStart");
	}
}
