package com.raven.dreamfulfill.domain.req.specialdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.raven.dreamfulfill.common.base.IdReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Description:
 * date: 2023/10/10 17:17
 *
 * @author raven
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateSpecialDateReq extends IdReq {

    @NotBlank
    @ApiModelProperty(value = "节日名称")
    private String holidayName;

    @NotNull
    @ApiModelProperty(value = "节日重要程度")
    private Integer level;

    @NotNull
    @ApiModelProperty(value = "节日类型，传统节日、纪念日等等")
    private Integer holidayType;

    @NotNull
    @ApiModelProperty(value = "节日所在日期")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime holidayTime;

}