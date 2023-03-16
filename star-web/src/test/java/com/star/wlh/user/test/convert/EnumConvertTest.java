
package com.star.wlh.user.test.convert;

import com.star.wlh.user.BaseTest;
import com.star.wlh.user.convert.EnumConvert;
import com.star.wlh.user.dto.UserDTO;
import com.star.wlh.user.enums.GenderEnum;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnumConvertTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(EnumConvertTest.class);

    @Test
    public void enumConvertTest(){
        UserDTO userDTO1 = new UserDTO(1,"jack","男");
        UserDTO userDTO2 = new UserDTO(2,"lucy","1");
        UserDTO userDTO3 = new UserDTO(3,"marry","0");
        UserDTO userDTO4 = new UserDTO(4,"zhangsan","12");
        UserDTO userDTO5 = new UserDTO();
        userDTO5.setId(5);
        userDTO5.setName("王五");
        logger.info("StringConvert:{}",new EnumConvert().convert(userDTO4.getGender()));

    }
}
