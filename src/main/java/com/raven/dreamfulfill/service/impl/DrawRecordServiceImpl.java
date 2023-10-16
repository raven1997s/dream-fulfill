package com.raven.dreamfulfill.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.converter.DrawRecordConverter;
import com.raven.dreamfulfill.domain.entity.*;
import com.raven.dreamfulfill.domain.enums.IsDelEnum;
import com.raven.dreamfulfill.domain.req.drawrecord.AddDrawRecordReq;
import com.raven.dreamfulfill.domain.req.drawrecord.PageQueryDrawRecordListReq;
import com.raven.dreamfulfill.domain.resp.drawrecord.DrawRecordResp;
import com.raven.dreamfulfill.mapper.*;
import com.raven.dreamfulfill.service.IDrawRecordService;
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
public class DrawRecordServiceImpl implements IDrawRecordService {

    @Autowired
    private DrawRecordConverter drawRecordConverter;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GiftMapper giftMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private SpecialDateMapper specialDateMapper;
    @Autowired
    private DrawRecordMapper drawRecordMapper;


    @Override
    public PageResult<DrawRecordResp> pageQueryDrawRecordList(PageQueryDrawRecordListReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());

        List<DrawRecord> drawRecordList = drawRecordMapper.selectByExample(Example.builder(DrawRecord.class)
                .orderByDesc("id")
                .build());
        PageInfo<DrawRecord> pageInfo = new PageInfo<>(drawRecordList);

        return CollectionUtils.isEmpty(drawRecordList) ? new PageResult<>(Collections.emptyList())
                : new PageResult<>(this.convertDrawRecordListToDrawRecordRespList(drawRecordList),
                pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getPageNum());
    }

    @Override
    public void addDrawRecord(AddDrawRecordReq req) {

    }


    private List<DrawRecordResp> convertDrawRecordListToDrawRecordRespList(List<DrawRecord> drawRecordList) {
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

        return drawRecordList.stream().map(specialDate -> {
            DrawRecordResp drawRecordResp = drawRecordConverter.drawRecordEntityToDrawRecordResp(specialDate);
            drawRecordResp.setUserName(userMap.get(specialDate.getUserId()).getName());
            drawRecordResp.setActivityTheme(activityMap.get(specialDate.getActivityId()).getTheme());
            drawRecordResp.setGiftName(giftMap.get(specialDate.getGiftId()).getGiftName());
            drawRecordResp.setHolidayName(specialDateMap.get(specialDate.getHolidayId()).getHolidayName());
            return drawRecordResp;
        }).collect(Collectors.toList());
    }

}