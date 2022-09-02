package com.star.wlh.quartz.controller;

import cn.hutool.core.collection.CollUtil;
import com.star.wlh.quartz.entity.QuartzJob;
import com.star.wlh.quartz.entity.Result;
import com.star.wlh.quartz.serivce.QuartzJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wlh
 */
@Controller
@RequestMapping(path = "/quartz")
public class QuartzController {
    private static final Logger logger = LoggerFactory.getLogger(QuartzController.class);

    private final QuartzJobService quartzJobService;
    @Autowired
    public QuartzController(QuartzJobService quartzJobService) {
        this.quartzJobService = quartzJobService;
    }

    /**
     * 新增定时任务
     *
     * @param jobName 任务名称
     * @param jobGroup 任务组
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组
     * @param cron cron表达式
     * @return Result
     */
    @PostMapping(path = "/addJob")
    @ResponseBody
    public Result<String> addJob(String jobName, String jobGroup, String triggerName, String triggerGroup, String cron) {
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setJobName(jobName);
        quartzJob.setJobGroup(jobGroup);
        quartzJob.setTriggerGroup(triggerGroup);
        quartzJob.setTriggerName(triggerName);
        quartzJob.setCreateBy("admin");
        quartzJob.setCronExpression(cron);
        quartzJobService.saveAndScheduleJob(quartzJob);
        try {
            return Result.success("添加任务成功");
        } catch (Exception e) {
            logger.error("添加任务失败:",e);
            return Result.failure("添加任务失败");
        }
    }

    /**
     * 暂停任务
     *
     * @param jName 任务名称
     * @param jGroup 任务组
     * @return Result
     */
    @PostMapping(path = "/pauseJob")
    @ResponseBody
    public Result<String> pauseJob(String jName, String jGroup) {
        try {
            return Result.success("暂停任务成功");
        } catch (Exception e) {
            logger.error("任务暂停失败",e);
            return Result.failure("暂停任务失败");
        }
    }

    /**
     * 恢复任务
     *
     * @param jName 任务名称
     * @param jGroup 任务组
     * @return Result
     */
    @PostMapping(path = "/resumeJob")
    @ResponseBody
    public Result<String> resumeJob(String jName, String jGroup) {
        try {
            return Result.success("恢复任务成功");
        } catch (Exception e) {
            logger.error("恢复任务失败",e);
            return  Result.failure("恢复任务失败");
        }
    }

    /**
     * 重启任务
     *
     * @param jName 任务名称
     * @param jGroup 任务组
     * @param cron cron表达式
     * @return Result
     */
    @PostMapping(path = "/rescheduleJob")
    @ResponseBody
    public Result<String> rescheduleJob(String jName, String jGroup, String cron) {
        try {
            return Result.success("重启任务成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("重启任务失败");
        }
    }

    /**
     * 删除任务
     *
     * @param jName 任务名称
     * @param jGroup 任务组
     * @return Result
     */
    @PostMapping(path = "/deleteJob")
    @ResponseBody
    public Result<String> deleteJob(String jName, String jGroup) {
        try {
            return Result.success("删除任务成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("删除任务失败");
        }
    }

    /**
     * 查询任务
     *
     * @return Map
     */
    @RequestMapping(path = "/queryJob")
    @ResponseBody
    public Result<List<QuartzJob>> queryJob() {
        List<QuartzJob> query = quartzJobService.query();
        if (CollUtil.isEmpty(query)) {
            return Result.success("查询任务成功",query);
        }
        return Result.failure("查询任务成功失败，没有数据");
    }
}
