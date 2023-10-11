package com.raven.dreamfulfill.mapper;

import com.raven.dreamfulfill.common.mabatis.MyMapper;
import com.raven.dreamfulfill.domain.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends MyMapper<User> {
}

