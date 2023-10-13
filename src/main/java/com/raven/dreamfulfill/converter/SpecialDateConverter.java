package com.raven.dreamfulfill.converter;

import com.raven.dreamfulfill.domain.dto.HolidayDTO;
import com.raven.dreamfulfill.domain.entity.SpecialDate;
import com.raven.dreamfulfill.domain.req.specialdate.AddSpecialDateReq;
import com.raven.dreamfulfill.domain.req.specialdate.UpdateSpecialDateReq;
import com.raven.dreamfulfill.domain.resp.specialdate.SpecialDateResp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Description:
 * date: 2023/10/11 10:57
 *
 * @author longjiaocao
 */
@Mapper(componentModel = "spring")
@Component
public interface SpecialDateConverter {

    @Mapping(target = "createTime", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "isDelete", expression = "java(com.raven.dreamfulfill.domain.enums.IsDelEnum.NO.getCode())")
    @Mapping(target = "updateTime", expression = "java(java.time.LocalDateTime.now())")
    SpecialDate specialDateAddReqToSpecialDateEntity(AddSpecialDateReq req);

    @Mapping(target = "updateTime", expression = "java(java.time.LocalDateTime.now())")
    SpecialDate specialDateUpdateReqToSpecialDateEntity(UpdateSpecialDateReq req);

    SpecialDateResp specialDateEntityToSpecialDateResp(SpecialDate specialDate);

    @Mapping(target = "holidayTime", source = "date", qualifiedByName = "mapStringToLocalDateTime")
    @Mapping(target = "holidayName", source = "name")
    @Mapping(target = "createTime", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updateTime", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "level", expression = "java(com.raven.dreamfulfill.domain.enums.SpecialDateLevelEnum.IMPORTANT.getCode())")
    // 默认为传统节日
    @Mapping(target = "holidayType", expression = "java(com.raven.dreamfulfill.domain.enums.SpecialDateTypeEnum.TRADITIONAL.getCode())")
    // 默认为系统
    @Mapping(target = "createId", constant = "9999L")
    @Mapping(target = "isDelete", expression = "java(com.raven.dreamfulfill.domain.enums.IsDelEnum.NO.getCode())")
    SpecialDate convertHolidayDTOToSpecialDate(HolidayDTO holidayDTO);

    @Named("mapStringToLocalDateTime")
    default LocalDateTime mapStringToLocalDateTime(String sourceHolidayTime) {
        if (sourceHolidayTime == null) {
            return null;
        }
        // 解析字符串为LocalDate
        LocalDate date = LocalDate.parse(sourceHolidayTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // 构建LocalDateTime,时间设为0
        return LocalDateTime.of(date, LocalTime.MIN);
    }
}