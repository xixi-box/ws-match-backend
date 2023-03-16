package com.ws.usercenter.model.domain.request;

import lombok.Data;

/**
 * @author 王顺
 * @version 1.0
 * @Date 2023/3/16 15:27
 */
@Data
public class UserLoginRequest {
    private static final long serialVersionUID = -8559877565455401808L;
    private String userAccount;
    private String userPassword;
}
