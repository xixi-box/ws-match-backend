package com.ws.match.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 王顺
 * @Date: 2023/6/2 15:16
 * @Version 1.0
 */
@Data
public class PageRequest implements Serializable {


    private static final long serialVersionUID = 3819027633415416968L;
    /**
     * @Description 第几页
     */
    private int pageSize=10;
    /**
     * @Description 页面大小
     */
    private int pageNum=1;

}
