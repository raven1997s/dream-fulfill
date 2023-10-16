package com.raven.dreamfulfill.domain.resp.specialdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
public class SpecialDateResp extends AbstractBean {

    private Long id;

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

    @ApiModelProperty(value = "节日添加人")
    private String createUserName;

    @ApiModelProperty(value = "节日所在日期")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime holidayTime;

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