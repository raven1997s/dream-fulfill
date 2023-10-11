package com.raven.dreamfulfill.controller;

import com.raven.dreamfulfill.common.base.CommonResult;
import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.domain.req.user.AddUserReq;
import com.raven.dreamfulfill.domain.req.user.PageQueryUserListReq;
import com.raven.dreamfulfill.domain.req.user.SetLotteryPermissionReq;
import com.raven.dreamfulfill.domain.req.user.UpdateUserReq;
import com.raven.dreamfulfill.domain.resp.user.UserResp;
import com.raven.dreamfulfill.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 * date: 2023/10/11 14:57
 *
 * @author longjiaocao
 */

@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/user")
public class UserController {


    @Autowired
    private IUserService userService;

    @ApiOperation("分页查询用户列表")
    @PostMapping("/pageQueryUserList")
    public CommonResult<PageResult<UserResp>> pageQueryUserList(@RequestBody @Validated PageQueryUserListReq req) {
        return CommonResult.success(userService.pageQueryUserList(req));
    }

    @ApiOperation("新增用户")
    @PostMapping("/addUser")
    public CommonResult<Void> addUser(@RequestBody @Validated AddUserReq req) {
        userService.addUser(req);
        return CommonResult.success();
    }

    @ApiOperation("修改用户")
    @PostMapping("/updateUser")
    public CommonResult<Void> updateUser(@RequestBody @Validated UpdateUserReq req) {
        userService.updateUser(req);
        return CommonResult.success();
    }

    @ApiOperation("设置用户抽奖权限")
    @PostMapping("/setLotteryPermission")
    public CommonResult<Void> setLotteryPermission(@RequestBody @Validated SetLotteryPermissionReq req) {
        userService.setLotteryPermission(req);
        return CommonResult.success();
    }

    @ApiOperation("查询所有有抽奖权限的用户")
    @PostMapping("/findAllHasLotteryPermissionUser")
    public CommonResult<List<UserResp>> findAllHasLotteryPermissionUser() {
        return CommonResult.success(userService.findAllHasLotteryPermissionUser());
    }
}