package com.star.wlh.algorithm.strategy;

public enum PayType {
    ALI("ali"),
    WE_CHAT("wechat");
    private final String type;

    PayType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
