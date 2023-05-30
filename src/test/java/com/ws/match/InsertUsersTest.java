package com.ws.match;

import com.ws.match.mapper.UserMapper;
import com.ws.match.model.domain.User;
import com.ws.match.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.sql.Array;
import java.util.ArrayList;

/**
 * @Author: 王顺
 * @Date: 2023/5/29 23:29
 * @Version 1.0
 */
@SpringBootTest
public class InsertUsersTest {
    @Resource
    private UserService userService;

    /**
     *
     */
    @Test
    public void insertUsers() {
        final int INSERT_NUM = 1000;
        StopWatch stopWatch = new StopWatch();
        ArrayList<User> users = new ArrayList<>();
        stopWatch.start();
        for (int i = 0; i < INSERT_NUM; i++) {
            User user = new User();
            user.setUserAccount("甲");
            user.setAvatarUrl("https://img1.doubanio.com/icon/up182099823-19.jpg");
            user.setGender(0);
            user.setUserPassword("");
            user.setPhone("");
            user.setEmail("");
            user.setTags("");
            user.setUserStatus(0);
            user.setUsername("");
            user.setUserRole(0);
            users.add(user);
        }
        userService.saveBatch(users, 100);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());


    }
    @Test
    public void doConcurrencyInsertUsers() {
        final int INSERT_NUM = 100000;
        StopWatch stopWatch = new StopWatch();
        ArrayList<User> users = new ArrayList<>();
        stopWatch.start();
        for (int i = 0; i < INSERT_NUM; i++) {
            User user = new User();
            user.setUserAccount("甲");
            user.setAvatarUrl("https://img1.doubanio.com/icon/up182099823-19.jpg");
            user.setGender(0);
            user.setUserPassword("");
            user.setPhone("");
            user.setEmail("");
            user.setTags("");
            user.setUserStatus(0);
            user.setUsername("");
            user.setUserRole(0);
            users.add(user);
        }
        userService.saveBatch(users, 100);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());


    }

}
