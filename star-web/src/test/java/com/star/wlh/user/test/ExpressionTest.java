package com.star.wlh.user.test;

import cn.hutool.cron.timingwheel.TimingWheel;
import com.star.wlh.user.BaseTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

public class ExpressionTest extends BaseTest {
    @Test
    public void expressionTest() {
        System.out.println(Collections.emptyList());
        System.out.println(Collections.singletonList(""));
        System.out.println(Arrays.asList(""));
        Double res = 26.57 / 1.674;
        System.out.println(res);
    }
}
