package com.forex.schedule;

import com.forex.app.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class DailyForexRateTaskTest {

    Logger log = LoggerFactory.getLogger(DailyForexRateTaskTest.class);

    @Autowired
    private DailyForexRateTask dailyForexRateTask;

    @Test
    public void testFetchForexRate() throws Exception {
        log.info("testFetchForexRate start");
        dailyForexRateTask.manualTaskTrigger();
        log.info("testFetchForexRate end");
    }
}
