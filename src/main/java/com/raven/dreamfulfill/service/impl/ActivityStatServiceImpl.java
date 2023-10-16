package com.raven.dreamfulfill.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.common.exception.CommonException;
import com.raven.dreamfulfill.converter.ActivityStatConverter;
import com.raven.dreamfulfill.domain.dto.activity.stat.InsertActivityStatDTO;
import com.raven.dreamfulfill.domain.entity.*;
import com.raven.dreamfulfill.domain.enums.IsDelEnum;
import com.raven.dreamfulfill.domain.enums.IsYesEnum;
import com.raven.dreamfulfill.domain.enums.SpecialDateLevelEnum;
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

import java.math.BigDecimal;
import java.util.*;
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

        List<ActivityStat> activityStats = activityStatMapper.selectByExample(Example.builder(ActivityStat.class)
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
        // 获取每个用户礼物 礼物按照心动值高低排序 筛选出前6个，如果不够6个，就不能抽
        List<Gift> allGiftList = giftMapper.selectByExample(Example.builder(Gift.class)
                .where(WeekendSqls.<Gift>custom()
                        .andEqualTo(Gift::getIsBuy, IsYesEnum.NO.getCode())
                        .andEqualTo(Gift::getIsDelete, IsDelEnum.NO.getCode()))
                .orderByDesc("infatuationScore")
                .build());

        if (CollectionUtils.isEmpty(allGiftList)){
            throw new CommonException("还没有心愿呢~");
        }

        // 获取节日的详情信息
        SpecialDate specialDate = specialDateMapper.selectByPrimaryKey(dto.getHolidayId());
        if (specialDate == null) {
            throw new CommonException("节日不存在");
        }

        // 获取每个礼物参加活动次数
        List<Long> giftIdList = allGiftList.stream().map(Gift::getId).distinct().collect(Collectors.toList());
        Map<Long, Long> giftJoinActivityCountMap = null;
        if (CollectionUtils.isNotEmpty(giftIdList)) {
            List<ActivityStat> activityStats = activityStatMapper.selectByExample(Example.builder(ActivityStat.class)
                    .where(WeekendSqls.<ActivityStat>custom()
                            .andIn(ActivityStat::getActivityId, giftIdList)).build());

            giftJoinActivityCountMap = activityStats.stream().collect(Collectors.groupingBy(ActivityStat::getGiftId, Collectors.counting()));
        }

        // 按照用户分组
        Map<Long, Long> finalGiftJoinActivityCountMap = giftJoinActivityCountMap;
        List<ActivityStat> activityStatList = allGiftList.stream().collect(Collectors.groupingBy(Gift::getCreateId)).entrySet().stream()
                // 有效礼物太少，不生成抽奖活动信息
                .filter(entry -> entry.getValue().size() > 3)
                // 筛选出心动值排名靠前的五个礼物
                .peek(entry -> entry.setValue(entry.getValue().subList(0, 3)))
                // 计算每个用户礼物的中奖概率
                .flatMap(entry -> {
                    List<Gift> userGiftList = entry.getValue();
                    // 计算各礼物中奖概率
                    Map<Long, Double> giftRateMap = this.calculateGiftRate(userGiftList, SpecialDateLevelEnum.getEnumByValue(specialDate.getLevel()), finalGiftJoinActivityCountMap);
                    return userGiftList.stream().map(gift -> activityStatConverter.giftToActivityStat(dto, entry.getKey(), gift, giftRateMap.get(gift.getId())));
                }).collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(activityStatList)) {
            activityStatMapper.insertList(activityStatList);
        }
    }

    /**
     * 计算用户本次抽奖活动各礼物的中奖概率
     * 中奖概率 = score/ sum score
     *
     * @param giftList
     * @param level
     * @param giftJoinActivityCountMap
     * @return
     */
    private Map<Long, Double> calculateGiftRate(List<Gift> giftList, SpecialDateLevelEnum level, Map<Long, Long> giftJoinActivityCountMap) {
        Map<Long, Double> giftSocreMap = giftList.stream().collect(Collectors.toMap(Gift::getId, gift -> calculateGiftScore(level, gift, giftJoinActivityCountMap)));
        double totalScore = giftSocreMap.values().stream().mapToDouble(Double::doubleValue).sum();
        return giftSocreMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue() / totalScore));
    }

    // 计算各礼物的得分
    private double calculateGiftScore(SpecialDateLevelEnum level, Gift gift, Map<Long, Long> giftJoinActivityCountMap) {
        // 实用性得分
        double utilityScore = gift.getPracticalityValue().multiply(BigDecimal.valueOf(level.getPracticalityRate())).doubleValue();
        // 价格得分
        double priceScore = gift.getPrice().multiply(BigDecimal.valueOf(level.getPriceScoreRate())).doubleValue();
        // 历史参加活动次数得分 = 历次参加活动次数 * 5
        double giftJoinActivityCountScore = giftJoinActivityCountMap.getOrDefault(gift.getId(),0L) * 5;
        // 心动值得分
        double infatuationScore = gift.getInfatuationScore().multiply(BigDecimal.valueOf(30)).doubleValue();
        // 计算礼物总得分
        return utilityScore + priceScore + giftJoinActivityCountScore + infatuationScore;
    }


    private List<ActivityStatResp> convertActivityStatListToActivityStatRespList(List<ActivityStat> activityStatList) {
        List<User> userList = userMapper.selectAll();
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, user -> user));

        List<Activity> activities = activityMapper.selectByExample(Example.builder(Activity.class)
                .where(WeekendSqls.<Activity>custom()
                        .andEqualTo(Activity::getIsDelete, IsDelEnum.NO.getCode()))
                .build());
        Map<Long, Activity> activityMap = activities.stream().collect(Collectors.toMap(Activity::getId, activity -> activity));

        List<Gift> giftList = giftMapper.selectByExample(Example.builder(Gift.class)
                .where(WeekendSqls.<Gift>custom()
                        .andEqualTo(Gift::getIsDelete, IsDelEnum.NO.getCode()))
                .build());
        Map<Long, Gift> giftMap = giftList.stream().collect(Collectors.toMap(Gift::getId, gift -> gift));

        List<SpecialDate> specialDateList = specialDateMapper.selectByExample(Example.builder(SpecialDate.class)
                .where(WeekendSqls.<SpecialDate>custom()
                        .andEqualTo(SpecialDate::getIsDelete, IsDelEnum.NO.getCode()))
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