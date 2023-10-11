package com.raven.dreamfulfill.domain.resp.drawrecord;

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
public class DrawRecordResp extends AbstractBean {


    @ApiModelProperty(value = "活动主题")
    private String activityTheme;

    @ApiModelProperty(value = "礼物名称")
    private String giftName;

    @ApiModelProperty(value = "节日名称")
    private String holidayName;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

}