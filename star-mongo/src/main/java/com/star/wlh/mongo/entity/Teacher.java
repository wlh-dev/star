package com.star.wlh.mongo.entity;


import javax.el.MethodNotFoundException;

public interface Teacher {
    void classIsBegin();
    void teach();
    void classIsOver();

    default void fly(){
        throw new MethodNotFoundException();
    }

}
