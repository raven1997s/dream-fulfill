package com.raven.dreamfulfill.domain.enums;

import com.raven.dreamfulfill.common.exception.CommonException;
import lombok.Getter;

import java.util.Objects;

/**
 * Description:
 * date: 2023/10/10 16:53
 *
 * @author raven
 */
@Getter
public enum SpecialDateLevelEnum {

    NORMAL("普通", 1, 30.0, 10.0),
    IMPORTANT("重要", 2, 20.0, 20.0),
    SPECIAL("特殊", 3, 10.0, 30.0);
    private String desc;
    private Integer code;

    /**
     * 实用性占比
     */
    private double practicalityRate;
    /**
     * 价格占比
     */
    private double priceScoreRate;

    SpecialDateLevelEnum(String desc, Integer code, double practicalityRate, double priceScoreRate) {
        this.desc = desc;
        this.code = code;
        this.practicalityRate = practicalityRate;
        this.priceScoreRate = priceScoreRate;
    }


    public static SpecialDateLevelEnum getEnumByValue(Integer code) {
        SpecialDateLevelEnum[] values = SpecialDateLevelEnum.values();
        for (SpecialDateLevelEnum enums : values) {
            if (Objects.equals(enums.code, code)) {
                return enums;
            }
        }
        throw new CommonException(String.format("节日等级类型{%s}不存在", code));
    }
}