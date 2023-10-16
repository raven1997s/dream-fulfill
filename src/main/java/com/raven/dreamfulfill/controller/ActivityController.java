package com.raven.dreamfulfill.controller;

import com.raven.dreamfulfill.common.base.CommonResult;
import com.raven.dreamfulfill.common.base.IdReq;
import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.domain.req.activity.PageQueryActivityListReq;
import com.raven.dreamfulfill.domain.req.activity.AddActivityReq;
import com.raven.dreamfulfill.domain.req.activity.UpdateActivityReq;
import com.raven.dreamfulfill.domain.req.drawrecord.DoLotteryReq;
import com.raven.dreamfulfill.domain.resp.activity.ActivityResp;
import com.raven.dreamfulfill.domain.resp.activity.stat.ActivityStatResp;
import com.raven.dreamfulfill.service.IActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * date: 2023/10/11 14:57
 *
 * @author longjiaocao
 */

@Api(tags = "活动管理")
@RestController
@RequestMapping(value = "/activity")
public class ActivityController {

    @Autowired
    private IActivityService activityService;


    @ApiOperation("分页查询中奖记录列表")
    @PostMapping("/pageQueryActivityList")
    public CommonResult<PageResult<ActivityResp>> pageQueryActivityList(@RequestBody @Validated PageQueryActivityListReq req) {
        return CommonResult.success(activityService.pageQueryActivityList(req));
    }

    @ApiOperation("新增活动")
    @PostMapping("/addActivity")
    public CommonResult<Void> addActivity(@RequestBody @Validated AddActivityReq req) {
        activityService.addActivity(req);
        return CommonResult.success();
    }

    @ApiOperation("修改活动")
    @PostMapping("/updateActivity")
    public CommonResult<Void> updateActivity(@RequestBody @Validated UpdateActivityReq req) {
        activityService.updateActivity(req);
        return CommonResult.success();
    }

    @ApiOperation("删除活动")
    @PostMapping("/deleteActivity")
    public CommonResult<Void> deleteActivity(@RequestBody @Validated IdReq req) {
        activityService.deleteActivity(req.getId());
        return CommonResult.success();
    }

    @ApiOperation("查询进行中的活动信息")
    @PostMapping("/selectActivityInfo")
    public CommonResult<ActivityResp> selectActivityInfo() {
        return CommonResult.success(activityService.selectActivityInfo());
    }

    @ApiOperation("执行抽奖")
    @PostMapping("/doLottery")
    public CommonResult<ActivityStatResp> doLottery(@RequestBody @Validated DoLotteryReq req) {
        return CommonResult.success(activityService.doLottery(req));
    }
}