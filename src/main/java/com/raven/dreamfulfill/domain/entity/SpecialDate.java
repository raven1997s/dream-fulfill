package com.raven.dreamfulfill.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ApiModel
@Table(name = "t_special_date")
public class SpecialDate {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "holiday_name")
	@ApiModelProperty(value = "节日名称")
	private String holidayName;

	@Column(name = "level")
	@ApiModelProperty(value = "节日重要程度")
	private Integer level;

	@Column(name = "holiday_type")
	@ApiModelProperty(value = "节日类型，传统节日、纪念日等等")
	private Integer holidayType;

	@Column(name = "create_id")
	@ApiModelProperty(value = "节日添加人")
	private Long createId;

	@Column(name = "is_delete")
	@ApiModelProperty(value = "是否删除")
	private Integer isDelete;

	@Column(name = "holiday_time")
	@ApiModelProperty(value = "节日所在日期")
	private LocalDateTime holidayTime;

	@Column(name = "create_time")
	@ApiModelProperty(value = "创建日期")
	private LocalDateTime createTime;

	@Column(name = "update_time")
	@ApiModelProperty(value = "修改日期")
	private LocalDateTime updateTime;

}

