package com.star.wlh.designpattern.strategy;

/**
 * 定义一个启用支付策略的接口
 */
public interface StrategyPayment {
    /**
     * 定义一种支付方式
     *
     * @return string
     */
    PayResult pay(Order order);
}
