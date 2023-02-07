package com.star.wlh.designpattern.strategy;

import org.springframework.stereotype.Service;

@Service(value = "")
public class AliPayment implements StrategyPayment {

    @Override
    public PayResult pay(Order order) {
        return new PayResult("支付宝支付成功");
    }
}
