package com.ws.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ws.usercenter.model.domain.User;
import com.ws.usercenter.model.domain.request.UserLoginRequest;
import com.ws.usercenter.model.domain.request.UserRegisterRequest;
import com.ws.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ws.usercenter.contant.UserConstant.ADMIN_ROLE;
import static com.ws.usercenter.contant.UserConstant.USER_LOGIN_STATE;

/**
 * @author 王顺
 * @version 1.0
 * @Date 2023/3/16 14:54
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return null;
        }
        String userPassword = userRegisterRequest.getUserPassword();
        String userAccount = userRegisterRequest.getUserAccount();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        return userService.userRegister(userAccount, userPassword, checkPassword);


    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }
        String userPassword = userLoginRequest.getUserPassword();
        String userAccount = userLoginRequest.getUserAccount();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        return userService.userLogin(userAccount, userPassword, request);


    }

    @GetMapping("/current")
    public User getCurrentUser(HttpServletRequest request) {

        Object attribute = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) attribute;
        if (currentUser == null) {
            return null;
        }
        Long userId = currentUser.getId();
        User byId = userService.getById(userId);
        return userService.getSafeUser(byId);
    }

    @GetMapping("/search")
    public List<User> searchUser(String username, HttpServletRequest request) {
        if (!isAdmin(request)) {
            return new ArrayList<>();
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            userQueryWrapper.like("username", username);
        }
        List<User> list = userService.list(userQueryWrapper);
        return list.stream().map(user -> {
            return userService.getSafeUser(user);
        }).collect(Collectors.toList());
    }

    @PostMapping("/delete")
    public boolean deleteById(@RequestBody Long id, HttpServletRequest request) {
        if (!isAdmin(request)) {
            return false;
        }
        isAdmin(request);
        if (id <= 0) {
            return false;
        }
        return userService.removeById(id);
    }

    public boolean isAdmin(HttpServletRequest request) {
        //仅管理员可以查询
        Object attribute = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) attribute;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }

}
