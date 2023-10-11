package com.raven.dreamfulfill.domain.req.activity.stat;

import com.raven.dreamfulfill.common.base.AbstractBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Description:
 * date: 2023/10/11 16:58
 *
 * @author longjiaocao
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CurrentActivityStatReq extends AbstractBean {

    @NotNull
    @ApiModelProperty(value = "活动id")
    private Long activityId;

    @NotNull
    @ApiModelProperty(value = "用户id")
    private Long userId;
}