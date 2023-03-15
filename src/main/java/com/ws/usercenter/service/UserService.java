package com.ws.usercenter.service;

import com.ws.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 26062
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2023-03-15 15:09:58
 */
public interface UserService extends IService<User> {
    /**
     * @param userAccount   用户账户
     * @param userPassword  密码
     * @param checkPassword 检查
     * @return 注册账户
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);
}
