package com.star.wlh.algorithm.strategy;

import org.springframework.stereotype.Service;

@Service
public class WeChatPayment implements StrategyPayment {

    @Override
    public PayResult pay(Order order) {
        return new PayResult("微信支付成功");
    }
}
