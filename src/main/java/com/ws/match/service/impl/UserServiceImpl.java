package com.ws.match.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ws.match.common.ErrorCode;
import com.ws.match.exception.BusinessException;
import com.ws.match.mapper.UserMapper;
import com.ws.match.model.domain.User;
import com.ws.match.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ws.match.contant.UserConstant.USER_LOGIN_STATE;

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
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账户过短");
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
        }
        //校验账号的特殊字符

        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "不允许存在特殊字符");
        }
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码不一致");
        }
        //查询重复
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount);
        long count = this.count(userQueryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
        }

        //对密码进行加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);

        boolean save = this.save(user);
        if (!save) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "注册失败");
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号太短");
        }
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码太短");
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

    /**
     * 用户注销
     *
     * @return
     */
    @Override
    public int userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 0;
    }
}




