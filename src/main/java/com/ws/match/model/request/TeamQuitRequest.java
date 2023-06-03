package com.ws.match.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 王顺
 * @Date: 2023/6/2 16:25
 * @Version 1.0
 */
@Data
public class TeamQuitRequest implements Serializable {


    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * id
     */
    private Long teamId;

}

