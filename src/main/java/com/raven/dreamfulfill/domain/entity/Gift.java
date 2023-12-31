package com.raven.dreamfulfill.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel
@Table(name = "t_gift")
public class Gift{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "gift_name")
	@ApiModelProperty(value = "礼物名称")
	private String giftName;

	@Column(name = "type")
	@ApiModelProperty(value = "礼物类型，虚拟物品、化妆品、出行等等")
	private Integer type;

	@Column(name = "price")
	@ApiModelProperty(value = "礼物价格")
	private BigDecimal price;

	@Column(name = "infatuation_score")
	@ApiModelProperty(value = "心动值")
	private BigDecimal infatuationScore;

	@Column(name = "practicality_value")
	@ApiModelProperty(value = "实用性")
	private BigDecimal practicalityValue;

	@Column(name = "description")
	@ApiModelProperty(value = "添加原因")
	private String description;

	@Column(name = "is_buy")
	@ApiModelProperty(value = "是否已购买")
	private Boolean isBuy;

	@Column(name = "is_delete")
	@ApiModelProperty(value = "是否取消")
	private Integer isDelete;

	@Column(name = "create_id")
	@ApiModelProperty(value = "创建人id")
	private Long createId;

	@Column(name = "create_time")
	@ApiModelProperty(value = "创建日期")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime createTime;

	@Column(name = "update_time")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "修改日期")
	private LocalDateTime updateTime;

}

