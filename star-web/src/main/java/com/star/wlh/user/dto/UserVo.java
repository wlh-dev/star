package com.star.wlh.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    @NotNull(message = "Id不能为空",groups = {UserInsert.class, UserUpdate.class})
    private String id;
    @NotBlank(message = "name不能为空",groups = {UserUpdate.class})
    private String name;

    @Valid
    private UserVo userVo;

}
