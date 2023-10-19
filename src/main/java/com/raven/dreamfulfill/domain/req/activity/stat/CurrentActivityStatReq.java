package com.raven.dreamfulfill.domain.req.activity.stat;

import com.raven.dreamfulfill.common.base.AbstractBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Description:
 * date: 2023/10/11 16:58
 *
 * @author longjiaocao
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrentActivityStatReq extends AbstractBean {

    @NotNull(message = "活动id不能为空")
    @ApiModelProperty(value = "活动id")
    private Long activityId;

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id")
    private Long userId;
}