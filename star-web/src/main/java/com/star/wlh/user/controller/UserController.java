package com.star.wlh.user.controller;

import com.star.wlh.user.config.Result;
import com.star.wlh.user.dto.*;
import com.star.wlh.user.entity.UserEntity;
import com.star.wlh.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;
    @RequestMapping("findByUserId")
    public Result<UserEntity> findByUserId(@RequestParam String userId){
        UserEntity userEntity = userService.findById(userId);
        Result<UserEntity> ok = Result.ok(userEntity);
        logger.info("查询返回数据是:{}",ok);
        return ok;
    }
    @RequestMapping("retry")
    public Result<UserEntity> retry(){
        UserEntity retry = userService.retry();
        return Result.ok(retry);
    }

    @RequestMapping("insert")
    public Result<UserEntity> insert(@Validated({UserUpdate.class, UserUpdate.class}) @RequestBody UserVo userVo){
        UserDTO userDTO = UserConvertor.userVoConvertUserDTO(userVo);

        UserEntity retry = userService.insert(userDTO);
        return Result.ok(retry);
    }
}
