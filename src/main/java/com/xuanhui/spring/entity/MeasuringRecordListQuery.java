package com.xuanhui.spring.entity;


import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 测量记录列表分页查询
 *
 * @author IanGuo
 * @date 2021/10/21
 */
@Data
public class MeasuringRecordListQuery extends PageQuery{

    /**
     * 面板用户id
     */
    @NotEmpty
    @Size(max = 19)
    private String panelUserId;

    /**
     * 设备id
     */
//    @Size(max = 20)
//    private String devId;

    /**
     * 查询开始时间的时间戳 "秒级"
     */
    @NotNull
    @Max(10000000000L)
    private Long startTime;
    /**
     * 查询结束时间的时间戳 "秒级"
     */
    @NotNull
    @Max(10000000000L)
    private Long endTime;

}
