package com.star.wlh.mongo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("mongo")
@RestController
public class MongoTestController {
    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }
}
