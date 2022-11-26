package com.star.wlh.demo;

import com.lmax.disruptor.EventHandler;

public class DataEventHandler implements EventHandler<DataEvent> {
    @Override
    public void onEvent(DataEvent dataEvent, long l, boolean b) {
        // System.out.println("消费:" + dataEvent.getValue());
    }
}
