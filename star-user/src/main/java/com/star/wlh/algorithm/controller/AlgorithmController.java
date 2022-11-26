package com.star.wlh.algorithm.controller;

import com.star.wlh.algorithm.strategy.Order;
import com.star.wlh.algorithm.strategy.PayResult;
import com.star.wlh.algorithm.strategy.StrategyPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("algorithm")
public class AlgorithmController {
    @Autowired
    private Map<String, StrategyPayment> strategyMap;

    @RequestMapping("strategy")
    public void strategy() {
        Order order = new Order();
        order.setPaymentType("aliPayment");
        StrategyPayment strategyPayment = strategyMap.get(order.getPaymentType());
        PayResult pay = strategyPayment.pay(order);
        String result = pay.getResult();
        System.out.println(result);
    }
}
