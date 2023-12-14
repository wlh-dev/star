package com.star.wlh.user.controller;

import com.star.wlh.common.response.ResponseResult;
import com.star.wlh.user.dto.*;
import com.star.wlh.user.entity.UserEntity;
import com.star.wlh.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "userServiceMyBatis")
    private UserService userService;
    @RequestMapping("findByUserId")
    public ResponseResult<UserEntity> findByUserId(@RequestParam String userId){
        UserEntity userEntity = userService.findById(userId);
        return ResponseResult.ok(userEntity);
    }
    @RequestMapping("retry")
    public ResponseResult<UserEntity> retry(){
        UserEntity retry = userService.retry();
        return ResponseResult.ok(retry);
    }

    /**
     * 插入新数据
     * @param userVo {@link UserVo}
     * @return {@link ResponseResult}
     */
    @RequestMapping("insert")
    public ResponseResult<UserEntity> insert(@Validated({UserUpdate.class, UserInsert.class}) @RequestBody UserVo userVo){
        UserDTO userDTO = UserConvertor.userVoConvertUserDTO(userVo);
        UserEntity insert = userService.insert(userDTO);
        return ResponseResult.ok(insert);
    }
    @RequestMapping("deleteByName")
    public ResponseResult<UserEntity> deleteByName(String name){
        UserEntity userEntity = userService.deleteByName(name);
        return ResponseResult.ok(userEntity);
    }

    @RequestMapping("update")
    public ResponseResult<Void> password(@Validated  @RequestBody  UserVo userVo){
        logger.info("userVo:{}",userVo);
        return ResponseResult.ok();
    }
}
