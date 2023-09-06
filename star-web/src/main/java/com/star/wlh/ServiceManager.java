package com.star.wlh;

/**
 * @Classname ServiceManager
 * @Description 描述
 * @Date 2023/6/13 19:05
 * @Created by wlh
 */
public class ServiceManager {
    private static final ServiceManager instance = new ServiceManager();

    private ServiceManager() {
    }

    public static ServiceManager getInstance() {
        return instance;
    }

}
