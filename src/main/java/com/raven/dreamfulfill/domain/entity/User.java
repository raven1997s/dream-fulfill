package com.raven.dreamfulfill.domain.entity;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel
@Table(name = "t_user")
public class User{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	@ApiModelProperty(value = "姓名")
	private String name;

	@Column(name = "has_lottery_permission")
	@ApiModelProperty(value = "是否有抽奖权限 ")
	private Boolean hasLotteryPermission;

	@Column(name = "birthday")
	@ApiModelProperty(value = "出生日期")
	private LocalDateTime birthday;

	@Column(name = "create_time")
	@ApiModelProperty(value = "创建日期")
	private LocalDateTime createTime;

	@Column(name = "update_time")
	@ApiModelProperty(value = "修改日期")
	private LocalDateTime updateTime;

}

