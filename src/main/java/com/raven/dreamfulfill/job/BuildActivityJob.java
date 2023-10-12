package com.raven.dreamfulfill.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Description:
 * date: 2023/10/12 11:00
 *
 * @author longjiaocao
 */
@Component
@Slf4j
public class BuildActivityJob {


    /**
     * 每天凌晨自动生成活动，提前一周生成
     */
    @Scheduled(cron = "*/2 * * * * ?")
    public void buildActivityJob() {
        // TODO wait
        // 获取一周后的那天有没有节日，如果有节日则生成活动
    }
}