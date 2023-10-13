package com.raven.dreamfulfill.job;

import com.raven.dreamfulfill.service.ISpecialDateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Description:
 * date: 2023/10/12 11:00
 * 每年生成一次指定的节日
 * @author longjiaocao
 */
@Component
@Slf4j
public class BuildSpecialDateJob {

     @Autowired
     private ISpecialDateService specialDateService;
    @Scheduled(cron = "0 0 12 31 12 *")
    public void buildSpecialDateJob() {
        int nextYear = LocalDateTime.now().plusDays(1).getDayOfYear();
        specialDateService.insertSpecialDateByYear(nextYear);
    }
}