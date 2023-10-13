package com.raven.dreamfulfill.domain.enums;

import lombok.Getter;

/**
 * Description:
 * date: 2023/10/10 16:53
 *
 * @author raven
 */
@Getter
public enum SpecialDateTypeEnum {

    TRADITIONAL("传统节日",1),
    MEMORIAL("纪念日",2),
    FOREIGN("外国节日",3),
    WEEKEND("周末",4),
    DAILY("日常",5);


    private String desc;
    private Integer code;

    SpecialDateTypeEnum(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }
}