package com.raven.dreamfulfill.service;

import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.domain.req.activity.AddActivityReq;
import com.raven.dreamfulfill.domain.req.activity.PageQueryActivityListReq;
import com.raven.dreamfulfill.domain.req.activity.UpdateActivityReq;
import com.raven.dreamfulfill.domain.req.drawrecord.DoLotteryReq;
import com.raven.dreamfulfill.domain.resp.activity.ActivityResp;
import com.raven.dreamfulfill.domain.resp.activity.stat.ActivityStatResp;

/**
 * Description:
 * date: 2023/10/11 09:45
 *
 * @author raven
 */
public interface IActivityService {
    /**
     * 分页查询活动列表
     * @param req
     * @return
     */
    PageResult<ActivityResp> pageQueryActivityList(PageQueryActivityListReq req);

    /**
     * 新增活动
     * @param req
     */
    void addActivity(AddActivityReq req);

    /**
     * 修改活动
     * @param req
     */
    void updateActivity(UpdateActivityReq req);


    /**
     * 删除活动
     * @param id
     */
    void deleteActivity(Long id);

    /**
     * 查询当前时间抽奖活动信息
     * @return
     */
    ActivityResp selectActivityInfo();

    /**
     * 抽奖
     * @param req
     */
    ActivityStatResp doLottery(DoLotteryReq req);
}