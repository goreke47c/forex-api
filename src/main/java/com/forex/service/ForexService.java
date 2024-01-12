package com.forex.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forex.document.ForexRate;
import com.forex.dto.ForexRateData;
import com.forex.dto.ForexRateRequest;
import com.forex.dto.ForexResponse;
import com.forex.repo.ForexRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.*;

@Service
public class ForexService {

    private final RestTemplate restTemplate;

    private final ForexRateRepository forexRateRepository;

    @Autowired
    public ForexService(RestTemplate restTemplate, ForexRateRepository forexRateRepository) {
        this.restTemplate = restTemplate;
        this.forexRateRepository = forexRateRepository;
    }

    public List<ForexRate> findByCodeBetween(String codeFrom, String codeTo, String start, String end) {
        String startDate = start.replace("/", "");
        String endDate = end.replace("/", "");

        return forexRateRepository.findByCodeFromAndCodeToBetweenDateString(codeFrom, codeTo, startDate, endDate);
    }

    public ForexRateData[] fetchDailyForexData() throws Exception {
        String forexUrl = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";
        String responseBody = restTemplate.getForEntity(forexUrl, String.class).getBody();
        ObjectMapper om = new ObjectMapper();
        return om.readValue(responseBody, ForexRateData[].class);
    }

    public void saveForexRateData(ForexRateData forexRateData) throws ParseException {
        ArrayList<ForexRate> forexRateArrayList = convertDataToDocumentList(forexRateData);
        forexRateRepository.saveAll(forexRateArrayList);
    }

    public ForexResponse genForexResponse(ForexRateRequest forexRateRequest) {
        ForexResponse forexResponse = new ForexResponse("0000", "成功");
        List<ForexRate> forexRateList = this.findByCodeBetween(forexRateRequest.getCurrency(), "ntd", forexRateRequest.getStartDate(), forexRateRequest.getEndDate());
        ArrayList<Map<String, String>> currencyList = new ArrayList<>();
        for (ForexRate forexRate : forexRateList) {
            Map<String, String> currencyMap = new HashMap<>();
            currencyMap.put("date", forexRate.getRecordDateString());
            currencyMap.put(forexRateRequest.getCurrency(), forexRate.getExRate());
            currencyList.add(currencyMap);
        }
        forexResponse.setCurrency(currencyList);
        return forexResponse;
    }

    private ArrayList<ForexRate> convertDataToDocumentList(ForexRateData forexRateData) throws ParseException {
        ArrayList<ForexRate> forexRateArrayList = new ArrayList<>();
        forexRateArrayList.add(new ForexRate("usd/ntd", forexRateData.getDate(), forexRateData.getUsdToNtd()));
        forexRateArrayList.add(new ForexRate("rmb/ntd", forexRateData.getDate(), forexRateData.getRmbToNtd()));
        forexRateArrayList.add(new ForexRate("eur/usd", forexRateData.getDate(), forexRateData.getEurToUsd()));
        forexRateArrayList.add(new ForexRate("usd/jpy", forexRateData.getDate(), forexRateData.getUsdToJpy()));
        forexRateArrayList.add(new ForexRate("gbp/usd", forexRateData.getDate(), forexRateData.getGbpToUsd()));
        forexRateArrayList.add(new ForexRate("aud/usd", forexRateData.getDate(), forexRateData.getAudToUsd()));
        forexRateArrayList.add(new ForexRate("usd/hkd", forexRateData.getDate(), forexRateData.getUsdToHkd()));
        forexRateArrayList.add(new ForexRate("usd/rmb", forexRateData.getDate(), forexRateData.getUsdToRmb()));
        forexRateArrayList.add(new ForexRate("usd/zar", forexRateData.getDate(), forexRateData.getUsdToZar()));
        forexRateArrayList.add(new ForexRate("nzd/usd", forexRateData.getDate(), forexRateData.getNzdToUsd()));
        return forexRateArrayList;
    }
}
