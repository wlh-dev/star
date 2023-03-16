package com.star.wlh.user.controller;

import com.star.wlh.user.config.Result;
import com.star.wlh.user.entity.UserEntity;
import com.star.wlh.user.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("list")
    public Result<List<UserEntity>> listWithPage(@RequestParam Integer pageSize, @RequestParam Integer pageNum) {
        List<String> list = new ArrayList<>();
        list.add("001");
        List<UserEntity> userEntities = userMapper.selectBatchIds(list);
        logger.info("User entities：{}",userEntities);
        return Result.ok(userEntities);
    }
}
