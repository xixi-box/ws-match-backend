package com.ws.match.model.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 王顺
 * @Date: 2023/6/2 16:25
 * @Version 1.0
 */
@Data
public class TeamUpdateRequest implements Serializable {

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
     * 过期时间
     */
    private Date expireTime;


    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    private Integer status;

    /**
     * 密码
     */
    private String password;

    private static final long serialVersionUID = 4594082349833287590L;
}
