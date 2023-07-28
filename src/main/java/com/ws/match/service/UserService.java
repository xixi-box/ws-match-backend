package com.ws.match.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.match.model.domain.User;
import com.ws.match.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * @param userAccount  账户
     * @param userPassword
     * @return 返回登录的用户的信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    User getSafeUser(User OriginalUser);

    /**
     * 用户注销功能
     *
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);

    /**
     * 根据标签搜索用户
     *
     * @param tagNameList
     * @return
     */
    List<User> searchUsersByTags(List<String> tagNameList);

    /**
     * @param user
     * @return
     */
    int updateUser(User user, User loginUser);

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    boolean isAdmin(User loginUser);

    List<User> matchUsers(long num, User loginUser);
}
