package com.raven.dreamfulfill.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.converter.ActivityStatConverter;
import com.raven.dreamfulfill.domain.dto.activity.stat.InsertActivityStatDTO;
import com.raven.dreamfulfill.domain.entity.*;
import com.raven.dreamfulfill.domain.enums.IsDelEnum;
import com.raven.dreamfulfill.domain.req.activity.stat.CurrentActivityStatReq;
import com.raven.dreamfulfill.domain.req.activity.stat.PageQueryActivityStatListReq;
import com.raven.dreamfulfill.domain.resp.activity.stat.ActivityStatResp;
import com.raven.dreamfulfill.mapper.*;
import com.raven.dreamfulfill.service.IActivityStatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description:
 * date: 2023/10/11 09:45
 *
 * @author raven
 */
@Service
@Slf4j
public class ActivityStatServiceImpl implements IActivityStatService {

    @Autowired
    private ActivityStatMapper activityStatMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GiftMapper giftMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private SpecialDateMapper specialDateMapper;
    @Autowired
    private ActivityStatConverter activityStatConverter;

    @Override
    public PageResult<ActivityStatResp> pageQueryActivityStatList(PageQueryActivityStatListReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());

        List<ActivityStat> specialDateList = activityStatMapper.selectByExample(Example.builder(ActivityStat.class)
                .orderByDesc("id")
                .build());
        PageInfo<ActivityStat> pageInfo = new PageInfo<>(specialDateList);

        return CollectionUtils.isEmpty(specialDateList) ? new PageResult<>(Collections.emptyList())
                : new PageResult<>(this.convertActivityStatListToActivityStatRespList(specialDateList),
                pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getPageNum());
    }

    @Override
    public List<ActivityStatResp> findCurrentActivityStatList(CurrentActivityStatReq req) {

        List<ActivityStat> activityStats = activityStatMapper.selectByExample(Example.builder(SpecialDate.class)
                .where(WeekendSqls.<ActivityStat>custom()
                        .andEqualTo(ActivityStat::getUserId, req.getUserId())
                        .andEqualTo(ActivityStat::getActivityId, req.getActivityId()))
                .build());
        if (CollectionUtils.isEmpty(activityStats)) {
            return Collections.emptyList();
        }

        return this.convertActivityStatListToActivityStatRespList(activityStats);
    }

    @Override
    public void insertActivityStatData(InsertActivityStatDTO dto) {
        // 获取每个用户礼物 礼物按照心动值高低排序 筛选出前5个，如果不够5个，剩余的用下次再买填充

        // 计算每个用户礼物的中奖概率

        // 生成本次活动的礼物数据

    }




    private List<ActivityStatResp> convertActivityStatListToActivityStatRespList(List<ActivityStat> activityStatList) {
        List<User> userList = userMapper.selectAll();
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, user -> user));

        List<Activity> activities = activityMapper.selectByExample(Example.builder(Activity.class)
                .where(WeekendSqls.<Activity>custom()
                        .andNotEqualTo(Activity::getIsDelete, IsDelEnum.NO.getVal()))
                .build());
        Map<Long, Activity> activityMap = activities.stream().collect(Collectors.toMap(Activity::getId, activity -> activity));

        List<Gift> giftList = giftMapper.selectByExample(Example.builder(Gift.class)
                .where(WeekendSqls.<Gift>custom()
                        .andNotEqualTo(Gift::getIsDelete, IsDelEnum.NO.getVal()))
                .build());
        Map<Long, Gift> giftMap = giftList.stream().collect(Collectors.toMap(Gift::getId, gift -> gift));

        List<SpecialDate> specialDateList = specialDateMapper.selectByExample(Example.builder(SpecialDate.class)
                .where(WeekendSqls.<SpecialDate>custom()
                        .andNotEqualTo(SpecialDate::getIsDelete, IsDelEnum.NO.getVal()))
                .build());
        Map<Long, SpecialDate> specialDateMap = specialDateList.stream().collect(Collectors.toMap(SpecialDate::getId, specialDate -> specialDate));

        return activityStatList.stream().map(specialDate -> {
            ActivityStatResp activityStatResp = activityStatConverter.activityStatEntityToActivityStatResp(specialDate);
            activityStatResp.setUserName(userMap.get(specialDate.getUserId()).getName());
            activityStatResp.setActivityTheme(activityMap.get(specialDate.getActivityId()).getTheme());
            activityStatResp.setGiftName(giftMap.get(specialDate.getGiftId()).getGiftName());
            activityStatResp.setHolidayName(specialDateMap.get(specialDate.getHolidayId()).getHolidayName());
            return activityStatResp;
        }).collect(Collectors.toList());
    }
}