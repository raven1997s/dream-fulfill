package com.raven.dreamfulfill.converter;

import com.raven.dreamfulfill.domain.entity.SpecialDate;
import com.raven.dreamfulfill.domain.req.specialdate.AddSpecialDateReq;
import com.raven.dreamfulfill.domain.req.specialdate.UpdateSpecialDateReq;
import com.raven.dreamfulfill.domain.resp.specialdate.SpecialDateResp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

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
    @Mapping(target = "updateTime", expression = "java(java.time.LocalDateTime.now())")
    SpecialDate specialDateAddReqToSpecialDateEntity(AddSpecialDateReq req);

    @Mapping(target = "updateTime", expression = "java(java.time.LocalDateTime.now())")
    SpecialDate specialDateUpdateReqToSpecialDateEntity(UpdateSpecialDateReq req);

    SpecialDateResp specialDateEntityToSpecialDateResp(SpecialDate specialDate);
}