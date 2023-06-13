package com.star.wlh.config.demo;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class DataEventProducer {
    private final RingBuffer<DataEvent> ringBuffer;

    public DataEventProducer(RingBuffer<DataEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void product(ByteBuffer data) {
        long sequence = ringBuffer.next();
        try {
            DataEvent dataEvent = ringBuffer.get(sequence);
            dataEvent.setValue(data.getChar(0));
            // System.out.println("生产:" + dataEvent.getValue());
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
