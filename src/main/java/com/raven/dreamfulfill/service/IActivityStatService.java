package com.raven.dreamfulfill.service;


import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.domain.dto.activity.stat.InsertActivityStatDTO;
import com.raven.dreamfulfill.domain.req.activity.stat.CurrentActivityStatReq;
import com.raven.dreamfulfill.domain.req.activity.stat.PageQueryActivityStatListReq;
import com.raven.dreamfulfill.domain.resp.activity.stat.ActivityStatResp;

import java.util.List;

/**
 * Description:
 * date: 2023/10/11 09:45
 *
 * @author raven
 */
public interface IActivityStatService {
    /**
     * 分页查询活动统计列表
     * @param req
     * @return
     */
    PageResult<ActivityStatResp> pageQueryActivityStatList(PageQueryActivityStatListReq req);

    /**
     * 查询用户当前活动的奖品信息
     * @param req
     * @return
     */
    List<ActivityStatResp> findCurrentActivityStatList(CurrentActivityStatReq req);

    /**
     * 新增活动统计记录
     * @param dto
     */
    void insertActivityStatData(InsertActivityStatDTO dto);

}