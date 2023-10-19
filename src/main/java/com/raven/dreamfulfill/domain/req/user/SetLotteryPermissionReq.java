package com.raven.dreamfulfill.domain.req.user;

import com.raven.dreamfulfill.common.base.IdReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Description:
 * date: 2023/8/21 18:20
 *
 * @author raven
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SetLotteryPermissionReq extends IdReq {

    @ApiModelProperty(value = "是否有抽奖权限 ")
    @NotNull(message = "抽奖权限不能为空")
    private Boolean hasLotteryPermission;

}