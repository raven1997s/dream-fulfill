package com.raven.dreamfulfill.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.common.exception.CommonException;
import com.raven.dreamfulfill.common.utils.LotteryDraw;
import com.raven.dreamfulfill.converter.ActivityConverter;
import com.raven.dreamfulfill.domain.entity.*;
import com.raven.dreamfulfill.domain.enums.IsDelEnum;
import com.raven.dreamfulfill.domain.enums.IsYesEnum;
import com.raven.dreamfulfill.domain.req.activity.AddActivityReq;
import com.raven.dreamfulfill.domain.req.activity.PageQueryActivityListReq;
import com.raven.dreamfulfill.domain.req.activity.UpdateActivityReq;
import com.raven.dreamfulfill.domain.req.activity.stat.CurrentActivityStatReq;
import com.raven.dreamfulfill.domain.req.drawrecord.DoLotteryReq;
import com.raven.dreamfulfill.domain.resp.activity.ActivityResp;
import com.raven.dreamfulfill.domain.resp.activity.stat.ActivityStatResp;
import com.raven.dreamfulfill.mapper.*;
import com.raven.dreamfulfill.service.IActivityService;
import com.raven.dreamfulfill.service.IActivityStatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.time.LocalDateTime;
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
public class ActivityServiceImpl implements IActivityService {

    @Autowired
    private ActivityConverter activityConverter;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private IActivityStatService activityStatService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SpecialDateMapper specialDateMapper;
    @Autowired
    private DrawRecordMapper drawRecordMapper;
    @Autowired
    private GiftMapper giftMapper;

    @Override
    public PageResult<ActivityResp> pageQueryActivityList(PageQueryActivityListReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());

        List<Activity> activityList = activityMapper.selectByExample(Example.builder(Activity.class)
                .where(WeekendSqls.<Activity>custom()
                        .andEqualTo(Activity::getIsDelete, IsYesEnum.NO.getCode()))
                .orderByDesc("id")
                .build());
        PageInfo<Activity> pageInfo = new PageInfo<>(activityList);

