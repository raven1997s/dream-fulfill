package com.raven.dreamfulfill.domain.req.specialdate;

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
public class PageQuerySpecialDateListReq extends PageQueryBaseReq {

    @ApiModelProperty(value = "节日名称")
    private String holidayName;

    /**
     * @see com.raven.dreamfulfill.domain.enums.SpecialDateLevelEnum
     */
    @ApiModelProperty(value = "节日重要程度")
    private Integer level;

    /**
     * @see com.raven.dreamfulfill.domain.enums.SpecialDateTypeEnum
     */
    @ApiModelProperty(value = "节日类型，传统节日、纪念日等等")
    private Integer holidayType;

}