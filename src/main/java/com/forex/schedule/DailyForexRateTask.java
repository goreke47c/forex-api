package com.forex.schedule;

import com.forex.dto.ForexRateData;
import com.forex.service.ForexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyForexRateTask {

    private static final Logger log = LoggerFactory.getLogger(DailyForexRateTask.class);

    @Autowired
    ForexService forexService;

    public void manualTaskTrigger() throws Exception {
        taskProcess();
    }

    @Scheduled(cron = "0 0 18 * * ?")
    public void fetchForexRate() throws Exception {
        taskProcess();
    }

    private void taskProcess() throws Exception {
        log.info("DailyForeignExchangeRateTask start");
        ForexRateData[] forexRateDataArray = forexService.fetchDailyForexData();
        for (ForexRateData forexRateData : forexRateDataArray) {
            forexService.saveForexRateData(forexRateData);
        }
        log.info("DailyForeignExchangeRateTask end");
    }
}
