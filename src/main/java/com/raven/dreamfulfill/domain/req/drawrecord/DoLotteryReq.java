package com.raven.dreamfulfill.domain.req.drawrecord;

import com.raven.dreamfulfill.common.base.AbstractBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Description:
 * date: 2023/10/12 13:46
 *
 * @author longjiaocao
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DoLotteryReq  extends AbstractBean {

    @ApiModelProperty(value = "活动id")
    @NotNull(message = "活动id不能为空")
    private Long activityId;

    @ApiModelProperty(value = "抽奖人id")
    @NotNull(message = "抽奖人id不能为空")
    private Long userId;
}