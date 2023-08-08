package com.ws.match.model.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 王顺
 * @Date: 2023/6/2 16:22
 * @Version 1.0
 * @Description 队伍和用户信息
 */
@Data
public class TeamUserVO implements Serializable {

    private static final long serialVersionUID = -3334804139950519976L;
    /**
     * id
     */
    private Long id;

    /**
     * 队伍名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 最大人数
     */
    private Integer maxNum;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    private Integer status;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建人信息
     */
    private UserVO createUser;
    /**
     * 加入的用户数
     */
    private Integer hasJoinNum;

    /**
     * 是否加入队伍
     */

    private boolean hasJoin = false;

}
