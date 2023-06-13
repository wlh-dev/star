package com.star.wlh.common.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Classname StarInit
 * @Description TODO
 * @Date 2023/6/11 15:30
 * @Created by wlh
 */
@Component
public class StarInit {
    private static Logger logger = LoggerFactory.getLogger(StarInit.class);

    @PostConstruct
    public void init() {
        logger.info("执行初始化任务");
        ServiceLoader<InitService> load = ServiceLoader.load(InitService.class);
        for (InitService next : load) {
            logger.info("执行初始化任务。。。：{}", next.initName());
            next.dataBaseInit();
        }
        logger.info("初始化执行完成");
    }
}
