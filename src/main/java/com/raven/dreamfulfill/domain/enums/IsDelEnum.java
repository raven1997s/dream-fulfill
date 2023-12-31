package com.raven.dreamfulfill.domain.enums;

import lombok.Getter;

/**
 * 逻辑删除标记枚举
 */
@Getter
public enum IsDelEnum {

    YES("已删除", 1),
    NO("未删除", 0);

    private String name;
    private Integer code;

    IsDelEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

}
