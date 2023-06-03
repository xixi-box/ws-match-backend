package com.ws.match.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ws.match.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: 王顺
 * @Date: 2023/6/2 14:58
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamQuery extends PageRequest {


    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * id 列表
     */
    private List<Long> idList;
    /**
     * 搜索关键词（同时对队伍名称和描述搜索）
     */
    private String searchText;
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
     * 用户id
     */
    private Long userId;

    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    private Integer status;


}
