package com.raven.dreamfulfill.domain.dto;

import lombok.Data;

/**
 * Description:
 * date: 2023/10/12 17:20
 *
 * @author longjiaocao
 */
@Data
public class HolidayDTO {
    private String date;
    private boolean isOffDay;
    private String name;
}