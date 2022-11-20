package com.star.wlh.mongo.entity;

public class MathTeacher implements Teacher{
    @Override
    public void classIsBegin() {
        System.out.println("上课");
    }

    @Override
    public void teach() {
        System.out.println("正在上课");
    }

    @Override
    public void classIsOver() {
        System.out.println("下课");
    }

    @Override
    public void fly() {
        System.out.println("数学老师会飞");
    }
}
