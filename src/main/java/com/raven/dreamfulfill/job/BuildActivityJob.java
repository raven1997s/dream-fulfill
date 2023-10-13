package com.raven.dreamfulfill.job;

import com.alibaba.fastjson.JSON;
import com.raven.dreamfulfill.domain.entity.SpecialDate;
import com.raven.dreamfulfill.domain.req.activity.AddActivityReq;
import com.raven.dreamfulfill.mapper.SpecialDateMapper;
import com.raven.dreamfulfill.service.IActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Description:
 * date: 2023/10/12 11:00
 *
 * @author longjiaocao
 */
@Component
@Slf4j
public class BuildActivityJob {

    @Autowired
    private IActivityService activityService;

    @Autowired
    private SpecialDateMapper specialDateMapper;

    /**
     * 每天凌晨自动生成活动，提前一周生成
     */
    @Scheduled(cron = "*/2 * * * * ?")
    public void buildActivityJob() {
        // 获取一周后的那天有没有节日，如果有节日则生成活动
        LocalDateTime holidayTime = LocalDateTime.now().plusDays(7);

        SpecialDate specialDate = specialDateMapper.selectOneByExample(Example.builder(SpecialDate.class)
                .where(WeekendSqls.<SpecialDate>custom()
                        .andBetween(SpecialDate::getHolidayTime, holidayTime.with(LocalTime.MIN), holidayTime.with(LocalTime.MAX)))
                .build());

        if (specialDate == null) {
            log.info(JSON.toJSONString(holidayTime) + "无节日,不需要生成活动");
            return;
        }

        AddActivityReq activityReq = AddActivityReq.builder()
                .theme(holidayTime.getYear() + "年" + specialDate.getHolidayName() + "互送礼物活动")
                .description("平时辛苦啦，这是系统自动生成的法定节假日活动，来试试运气吧~")
                .rule("可在下面的礼物中随机抽取一个，一场活动只能抽取一次哦~")
                .startTime(holidayTime.with(LocalTime.MIN))
                .endTime(holidayTime.with(LocalTime.MAX))
                .holidayId(specialDate.getId())
                .createUser(Math.toIntExact(specialDate.getCreateId()))
                .build();

        try {
            activityService.addActivity(activityReq);
        } catch (Exception e) {
            log.error("生成活动失败", e);
        }
    }
}