package com.star.wlh.user.test;

import com.star.wlh.user.BaseTest;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PointCutTest extends BaseTest {
    @Test
    @DisplayName("before切入点测试")
    @Before(value = "execution(public UserEntity com.star.wlh.user.service.impl.UserServiceImpl.findById(String))")
    public void pointCutBeforeTest(){
        System.out.println("[AOP返回通知]方法成功返回了");
    }
    @Test
    @DisplayName("after切入点测试")
    @After(value = "execution(public UserEntity com.star.wlh.user.service.impl.UserServiceImpl.findById(String))")
    public void pointCutAfterTest(){
        System.out.println("[AOP返回通知]方法成功返回了");
    }
}
