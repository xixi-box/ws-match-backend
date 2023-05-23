package com.ws.match.exception;

import com.ws.match.common.ErrorCode;

/**
 * @author 王顺
 * @version 1.0
 * @Date 2023/3/21 16:16
 */
public class BusinessException extends RuntimeException {

    private final int code;
    private final String description;

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;

    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
