package com.forex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForexRateData {
    @JsonProperty("Date")
    String date;
    @JsonProperty("USD/NTD")
    String usdToNtd;
    @JsonProperty("RMB/NTD")
    String rmbToNtd;
    @JsonProperty("EUR/USD")
    String eurToUsd;
    @JsonProperty("USD/JPY")
    String usdToJpy;
    @JsonProperty("GBP/USD")
    String gbpToUsd;
    @JsonProperty("AUD/USD")
    String audToUsd;
    @JsonProperty("USD/HKD")
    String usdToHkd;
    @JsonProperty("USD/RMB")
    String usdToRmb;
    @JsonProperty("USD/ZAR")
    String usdToZar;
    @JsonProperty("NZD/USD")
    String nzdToUsd;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsdToNtd() {
        return usdToNtd;
    }

    public void setUsdToNtd(String usdToNtd) {
        this.usdToNtd = usdToNtd;
    }

    public String getRmbToNtd() {
        return rmbToNtd;
    }

    public void setRmbToNtd(String rmbToNtd) {
        this.rmbToNtd = rmbToNtd;
    }

    public String getEurToUsd() {
        return eurToUsd;
    }

    public void setEurToUsd(String eurToUsd) {
        this.eurToUsd = eurToUsd;
    }

    public String getUsdToJpy() {
        return usdToJpy;
    }

    public void setUsdToJpy(String usdToJpy) {
        this.usdToJpy = usdToJpy;
    }

    public String getGbpToUsd() {
        return gbpToUsd;
    }

    public void setGbpToUsd(String gbpToUsd) {
        this.gbpToUsd = gbpToUsd;
    }

    public String getAudToUsd() {
        return audToUsd;
    }

    public void setAudToUsd(String audToUsd) {
        this.audToUsd = audToUsd;
    }

    public String getUsdToHkd() {
        return usdToHkd;
    }

    public void setUsdToHkd(String usdToHkd) {
        this.usdToHkd = usdToHkd;
    }

    public String getUsdToRmb() {
        return usdToRmb;
    }

    public void setUsdToRmb(String usdToRmb) {
        this.usdToRmb = usdToRmb;
    }

    public String getUsdToZar() {
        return usdToZar;
    }

    public void setUsdToZar(String usdToZar) {
        this.usdToZar = usdToZar;
    }

    public String getNzdToUsd() {
        return nzdToUsd;
    }

    public void setNzdToUsd(String nzdToUsd) {
        this.nzdToUsd = nzdToUsd;
    }
}