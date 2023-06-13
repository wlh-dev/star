package com.star.wlh.common.init;

/**
 * @Classname InitService
 * @Description TODO
 * @Date 2023/6/11 15:33
 * @Created by wlh
 */
public interface InitService {
    /**
     * 数据库初始化执行
     */
    public void dataBaseInit();

    public String initName();

}
