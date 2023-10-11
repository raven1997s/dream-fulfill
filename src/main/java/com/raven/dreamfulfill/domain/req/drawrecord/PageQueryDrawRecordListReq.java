package com.raven.dreamfulfill.domain.req.drawrecord;

import com.raven.dreamfulfill.common.base.PageQueryBaseReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description:
 * date: 2023/10/10 17:21
 *
 * @author raven
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageQueryDrawRecordListReq extends PageQueryBaseReq {
    @ApiModelProperty(value = "中奖人id")
    private Long userId;
}