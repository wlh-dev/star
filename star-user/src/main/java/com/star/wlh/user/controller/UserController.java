package com.star.wlh.user.controller;

import com.star.wlh.user.entity.UserEntity;
import com.star.wlh.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("list")
    public List<UserEntity> listWithPage(@RequestParam Integer pageSize, @RequestParam Integer pageNum) {

        List<UserEntity> query = userMapper.query();
        return query;
    }
}
