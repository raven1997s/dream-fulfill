package com.raven.dreamfulfill.domain.dto.activity.stat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description:
 * date: 2023/10/12 09:57
 *
 * @author longjiaocao
 */
@Data
public class InsertActivityStatDTO {

    @ApiModelProperty(value = "节日id")
    private Long holidayId;

    @ApiModelProperty(value = "活动id")
    private Long activityId;
    
}