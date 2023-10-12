package com.raven.dreamfulfill.controller;

import com.raven.dreamfulfill.common.base.CommonResult;
import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.domain.req.drawrecord.PageQueryDrawRecordListReq;
import com.raven.dreamfulfill.domain.resp.drawrecord.DrawRecordResp;
import com.raven.dreamfulfill.service.IDrawRecordService;
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

@Api(tags = "抽奖记录管理")
@RestController
@RequestMapping(value = "/drawRecord")
public class DrawRecordController {

    @Autowired
    private IDrawRecordService drawRecordService;

    @ApiOperation("分页查询中奖记录列表")
    @PostMapping("/pageQueryDrawRecordList")
    public CommonResult<PageResult<DrawRecordResp>> pageQueryDrawRecordList(@RequestBody @Validated PageQueryDrawRecordListReq req) {
        return CommonResult.success(drawRecordService.pageQueryDrawRecordList(req));
    }

}