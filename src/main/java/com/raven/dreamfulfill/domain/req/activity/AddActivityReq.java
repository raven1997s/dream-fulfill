package com.raven.dreamfulfill.domain.req.activity;

import com.raven.dreamfulfill.common.base.AbstractBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Description:
 * date: 2023/10/10 17:17
 *
 * @author raven
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class AddActivityReq extends AbstractBean {

    @NotBlank
    @ApiModelProperty(value = "活动主题")
    private String theme;

    @NotBlank
    @ApiModelProperty(value = "活动描述")
    private String description;

    @NotBlank
    @ApiModelProperty(value = "活动规则")
    private String rule;

    @NotNull
    @ApiModelProperty(value = "活动开始时间")
    private LocalDateTime startTime;

    @NotNull
    @ApiModelProperty(value = "活动结束时间")
    private LocalDateTime endTime;

    @NotNull
    @ApiModelProperty(value = "节日id")
    private Long holidayId;

    @NotNull
    @ApiModelProperty(value = "创建人id")
    private Integer createUser;

}