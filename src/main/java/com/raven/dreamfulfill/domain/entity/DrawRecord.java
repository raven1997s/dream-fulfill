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
import java.time.LocalDateTime;

@Data
@ApiModel
@Table(name = "t_draw_record")
public class DrawRecord{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	@ApiModelProperty(value = "中奖人id")
	private Long userId;

	@Column(name = "holiday_id")
	@ApiModelProperty(value = "节日id")
	private Long holidayId;

	@Column(name = "activity_id")
	@ApiModelProperty(value = "活动id")
	private Long activityId;

	@Column(name = "gift_id")
	@ApiModelProperty(value = "礼物id")
	private Long giftId;

	@Column(name = "create_time")
	@ApiModelProperty(value = "创建日期")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime createTime;

}

