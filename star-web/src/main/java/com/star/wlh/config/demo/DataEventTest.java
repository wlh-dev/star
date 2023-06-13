package com.star.wlh.config.demo;

import com.google.common.collect.Maps;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ThreadFactory;

public class DataEventTest {

    public static void main(String[] args) {

        Map<@Nullable String, @Nullable String> map = Maps.newHashMap();
        ThreadFactory factory = r -> new Thread(null, r, "线程名");
        int ringBufferSize = 1024 * 1024;
        Disruptor<DataEvent> disruptor = new Disruptor<>(new DataEventFactory(), ringBufferSize, factory, ProducerType.SINGLE, new YieldingWaitStrategy());
        disruptor.handleEventsWith(new DataEventHandler());
        disruptor.start();
        RingBuffer<DataEvent> ringBuffer = disruptor.getRingBuffer();
        DataEventProducer dataEventProducer = new DataEventProducer(ringBuffer);
        ByteBuffer allocate = ByteBuffer.allocate(2);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            allocate.putChar(0, 'a');
            dataEventProducer.product(allocate);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
