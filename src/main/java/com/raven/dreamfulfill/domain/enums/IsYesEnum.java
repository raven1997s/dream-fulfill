package com.raven.dreamfulfill.domain.enums;

import lombok.Getter;

/**
 */
@Getter
public enum IsYesEnum {

    YES("是", 1),
    NO("否", 0);

    private String name;
    private Integer val;

    IsYesEnum(String name, Integer val) {
        this.name = name;
        this.val = val;
    }

}
