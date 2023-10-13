package com.raven.dreamfulfill.domain.enums;

import lombok.Getter;

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
     * 心动值占比
     */
    private double infatuationScoreRate;

    SpecialDateLevelEnum(String desc, Integer code, double practicalityRate, double infatuationScoreRate) {
        this.desc = desc;
        this.code = code;
        this.practicalityRate = practicalityRate;
        this.infatuationScoreRate = infatuationScoreRate;
    }
}