package com.ws.match.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import lombok.Data;

/**
 * 用户表
 *
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
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
     * 密码
     */
    private String userPassword;

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
     *
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 用户名
     */
    private String username;

    /**
     * 0是普通用户，1是管理员用户
     */
    private Integer userRole;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userAccount, user.userAccount) && Objects.equals(avatarUrl, user.avatarUrl) && Objects.equals(gender, user.gender) && Objects.equals(userPassword, user.userPassword) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email) && Objects.equals(tags, user.tags) && Objects.equals(userStatus, user.userStatus) && Objects.equals(createTime, user.createTime) && Objects.equals(updateTime, user.updateTime) && Objects.equals(isDelete, user.isDelete) && Objects.equals(username, user.username) && Objects.equals(userRole, user.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAccount, avatarUrl, gender, userPassword, phone, email, tags, userStatus, createTime, updateTime, isDelete, username, userRole);
    }
}