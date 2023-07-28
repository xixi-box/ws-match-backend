package com.ws.match.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 王顺
 * @Date: 2023/6/2 16:22
 * @Version 1.0
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 5420391113841414977L;
    /**
     * 用户ID
     */
    private long id;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private Integer gender;


    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 标签列表 json
     */
    private String tags;


    /**
     * 状态
     */
    private Integer userStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 用户名
     */
    private String username;

    /**
     * 0是普通用户，1是管理员用户
     */
    private Integer userRole;

}
