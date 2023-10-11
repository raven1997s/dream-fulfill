package com.raven.dreamfulfill.service;

import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.domain.req.gift.AddGiftReq;
import com.raven.dreamfulfill.domain.req.gift.PageQueryGiftListReq;
import com.raven.dreamfulfill.domain.req.gift.UpdateGiftReq;
import com.raven.dreamfulfill.domain.resp.gift.GiftResp;

/**
 * Description:
 * date: 2023/10/11 09:45
 *
 * @author raven
 */
public interface IGiftService {

    /**
     * 分页查询礼物列表
     *
     * @param req
     * @return
     */
    PageResult<GiftResp> pageQueryGiftList(PageQueryGiftListReq req);

    /**
     * 新增礼物
     *
     * @param req
     */
    void addGift(AddGiftReq req);

    /**
     * 修改礼物
     *
     * @param req
     */
    void updateGift(UpdateGiftReq req);


    /**
     * 删除礼物
     *
     * @param id
     */
    void deleteGift(Long id);
}