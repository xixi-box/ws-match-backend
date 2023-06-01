package com.ws.match.ones;

import com.ws.match.mapper.UserMapper;
import com.ws.match.model.domain.User;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;

/**
 * @Author: 王顺
 * @Date: 2023/5/29 16:33
 * @Version 1.0
 */
//@Component
public class InsertUsers {
    @Resource
    private UserMapper userMapper;

    /**
     *
     */
//    @Scheduled(fixedDelay = 5000)
    public void insertUsers() {
        final int INSERT_NUM = 10000000;
        StopWatch stopWatch = new StopWatch();
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
            userMapper.insert(user);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());


    }
}
