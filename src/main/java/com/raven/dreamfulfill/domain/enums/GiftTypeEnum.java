package com.raven.dreamfulfill.domain.enums;

import com.raven.dreamfulfill.common.exception.CommonException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * Description:
 * date: 2023/5/25 16:57
 * 礼物类型
 *
 * @author raven
 */
@Getter
public enum GiftTypeEnum {

    MAKEUP("化妆品", 1),
    TRAVEL("旅行", 2),
    CASH("现金", 3),
    GAME("游戏", 4),
    CLOTHES("衣服", 5),
    FOOD("美食", 6),
    ELECTRONICS("电子产品", 7);


    private final String desc;
    private final Integer code;

    GiftTypeEnum(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }

    public static String getDescByCode(Integer code) {
        return getEnumByValue(code).getDesc();
    }

    public static Integer getCodeByName(String name) {
        GiftTypeEnum[] values = GiftTypeEnum.values();
        for (GiftTypeEnum enums : values) {
            if (StringUtils.equals(enums.desc, name)) {
                return enums.code;
            }
        }
        return null;
    }

    public static GiftTypeEnum getEnumByValue(Integer code) {
        GiftTypeEnum[] values = GiftTypeEnum.values();
        for (GiftTypeEnum enums : values) {
            if (Objects.equals(enums.code, code)) {
                return enums;
            }
        }
        throw new CommonException(String.format("礼物类型{%s}不存在", code));
    }



}