        return CollectionUtils.isEmpty(activityList) ? new PageResult<>(Collections.emptyList())
                : new PageResult<>(this.convertActivityListToActivityRespList(activityList),
                pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getPageNum());
    }

    @Override
    @Transactional
    public void addActivity(AddActivityReq req) {
        int themeExist = activityMapper.selectCountByExample(Example.builder(Activity.class)
                .where(WeekendSqls.<Activity>custom()
                        .andEqualTo(Activity::getIsDelete, IsYesEnum.NO.getCode())
                        .andEqualTo(Activity::getTheme, req.getTheme()))
                .build());
        if (themeExist > 0) {
            throw new CommonException("活动主题已存在~");
        }
        int timeClashCount = activityMapper.selectCountByExample(Example.builder(Activity.class)
                .where(WeekendSqls.<Activity>custom()
                        .andEqualTo(Activity::getIsDelete, IsYesEnum.NO.getCode())
                        .andLessThanOrEqualTo(Activity::getStartTime, req.getEndTime())
                        .andGreaterThanOrEqualTo(Activity::getEndTime, req.getStartTime()))
                .build());
        if (timeClashCount > 0) {
            throw new CommonException("该活动时间范围内已存在其他活动~");
        }

        // 生成活动 活动统计记录
        Activity activity = activityConverter.activityAddReqToActivityEntity(req);
        activityMapper.insertSelective(activity);
        activityStatService.insertActivityStatData(activityConverter.activityEntityToActivityStatDTO(activity));
    }

    @Override
    public void updateActivity(UpdateActivityReq req) {

        int themeExist = activityMapper.selectCountByExample(Example.builder(Activity.class)
                .where(WeekendSqls.<Activity>custom()
                        .andEqualTo(Activity::getIsDelete, IsYesEnum.NO.getCode())
                        .andEqualTo(Activity::getTheme, req.getTheme())
                        .andNotEqualTo(Activity::getId, req.getId()))
                .build());
        if (themeExist > 0) {
            throw new CommonException("活动主题已存在~");
        }
        int timeClashCount = activityMapper.selectCountByExample(Example.builder(Activity.class)
                .where(WeekendSqls.<Activity>custom()
                        .andEqualTo(Activity::getIsDelete, IsYesEnum.NO.getCode())
                        .andNotEqualTo(Activity::getId, req.getId())
                        .andLessThanOrEqualTo(Activity::getStartTime, req.getEndTime())
                        .andGreaterThanOrEqualTo(Activity::getEndTime, req.getStartTime()))
                .build());
        if (timeClashCount > 0) {
            throw new CommonException("该活动时间范围内已存在其他活动~");
        }

        Activity activity = activityConverter.activityUpdateReqToActivityEntity(req);
        activityMapper.updateByPrimaryKeySelective(activity);
    }

    @Override
    public void deleteActivity(Long id) {

        Activity activity = activityMapper.selectByPrimaryKey(id);
        if (activity == null) {
            throw new CommonException("活动已删除~");
        }

        activity.setIsDelete(IsYesEnum.YES.getCode());
        activityMapper.updateByPrimaryKeySelective(activity);
    }

    @Override
    public ActivityResp selectActivityInfo() {
        Activity activity = activityMapper.selectOneByExample(Example.builder(Activity.class)
                .where(WeekendSqls.<Activity>custom()
                        .andEqualTo(Activity::getIsDelete, IsDelEnum.NO.getCode())
                        .andLessThanOrEqualTo(Activity::getStartTime, LocalDateTime.now())
                        .andGreaterThanOrEqualTo(Activity::getEndTime, LocalDateTime.now()))
                .build());

        if (activity == null) {
            throw new CommonException("现在没有进行中的活动哦~");
        }

        return activityConverter.activityEntityToActivityResp(activity);
    }

    @Override
    @Transactional
    public ActivityStatResp doLottery(DoLotteryReq req) {

        Activity activity = activityMapper.selectByPrimaryKey(req.getActivityId());
        if (activity == null) {
            throw new CommonException("活动已删除~");
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(activity.getEndTime())) {
            throw new CommonException("抽奖活动还没开始~");
        }

        if (now.isBefore(activity.getStartTime())) {
            throw new CommonException("抽奖活动已经结束啦~");
        }

        int count = drawRecordMapper.selectCountByExample(Example.builder(DrawRecord.class)
                .where(WeekendSqls.<DrawRecord>custom()
                        .andEqualTo(DrawRecord::getActivityId, req.getActivityId())
                        .andEqualTo(DrawRecord::getUserId, req.getUserId()))
                .build());
        if (count > 0) {
            throw new CommonException("已经抽过奖啦，不要太贪心啦~");
        }

        // 获取该用户本场活动的礼物信息。
        List<ActivityStatResp> currentActivityStatList = activityStatService.findCurrentActivityStatList(CurrentActivityStatReq.builder().activityId(req.getActivityId()).userId(req.getUserId()).build());
        if (CollectionUtils.isEmpty(currentActivityStatList)) {
            throw new CommonException("没有您的专属抽奖活动哦，是不是想要的礼物太少了呢~");
        }

        // 抽奖
        ActivityStatResp resultGift = this.doLottery(currentActivityStatList);

        // 生成抽奖记录
        DrawRecord drawRecord = activityConverter.activityStatRespToDrawRecordEntity(resultGift, req.getUserId());
        drawRecordMapper.insertSelective(drawRecord);

        // 修改礼物已购买
        Gift gift = new Gift();
        gift.setId(drawRecord.getGiftId());
        gift.setIsBuy(true);
        giftMapper.updateByPrimaryKeySelective(gift);
        return resultGift;
    }

    private ActivityStatResp doLottery(List<ActivityStatResp> currentActivityStatList) {
        Map<Long, ActivityStatResp> giftMap = currentActivityStatList.stream().collect(Collectors.toMap(ActivityStatResp::getGiftId, entity -> entity));
        Map<Long, Double> giftWinRateMap = currentActivityStatList.stream().collect(Collectors.toMap(ActivityStatResp::getGiftId, ActivityStatResp::getWinRate));
        // 抽奖
        Long selectGiftId = select(giftWinRateMap);
        return giftMap.get(selectGiftId);
    }

    // 获取随机角度,然后计算落在哪个物品上
    private Long select(Map<Long, Double> giftWinRateMap) {
        LotteryDraw<Long, Double> lotteryDraw = new LotteryDraw<>(giftWinRateMap);
        return lotteryDraw.draw();
    }

    private List<ActivityResp> convertActivityListToActivityRespList(List<Activity> activityList) {
        List<User> userList = userMapper.selectAll();
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, user -> user));

        List<SpecialDate> specialDateList = specialDateMapper.selectByExample(Example.builder(SpecialDate.class)
                .where(WeekendSqls.<SpecialDate>custom()
                        .andEqualTo(SpecialDate::getIsDelete, IsDelEnum.NO.getCode()))
                .build());
        Map<Long, SpecialDate> specialDateMap = specialDateList.stream().collect(Collectors.toMap(SpecialDate::getId, specialDate -> specialDate));

        return activityList.stream().map(activity -> {
            ActivityResp activityResp = activityConverter.activityEntityToActivityResp(activity);
            activityResp.setCreateUserName(userMap.get(Long.valueOf(activity.getCreateUser())).getName());
            activityResp.setHolidayName(specialDateMap.get(activity.getHolidayId()).getHolidayName());
            return activityResp;
        }).collect(Collectors.toList());
    }
}