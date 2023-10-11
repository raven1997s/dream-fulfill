package com.raven.dreamfulfill.domain.enums;

/**
 * Description:
 * date: 2023/10/10 16:53
 *
 * @author raven
 */
public enum SpecialDateLevelEnum {

    NORMAL("普通", 1),
    IMPORTANT("重要", 2),
    SPECIAL("特殊", 3);
    private String desc;
    private Integer code;

    SpecialDateLevelEnum(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }
}