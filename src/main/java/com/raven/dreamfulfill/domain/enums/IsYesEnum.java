package com.raven.dreamfulfill.domain.enums;

import lombok.Getter;

@Getter
public enum IsYesEnum {

    YES("是", 1),
    NO("否", 0);

    private String name;
    private Integer code;

    IsYesEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

}
