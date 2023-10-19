package com.raven.dreamfulfill.domain.req.gift;

import com.raven.dreamfulfill.common.base.IdReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Description:
 * date: 2023/10/10 17:17
 *
 * @author raven
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateGiftReq extends IdReq {

    @ApiModelProperty(value = "礼物名称")
    @NotBlank
    private String giftName;

    /**
     * @see com.raven.dreamfulfill.domain.enums.GiftTypeEnum
     */
    @ApiModelProperty(value = "礼物类型，虚拟物品、化妆品、出行等等 ")
    @NotNull(message = "礼物类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "礼物价格")
    @DecimalMin("0")
    @NotNull(message = "礼物价格不能为空")
    private BigDecimal price;

    @ApiModelProperty(value = "心动值")
    @DecimalMin("60")
    @DecimalMax("100")
    @NotNull(message = "心动值不能为空")
    private BigDecimal infatuationScore;

    @ApiModelProperty(value = "实用性")
    @DecimalMin("0")
    @DecimalMax("100")
    @NotNull(message = "实用性不能为空")
    private BigDecimal practicalityValue;

    @ApiModelProperty(value = "添加原因")
    @NotBlank(message = "添加原因不能为空")
    private String description;

}