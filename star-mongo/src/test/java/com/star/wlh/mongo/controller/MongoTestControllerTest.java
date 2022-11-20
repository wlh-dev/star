package com.star.wlh.mongo.controller;

import com.star.wlh.MongoApplicationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class MongoTestControllerTest extends MongoApplicationTest {
    @Autowired
    private MongoTestController mongoTestController;

    @Test
    public void helloTest() {
        String hello = mongoTestController.hello();
        System.out.println(hello);
        assert hello != null;
    }

}