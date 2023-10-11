package com.raven.dreamfulfill.domain.req.user;

import com.raven.dreamfulfill.common.base.AbstractBean;
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
public class AddUserReq extends AbstractBean {

    @NotBlank
    @ApiModelProperty(value = "姓名")
    private String name;

    @NotNull
    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birthday;
}