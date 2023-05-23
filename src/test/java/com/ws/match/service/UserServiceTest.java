package com.ws.match.service;

import com.ws.match.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author 王顺
 * @version 1.0
 * @Date 2023/3/15 15:14
 */
@SpringBootTest
class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    public void testAdd() {
        User user = new User();
        user.setUserAccount("123");
        user.setAvatarUrl("https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqqezIqTPmqO9uzvOTzsjsxdBhXvPuibOhYYIdFMGVw27363WuWkBegvt8XuGFNdphgwrBkr7tTBSw/132");
        user.setGender(0);
        user.setUserPassword("123456");
        user.setPhone("111");
        user.setEmail("1111");
        user.setUsername("ws");
        boolean save = userService.save(user);
        System.out.println(save);
        Assertions.assertTrue(save);
    }

    @Test
    void userRegister() {
//        String userAccount = "yupi";
//        String userPassword = "";
//        String checkPassword = "123456";
//        String planetCode = "1";
//        long result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1, result);
//        userAccount = "yu";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1, result);
//        userAccount = "yupi";
//        userPassword = "123456";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1, result);
//        userAccount = "yu pi";
//        userPassword = "12345678";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1, result);
//        checkPassword = "123456789";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1, result);
//        userAccount = "dogYupi";
//        checkPassword = "12345678";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1, result);
//        userAccount = "yupi";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1, result);
        String userAccount = "yupi";
        String userPassword = "12345678";
        String checkPassword = "12345678";
        long l = userService.userRegister(userAccount, userPassword, checkPassword);
        System.out.println(l);

    }
}