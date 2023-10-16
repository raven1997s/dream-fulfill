package com.raven.dreamfulfill.domain.resp.gift;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.raven.dreamfulfill.common.base.AbstractBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Description:
 * date: 2023/10/10 17:21
 *
 * @author raven
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GiftResp extends AbstractBean {
    private Long id;

    @ApiModelProperty(value = "礼物名称")
    private String giftName;

    @ApiModelProperty(value = "礼物类型，虚拟物品、化妆品、出行等等")
    private Integer type;

    @ApiModelProperty(value = "礼物价格")
    private BigDecimal price;

    @ApiModelProperty(value = "心动值")
    private BigDecimal infatuationScore;

    @ApiModelProperty(value = "实用性")
    private BigDecimal practicalityValue;

    @ApiModelProperty(value = "添加原因")
    private String description;

    @ApiModelProperty(value = "是否已购买")
    private Boolean isBuy;

    @ApiModelProperty(value = "创建人名称")
    private String createUserName;

    @ApiModelProperty(value = "创建日期")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改日期")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

}