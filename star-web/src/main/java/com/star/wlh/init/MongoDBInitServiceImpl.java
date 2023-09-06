package com.star.wlh.init;

import com.star.wlh.common.init.InitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Classname MongDBInitServiceImpl
 * @Description 
 * @Date 2023/6/11 15:35
 * @Created by wlh
 */
@Service
public class MongoDBInitServiceImpl implements InitService {
    private static final Logger logger = LoggerFactory.getLogger(MysqlInitServiceImpl.class);

    @Override
    public void dataBaseInit() {
        logger.info("检查是否有mongo依赖");
        doInit();
    }

    @Override
    public String initName() {
        return "MongoDBInitServiceImpl";
    }

    public void doInit() {
        logger.info("进行mongo的初始化操作");
    }
}
