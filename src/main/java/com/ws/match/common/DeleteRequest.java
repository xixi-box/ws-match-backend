package com.ws.match.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 王顺
 * @Date: 2023/6/14 15:04
 * @Version 1.0
 */
@Data
public class DeleteRequest implements Serializable {

    private static final long serialVersionUID = -5860707094194210842L;

    private long id;
}
