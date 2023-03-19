package com.ws.usercenter.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ws.usercenter.mapper.UserMapper;
import com.ws.usercenter.model.domain.User;
import com.ws.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ws.usercenter.contant.UserConstant.USER_LOGIN_STATE;

/**
 * @author 26062
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2023-03-15 15:09:58
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {


    @Resource
    private UserMapper userMapper;
    //盐值 混淆密码
    private final String SALT = "ws";


    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return -1;
        }
        if (userAccount.length() < 4) {
            return -1;
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            return -1;
        }
        //校验账号的特殊字符

        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return -1;
        }
        if (!userPassword.equals(checkPassword)) {
            return -1;
        }
        //查询重复
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount);
        long count = this.count(userQueryWrapper);
        if (count > 0) {
            return -1;
        }

        //对密码进行加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);

        boolean save = this.save(user);
        if (!save) {
            return -1;
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        if (userAccount.length() < 4) {
            return null;
        }
        if (userPassword.length() < 8) {
            return null;
        }
        //校验账号的特殊字符

        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return null;
        }
        //对密码进行加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //查询重复
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount);
        userQueryWrapper.eq("userPassword", encryptPassword);
        User user = (User) userMapper.selectOne(userQueryWrapper);
        //用户不存在
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            return null;
        }

        //用户脱敏
        User securityUser = getSafeUser(user);
        //记录用户信息
        HttpSession session = request.getSession();
        session.setAttribute(USER_LOGIN_STATE, securityUser);
        return securityUser;
    }

    /**
     * 用户脱敏
     *
     * @param OriginalUser
     * @return
     */
    @Override
    public User getSafeUser(User OriginalUser) {
        if (OriginalUser == null) {
            return null;
        }
        User securityUser = new User();
        securityUser.setId(OriginalUser.getId());
        securityUser.setUserAccount(OriginalUser.getUserAccount());
        securityUser.setAvatarUrl(OriginalUser.getAvatarUrl());
        securityUser.setGender(OriginalUser.getGender());
        securityUser.setPhone(OriginalUser.getPhone());
        securityUser.setEmail(OriginalUser.getEmail());
        securityUser.setUserStatus(OriginalUser.getUserStatus());
        securityUser.setCreateTime(OriginalUser.getCreateTime());
        securityUser.setUpdateTime(OriginalUser.getUpdateTime());
        securityUser.setUsername(OriginalUser.getUsername());
        securityUser.setUserRole(OriginalUser.getUserRole());

        return securityUser;
    }
}




