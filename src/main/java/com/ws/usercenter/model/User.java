package com.ws.usercenter.model;

import lombok.Data;

/**
 * @author 王顺
 * @version 1.0
 * @Date 2023/3/14 17:32
 */
@Data
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String email;

}
