package com.ws.usercenter.model.domain.request;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 王顺
 * @version 1.0
 * @Date 2023/3/16 15:02
 */
@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = -8559877565455401808L;
    private String userAccount;
    private String userPassword;
    private String checkPassword;
}
