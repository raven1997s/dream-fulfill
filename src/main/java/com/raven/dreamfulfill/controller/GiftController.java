package com.raven.dreamfulfill.controller;

import com.raven.dreamfulfill.common.base.CommonResult;
import com.raven.dreamfulfill.common.base.IdReq;
import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.domain.req.gift.AddGiftReq;
import com.raven.dreamfulfill.domain.req.gift.PageQueryGiftListReq;
import com.raven.dreamfulfill.domain.req.gift.UpdateGiftReq;
import com.raven.dreamfulfill.domain.resp.gift.GiftResp;
import com.raven.dreamfulfill.service.IGiftService;
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

@Api(tags = "礼物管理")
@RestController
@RequestMapping(value = "/gift")
public class GiftController {


    @Autowired
    private IGiftService giftService;

    @ApiOperation("分页查询礼物列表")
    @PostMapping("/pageQueryGiftList")
    public CommonResult<PageResult<GiftResp>> pageQuerySpecialDateList(@RequestBody @Validated PageQueryGiftListReq req) {
        return CommonResult.success(giftService.pageQueryGiftList(req));
    }

    @ApiOperation("新增礼物")
    @PostMapping("/addGift")
    public CommonResult<Void> addGift(@RequestBody @Validated AddGiftReq req) {
        giftService.addGift(req);
        return CommonResult.success();
    }

    @ApiOperation("修改礼物")
    @PostMapping("/updateGift")
    public CommonResult<Void> updateGift(@RequestBody @Validated UpdateGiftReq req) {
        giftService.updateGift(req);
        return CommonResult.success();
    }

    @ApiOperation("删除礼物")
    @PostMapping("/deleteGift")
    public CommonResult<Void> deleteGift(@RequestBody @Validated IdReq req) {
        giftService.deleteGift(req.getId());
        return CommonResult.success();
    }
}