package com.raven.dreamfulfill.service;


import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.domain.req.drawrecord.AddDrawRecordReq;
import com.raven.dreamfulfill.domain.req.drawrecord.PageQueryDrawRecordListReq;
import com.raven.dreamfulfill.domain.resp.drawrecord.DrawRecordResp;

/**
 * Description:
 * date: 2023/10/11 09:45
 *
 * @author raven
 */
public interface IDrawRecordService {
    /**
     * 分页查询抽奖记录列表
     * @param req
     * @return
     */
    PageResult<DrawRecordResp> pageQueryDrawRecordList(PageQueryDrawRecordListReq req);

    /**
     * 新增抽奖记录
     * @param req
     */
    void addDrawRecord(AddDrawRecordReq req);

}