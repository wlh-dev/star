package com.star.wlh.init;

import com.star.wlh.common.init.InitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Classname MysqlInitServiceImpl
 * @Description 
 * @Date 2023/6/11 15:34
 * @Created by wlh
 */
@Service
public class MysqlInitServiceImpl implements InitService {
    private static final Logger logger = LoggerFactory.getLogger(MysqlInitServiceImpl.class);

    @Override
    public void dataBaseInit() {
        logger.info("MySQL init");
        doInit();
    }

    @Override
    public String initName() {
        return "mysqlInitServiceImpl";
    }

    public void doInit() {
        logger.info("执行mysql的初始化操作");
    }
}
