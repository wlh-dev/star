package com.star.wlh.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class UserVo {
    @NotNull(message = "更新数据Id不能为空",groups = {UserUpdate.class})
    private String id;
    @NotBlank(message = "name不能为空",groups = {UserUpdate.class})
    private String name;
    @Length(min = 6,max = 32,message = "密码长度必须在6-32位")
    @NotBlank(message = "密码不能为空")
    private String password;
    @Valid
    private UserVo userVo;

}
