package com.raven.dreamfulfill.converter;

import com.raven.dreamfulfill.domain.entity.Activity;
import com.raven.dreamfulfill.domain.req.activity.AddActivityReq;
import com.raven.dreamfulfill.domain.req.activity.UpdateActivityReq;
import com.raven.dreamfulfill.domain.resp.activity.ActivityResp;
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
public interface ActivityConverter {

    @Mapping(target = "createTime", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updateTime", expression = "java(java.time.LocalDateTime.now())")
    Activity activityAddReqToActivityEntity(AddActivityReq req);

    @Mapping(target = "createTime", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updateTime", expression = "java(java.time.LocalDateTime.now())")
    Activity activityUpdateReqToActivityEntity(UpdateActivityReq req);

    ActivityResp activityEntityToActivityResp(Activity activity);
}