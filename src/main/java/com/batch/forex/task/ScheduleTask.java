package com.batch.forex.task;

import com.batch.forex.service.ForexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduleTask {
    @Autowired
    private ForexService forexService;

//    @Scheduled(fixedDelay = 10 * 1000, initialDelay = 5 * 1000)
    @Scheduled(cron = "0 0 18 * * ?", zone = "Asia/Taipei")
    public void fetchDate() {
        forexService.saveAll();
        System.out.println("資料獲取成功 當前時間 : " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
    }
}
