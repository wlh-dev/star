package com.star.wlh.user.dto;

import com.star.wlh.user.entity.UserEntity;
import org.springframework.beans.BeanUtils;

public class UserConvertor {
    /**
     * convert userUserEntity to userDTO
     *
     * @return User传输实体对象 {@link UserDTO}
     */
    public static UserDTO userEntityConvertUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity, userDTO);
        return userDTO;
    }

    private UserConvertor() {
    }

    /**
     * convert UserDTO to UserEntity
     *
     * @param userDTO {@link UserDTO} 传输对象
     * @return UserEntity {@link UserEntity}
     */
    public static UserEntity userDTOConvertUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userEntity, userDTO);
        return userEntity;
    }

    /**
     * convert userVo to UserDTO
     * UserVO对象转换为UserDTO对象
     *
     * @param userVo {@link UserVo} user包装对象
     * @return UserDTO {@link UserDTO} User传输对象
     */
    public static UserDTO userVoConvertUserDTO(UserVo userVo) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userVo, userDTO);
        return userDTO;
    }
}
