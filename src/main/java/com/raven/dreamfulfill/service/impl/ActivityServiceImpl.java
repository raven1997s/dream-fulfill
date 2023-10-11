package com.raven.dreamfulfill.service.impl;

import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.domain.req.activity.AddActivityReq;
import com.raven.dreamfulfill.domain.req.activity.PageQueryActivityListReq;
import com.raven.dreamfulfill.domain.req.activity.UpdateActivityReq;
import com.raven.dreamfulfill.domain.resp.activity.ActivityResp;
import com.raven.dreamfulfill.service.IActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Description:
 * date: 2023/10/11 09:45
 *
 * @author raven
 */
@Service
@Slf4j
public class ActivityServiceImpl implements IActivityService {

    @Override
    public PageResult<ActivityResp> pageQueryActivityList(PageQueryActivityListReq req) {
        return null;
    }

    @Override
    public void addActivity(AddActivityReq req) {

    }

    @Override
    public void updateActivity(UpdateActivityReq req) {

    }

    @Override
    public void deleteActivity(Long id) {

    }
}