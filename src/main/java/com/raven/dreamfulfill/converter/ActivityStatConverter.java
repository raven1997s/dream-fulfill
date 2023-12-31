package com.raven.dreamfulfill.converter;

import com.raven.dreamfulfill.domain.dto.activity.stat.InsertActivityStatDTO;
import com.raven.dreamfulfill.domain.entity.ActivityStat;
import com.raven.dreamfulfill.domain.entity.Gift;
import com.raven.dreamfulfill.domain.req.activity.stat.AddActivityStatReq;
import com.raven.dreamfulfill.domain.req.activity.stat.UpdateActivityStatReq;
import com.raven.dreamfulfill.domain.resp.activity.stat.ActivityStatResp;
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
public interface ActivityStatConverter {

    @Mapping(target = "createTime", expression = "java(java.time.LocalDateTime.now())")
    ActivityStat activityStatAddReqToActivityStatEntity(AddActivityStatReq req);

    @Mapping(target = "createTime", expression = "java(java.time.LocalDateTime.now())")
    ActivityStat activityStatUpdateReqToActivityStatEntity(UpdateActivityStatReq req);

    ActivityStatResp activityStatEntityToActivityStatResp(ActivityStat activityStat);

    @Mapping(target = "createTime", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "giftId", expression = "java(gift.getId())")
    @Mapping(target = "id", ignore = true)
    ActivityStat giftToActivityStat(InsertActivityStatDTO dto, Long userId, Gift gift, Double winRate);
}