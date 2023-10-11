package com.raven.dreamfulfill.domain.resp.user;

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
public class UserResp extends AbstractBean {

    private Long id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "是否有抽奖权限")
    private Boolean hasLotteryPermission;

    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改日期")
    private LocalDateTime updateTime;

}