package com.raven.dreamfulfill.service;

import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.domain.req.user.AddUserReq;
import com.raven.dreamfulfill.domain.req.user.PageQueryUserListReq;
import com.raven.dreamfulfill.domain.req.user.SetLotteryPermissionReq;
import com.raven.dreamfulfill.domain.req.user.UpdateUserReq;
import com.raven.dreamfulfill.domain.resp.user.UserResp;

import java.util.List;

/**
 * Description:
 * date: 2023/10/10 16:31
 *
 * @author raven
 */
public interface IUserService {

    /**
     * 分页查询用户列表
     * @param req
     * @return
     */
    PageResult<UserResp> pageQueryUserList(PageQueryUserListReq req);

    /**
     * 新增用户
     * @param req
     */
    void addUser(AddUserReq req);

    /**
     * 修改用户
     * @param req
     */
    void updateUser(UpdateUserReq req);

    /**
     * 设置抽奖权限
     * @param req
     */
    void setLotteryPermission(SetLotteryPermissionReq req);

    /**
     * 查询所有有抽奖权限的用户
     * @param
     * @return
     */
    List<UserResp> findAllHasLotteryPermissionUser();



}