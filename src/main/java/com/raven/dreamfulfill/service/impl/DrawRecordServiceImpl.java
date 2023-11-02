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
        Map<Long, User> userMap = userMapper.selectAll().stream().collect(Collectors.toMap(User::getId, user -> user));

        Map<Long, Activity> activityMap = activityMapper.selectAll().stream().collect(Collectors.toMap(Activity::getId, activity -> activity));

        Map<Long, Gift> giftMap = giftMapper.selectAll().stream().collect(Collectors.toMap(Gift::getId, gift -> gift));

        Map<Long, SpecialDate> specialDateMap = specialDateMapper.selectAll().stream().collect(Collectors.toMap(SpecialDate::getId, specialDate -> specialDate));

        return drawRecordList.stream().map(drawRecord -> {
            DrawRecordResp drawRecordResp = drawRecordConverter.drawRecordEntityToDrawRecordResp(drawRecord);
            drawRecordResp.setUserName(userMap.get(drawRecord.getUserId()).getName());
            drawRecordResp.setActivityTheme(activityMap.get(drawRecord.getActivityId()).getTheme());
            drawRecordResp.setGiftName(giftMap.get(drawRecord.getGiftId()).getGiftName());
            drawRecordResp.setHolidayName(specialDateMap.get(drawRecord.getHolidayId()).getHolidayName());
            return drawRecordResp;
        }).collect(Collectors.toList());
    }

}