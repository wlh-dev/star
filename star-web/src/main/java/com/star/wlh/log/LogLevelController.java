package com.star.wlh.log;

import com.star.wlh.user.config.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggerConfiguration;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.Logger.ROOT_LOGGER_NAME;

@RequestMapping("log")
@RestController
public class LogLevelController {
    @Resource
    private LoggingSystem  loggingSystem ;

    private static final Logger logger = LoggerFactory.getLogger(LogLevelController.class);


    @RequestMapping(value = "logLevel/{logLevel}")
    public Result<String> changeLogLevel(@PathVariable("logLevel") String level) {
        try {
            LoggerConfiguration loggerConfiguration = loggingSystem.getLoggerConfiguration(ROOT_LOGGER_NAME);

            if (loggerConfiguration == null) {
                if (logger.isErrorEnabled()) {
                    logger.error("no loggerConfiguration with loggerName " + level);
                    return Result.fail("no loggerConfiguration with loggerName！！！");
                }
            }
            if (!supportLevels().contains(level)) {
                if (logger.isErrorEnabled()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("current Level is not support :").append(level).append(" \n support level is :").append(supportLevels());
                    logger.error(sb.toString());
                    return Result.fail(sb.toString());
                }
            }
            LogLevel effectiveLevel = loggerConfiguration.getEffectiveLevel();
            if (!effectiveLevel.equals(LogLevel.valueOf(level))) {
                if (logger.isInfoEnabled()) {
                    logger.info("setRootLoggerLevel success,old level is  '" + loggerConfiguration.getEffectiveLevel()
                            + "' , new level is '" + level + "'");
                }
                loggingSystem.setLogLevel(ROOT_LOGGER_NAME, LogLevel.valueOf(level));
            }
            StringBuilder sb = new StringBuilder();
            sb.append("change log level successful!!!  before log level is :").append(effectiveLevel.name()).append("  current log level is ").append(level);
            return Result.ok(sb.toString());
        }catch (IllegalArgumentException e){
            logger.error("IllegalArgumentException : ",e);
            return Result.fail(e.getMessage());
        }

    }

    private List<String> supportLevels() {
        return loggingSystem.getSupportedLogLevels().stream().map(Enum::name).collect(Collectors.toList());
    }

    /**
     *     TRACE,
     *     DEBUG,
     *     INFO,
     *     WARN,
     *     ERROR,
     *     FATAL,
     *     OFF;
     */
    @RequestMapping(value = "test")
    public void logLevelTest(){
        logger.warn("logger warn");
        logger.debug("logger debug");
        logger.info("logger info");
        logger.error("logger error");

    }

}
