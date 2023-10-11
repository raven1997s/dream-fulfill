package com.raven.dreamfulfill.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel
@Table(name = "t_activity")
public class Activity{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "theme")
	@ApiModelProperty(value = "活动主题")
	private String theme;

	@Column(name = "description")
	@ApiModelProperty(value = "活动描述")
	private String description;

	@Column(name = "rule")
	@ApiModelProperty(value = "活动规则")
	private String rule;

	@Column(name = "start_time")
	@ApiModelProperty(value = "活动开始时间")
	private LocalDateTime startTime;

	@Column(name = "end_time")
	@ApiModelProperty(value = "活动结束时间")
	private LocalDateTime endTime;

	@Column(name = "holiday_id")
	@ApiModelProperty(value = "节日id")
	private Long holidayId;

	@Column(name = "create_user")
	@ApiModelProperty(value = "创建人id")
	private Integer createUser;

	@Column(name = "is_delete")
	@ApiModelProperty(value = "是否取消")
	private Integer isDelete;

	@Column(name = "create_time")
	@ApiModelProperty(value = "创建日期")
	private LocalDateTime createTime;

	@Column(name = "update_time")
	@ApiModelProperty(value = "修改日期")
	private LocalDateTime updateTime;

}

