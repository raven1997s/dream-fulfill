package com.raven.dreamfulfill.converter;

import com.raven.dreamfulfill.domain.entity.Gift;
import com.raven.dreamfulfill.domain.req.gift.AddGiftReq;
import com.raven.dreamfulfill.domain.req.gift.AddGiftReq;
import com.raven.dreamfulfill.domain.req.gift.UpdateGiftReq;
import com.raven.dreamfulfill.domain.resp.gift.GiftResp;
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
public interface GiftConverter {

    @Mapping(target = "createTime", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updateTime", expression = "java(java.time.LocalDateTime.now())")
    Gift giftAddReqToGiftEntity(AddGiftReq req);

    @Mapping(target = "updateTime", expression = "java(java.time.LocalDateTime.now())")
    Gift giftUpdateReqToGiftEntity(UpdateGiftReq req);

    GiftResp giftEntityToGiftResp(Gift gift);
}