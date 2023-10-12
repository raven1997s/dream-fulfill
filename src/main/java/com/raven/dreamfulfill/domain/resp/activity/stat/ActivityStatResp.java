package com.raven.dreamfulfill.domain.resp.activity.stat;

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
public class ActivityStatResp extends AbstractBean {

    private Long id;

    @ApiModelProperty(value = "活动主题")
    private String activityTheme;

    @ApiModelProperty(value = "礼物名称")
    private String giftName;

    @ApiModelProperty(value = "节日名称")
    private String holidayName;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "活动id")
    private Long activityId;

    @ApiModelProperty(value = "礼物id")
    private Long giftId;

    @ApiModelProperty(value = "节日id")
    private Long holidayId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "本期中奖概率")
    private Integer winRate;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;
}