package com.ws.match.model.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 王顺
 * @Date: 2023/6/2 16:46
 * @Version 1.0
 */
@Data
public class TeamAddRequest implements Serializable {


    private static final long serialVersionUID = 5728460049474860235L;
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
     * 密码
     */
    private String password;
}
