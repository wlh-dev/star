package com.star.wlh.user.dto;

import com.star.wlh.user.entity.UserEntity;
import org.springframework.beans.BeanUtils;

public class UserConvertor {
    /**
     * convert userUserEntity to userDTO
     *
     * @return
     */
    public static UserDTO userEntityConvertUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity, userDTO);
        return userDTO;
    }

    /**
     * convert UserDTO to UserEntity
     *
     * @param userDTO
     * @return
     */
    public static UserEntity userDTOConvertUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userEntity, userDTO);
        return userEntity;
    }

    /**
     * convert userVo to UserDTO
     *
     * @param userVo
     * @return
     */
    public static UserDTO userVoConvertUserDTO(UserVo userVo) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userVo, userDTO);
        return userDTO;
    }
}
