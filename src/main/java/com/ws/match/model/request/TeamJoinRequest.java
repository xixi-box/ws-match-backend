package com.ws.match.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 王顺
 * @Date: 2023/6/2 16:26
 * @Version 1.0
 */
@Data
public class TeamJoinRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * id
     */
    private Long teamId;

    /**
     * 密码
     */
    private String password;
}
