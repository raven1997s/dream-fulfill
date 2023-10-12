package com.raven.dreamfulfill.domain.resp.activity;

import com.raven.dreamfulfill.common.base.AbstractBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Description:
 * date: 2023/10/10 17:21
 *
 * @author raven
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityResp extends AbstractBean {

    private Long id;

    @ApiModelProperty(value = "活动主题")
    private String theme;

    @ApiModelProperty(value = "活动描述")
    private String description;

    @ApiModelProperty(value = "活动规则")
    private String rule;

    @ApiModelProperty(value = "活动开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "活动结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "节日名称")
    private String holidayName;

    @ApiModelProperty(value = "用户名称")
    private String createUserName;

    @ApiModelProperty(value = "是否取消")
    private Integer isDelete;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改日期")
    private LocalDateTime updateTime;
}