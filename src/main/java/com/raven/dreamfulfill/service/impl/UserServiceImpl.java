package com.raven.dreamfulfill.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.raven.dreamfulfill.common.exception.CommonException;
import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.converter.UserConverter;
import com.raven.dreamfulfill.domain.entity.User;
import com.raven.dreamfulfill.domain.enums.IsYesEnum;
import com.raven.dreamfulfill.domain.req.user.AddUserReq;
import com.raven.dreamfulfill.domain.req.user.PageQueryUserListReq;
import com.raven.dreamfulfill.domain.req.user.SetLotteryPermissionReq;
import com.raven.dreamfulfill.domain.req.user.UpdateUserReq;
import com.raven.dreamfulfill.domain.resp.user.UserResp;
import com.raven.dreamfulfill.mapper.UserMapper;
import com.raven.dreamfulfill.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 * date: 2023/10/10 16:31
 *
 * @author raven
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult<UserResp> pageQueryUserList(PageQueryUserListReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());

        List<User> userList = userMapper.selectByExample(Example.builder(User.class)
                .orderByDesc("id")
                .build());
        PageInfo<User> pageInfo = new PageInfo<>(userList);

        return CollectionUtils.isEmpty(userList) ? new PageResult<>(Collections.emptyList())
                : new PageResult<>(this.convertUserListToUserRespList(userList),
                pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getPageNum());
    }

    @Override
    public void addUser(AddUserReq req) {

        int count = userMapper.selectCountByExample(Example.builder(User.class)
                .where(WeekendSqls.<User>custom()
                        .andEqualTo("name", req.getName()))
                .build());
        if (count > 0) {
            throw new CommonException("用户名已存在");
        }

        User user = userConverter.userAddReqToUserEntity(req);

        userMapper.insertSelective(user);
    }

    @Override
    public void updateUser(UpdateUserReq req) {

        int count = userMapper.selectCountByExample(Example.builder(User.class)
                .where(WeekendSqls.<User>custom()
                        .andEqualTo("name", req.getName())
                        .andNotEqualTo("id", req.getId()))
                .build());
        if (count > 0) {
            throw new CommonException("用户名已存在");
        }

        User user = userConverter.userUpdateReqToUserEntity(req);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void setLotteryPermission(SetLotteryPermissionReq req) {
        User user = new User();
        user.setId(req.getId());
        user.setHasLotteryPermission(req.getHasLotteryPermission());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<UserResp> findAllHasLotteryPermissionUser() {
        List<User> userList = userMapper.selectByExample(Example.builder(User.class)
                .where(WeekendSqls.<User>custom()
                        .andEqualTo("hasLotteryPermission", IsYesEnum.YES.getCode()))
                .build());
        if (CollectionUtils.isEmpty(userList)) {
            return Collections.emptyList();
        }

        return this.convertUserListToUserRespList(userList);
    }

    private List<UserResp> convertUserListToUserRespList(List<User> userList) {
        return userList.stream().map(user -> userConverter.userEntityToUserResp(user)).collect(Collectors.toList());
    }


}