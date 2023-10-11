package com.raven.dreamfulfill.converter;

import com.raven.dreamfulfill.domain.entity.User;
import com.raven.dreamfulfill.domain.req.user.AddUserReq;
import com.raven.dreamfulfill.domain.req.user.UpdateUserReq;
import com.raven.dreamfulfill.domain.resp.user.UserResp;
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
public interface UserConverter {

    @Mapping(target = "createTime", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updateTime", expression = "java(java.time.LocalDateTime.now())")
    User userAddReqToUserEntity(AddUserReq req);

    @Mapping(target = "updateTime", expression = "java(java.time.LocalDateTime.now())")
    User userUpdateReqToUserEntity(UpdateUserReq req);

    UserResp userEntityToUserResp(User user);
}