package com.raven.dreamfulfill.controller;

import com.raven.dreamfulfill.common.base.CommonResult;
import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.domain.req.activity.stat.CurrentActivityStatReq;
import com.raven.dreamfulfill.domain.req.activity.stat.PageQueryActivityStatListReq;
import com.raven.dreamfulfill.domain.resp.activity.stat.ActivityStatResp;
import com.raven.dreamfulfill.service.IActivityStatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 * date: 2023/10/11 14:57
 *
 * @author longjiaocao
 */

@Api(tags = "活动统计管理")
@RestController
@RequestMapping(value = "/activity/stat")
public class ActivityStatController {

    @Autowired
    private IActivityStatService activityStatService;

    @ApiOperation("分页查询活动统计列表")
    @PostMapping("/pageQueryActivityStatList")
    public CommonResult<PageResult<ActivityStatResp>> pageQueryActivityStatList(@RequestBody @Validated PageQueryActivityStatListReq req) {
        return CommonResult.success(activityStatService.pageQueryActivityStatList(req));
    }

    @ApiOperation("查询用户当前活动的奖品信息")
    @PostMapping("/findCurrentActivityStatList")
    public CommonResult<List<ActivityStatResp>> findCurrentActivityStatList(@RequestBody @Validated CurrentActivityStatReq req) {
        return CommonResult.success(activityStatService.findCurrentActivityStatList(req));
    }
}