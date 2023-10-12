package com.raven.dreamfulfill.controller;

import com.raven.dreamfulfill.common.base.CommonResult;
import com.raven.dreamfulfill.common.base.IdReq;
import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.domain.req.specialdate.AddSpecialDateReq;
import com.raven.dreamfulfill.domain.req.specialdate.PageQuerySpecialDateListReq;
import com.raven.dreamfulfill.domain.req.specialdate.UpdateSpecialDateReq;
import com.raven.dreamfulfill.domain.resp.specialdate.SpecialDateResp;
import com.raven.dreamfulfill.service.ISpecialDateService;
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

@Api(tags = "节日管理")
@RestController
@RequestMapping(value = "/holiday")
public class SpecialDateController {


    @Autowired
    private ISpecialDateService specialDateService;

    @ApiOperation("分页查询节日列表")
    @PostMapping("/pageQuerySpecialDateList")
    public CommonResult<PageResult<SpecialDateResp>> pageQuerySpecialDateList(@RequestBody @Validated PageQuerySpecialDateListReq req) {
        return CommonResult.success(specialDateService.pageQuerySpecialDateList(req));
    }

    @ApiOperation("新增节日")
    @PostMapping("/addSpecialDate")
    public CommonResult<Void> addSpecialDate(@RequestBody @Validated AddSpecialDateReq req) {
        specialDateService.addSpecialDate(req);
        return CommonResult.success();
    }

    @ApiOperation("修改节日")
    @PostMapping("/updateUser")
    public CommonResult<Void> updateSpecialDate(@RequestBody @Validated UpdateSpecialDateReq req) {
        specialDateService.updateSpecialDate(req);
        return CommonResult.success();
    }

    @ApiOperation("删除节日")
    @PostMapping("/deleteSpecialDate")
    public CommonResult<Void> deleteSpecialDate(@RequestBody @Validated IdReq req) {
        specialDateService.deleteSpecialDate(req.getId());
        return CommonResult.success();
    }
}