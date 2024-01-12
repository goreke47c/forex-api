package com.forex.controller;

import com.forex.dto.ForexResponse;
import com.forex.dto.ForexRateRequest;
import com.forex.schedule.DailyForexRateTask;
import com.forex.service.ForexService;
import com.forex.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/api/forex")
public class ForexController {
    private final ForexService forexService;

    private final DailyForexRateTask dailyForeignExchangeRateTask;

    @Autowired
    public ForexController(ForexService forexService, DailyForexRateTask dailyForeignExchangeRateTask) {
        this.forexService = forexService;
        this.dailyForeignExchangeRateTask = dailyForeignExchangeRateTask;
    }

    @GetMapping("/fetchDailyForexData")
    public ResponseEntity<Object> fetchDailyForexData() throws Exception {
        dailyForeignExchangeRateTask.manualTaskTrigger();
        return new ResponseEntity<>(new ForexResponse("0000", "成功"), HttpStatus.OK);
    }

    @PostMapping(value = "/getForexRate")
    public ResponseEntity<Object> getForexRate(@RequestBody ForexRateRequest forexRateRequest) {
        if (!isValid(forexRateRequest)) {
            return new ResponseEntity<>(new ForexResponse("E001", "日期區間不符"), HttpStatus.OK);
        }
        return new ResponseEntity<>(forexService.genForexResponse(forexRateRequest), HttpStatus.OK);
    }

    private boolean isValid(ForexRateRequest forexRateRequest) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.HOUR, 0);
            Date currentDate = calendar.getTime();
            Date startDate = DateUtils.parse(forexRateRequest.getStartDate(), "yyyy/MM/dd");
            Date endDate = DateUtils.parse(forexRateRequest.getEndDate(), "yyyy/MM/dd");
            long timeDifference = startDate.getTime() - currentDate.getTime();
            long daysDifference = timeDifference / (24 * 60 * 60 * 1000);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            currentDate = calendar.getTime();
            // 檢查startDate是否在一年之內,endDate是否小於等於為當天-1
            return startDate.compareTo(endDate) < 0 && Math.abs(daysDifference) <= 365 && endDate.compareTo(currentDate) < 0;
        } catch (ParseException e) {
            return false;
        }
    }

}
