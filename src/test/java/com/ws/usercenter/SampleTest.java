package com.ws.usercenter;

import com.ws.usercenter.mapper.UserMapper;
import com.ws.usercenter.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 王顺
 * @version 1.0
 * @Date 2023/3/14 17:35
 */
@SpringBootTest
//@RunWith(SpringRunner.class)  如果无法运行的话
public class SampleTest {
    //按javabean的名称去注入
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}