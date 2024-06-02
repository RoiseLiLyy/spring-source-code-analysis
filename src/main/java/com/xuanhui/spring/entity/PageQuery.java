package com.xuanhui.spring.entity;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * page 查询
 *
 * @author IanGuo
 * @date 2021/10/22
 */
@Data
public class PageQuery {

    /**
     * pageNum
     */
    @NotNull
    @Max(1000)
    @Min(1)
    private Integer pageNum;

    /**
     * pageSize
     */
    @NotNull
    @Max(500)
    @Min(1)
    private Integer pageSize;
}
