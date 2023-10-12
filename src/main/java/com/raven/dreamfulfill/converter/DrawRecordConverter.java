package com.raven.dreamfulfill.converter;

import com.raven.dreamfulfill.domain.entity.DrawRecord;
import com.raven.dreamfulfill.domain.req.drawrecord.AddDrawRecordReq;
import com.raven.dreamfulfill.domain.req.drawrecord.UpdateDrawRecordReq;
import com.raven.dreamfulfill.domain.resp.drawrecord.DrawRecordResp;
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
public interface DrawRecordConverter {

    @Mapping(target = "createTime", expression = "java(java.time.LocalDateTime.now())")
    DrawRecord drawRecordAddReqToDrawRecordEntity(AddDrawRecordReq req);

    @Mapping(target = "createTime", expression = "java(java.time.LocalDateTime.now())")
    DrawRecord drawRecordUpdateReqToDrawRecordEntity(UpdateDrawRecordReq req);

    DrawRecordResp drawRecordEntityToDrawRecordResp(DrawRecord drawRecord);
}