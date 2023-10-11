package com.raven.dreamfulfill.domain.entity;

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
	private LocalDateTime createTime;

}

