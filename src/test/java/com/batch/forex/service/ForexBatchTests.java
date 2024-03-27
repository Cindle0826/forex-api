package com.batch.forex.service;

import com.batch.forex.task.ScheduleTask;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

@SpringBootTest
public class ForexBatchTests implements SchedulingConfigurer {
    @MockBean
    private ForexService forexService;

    @Autowired
    private ScheduleTask scheduleTask;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(1));
    }

    /**
     * 測試 batch 功能
     * forexService saveAll() 功能無回傳值，所以實作 answer
     */
    @Test
    public void testSchedule() {
        scheduleTask.fetchDate();
        Mockito.doAnswer(invocationOnMock -> "call arguments " + invocationOnMock.getArguments())
                        .when(forexService).saveAll();

        Mockito.verify(forexService, Mockito.times(1)).saveAll();
    }
}
