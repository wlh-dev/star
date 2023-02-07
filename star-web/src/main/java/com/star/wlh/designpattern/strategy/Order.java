package com.star.wlh.designpattern.strategy;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {
    private BigDecimal amount;
    private String paymentType;
}
