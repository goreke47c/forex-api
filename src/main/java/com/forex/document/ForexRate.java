package com.forex.document;

import com.forex.utils.DateUtils;
import com.forex.utils.SecurityUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.text.ParseException;
import java.util.Date;

@Document(collection = "forex_rate")
public class ForexRate {

    @Id
    private String id;

    @Field("code_from")
    private String codeFrom;

    @Field("code_to")
    private String codeTo;

    @Field("record_date")
    private Date recordDate;

    @Field("record_date_string")
    private String recordDateString;

    @Field("ex_rate")
    private String exRate;

    @Field("create_time")
    private Date createTime;

    public ForexRate(){

    }

    public ForexRate(String codeFromTo, String recordDateString, String exRate) throws ParseException {
        this.recordDateString = recordDateString;
        String[] code = codeFromTo.split("/");
        this.codeFrom = code[0];
        this.codeTo = code[1];
        // MD5(20240101usdntd)
        this.id = SecurityUtils.calculateMD5(recordDateString + this.codeFrom + this.codeTo);
        this.recordDate = DateUtils.parse(recordDateString, "yyyyMMdd");
        this.exRate = exRate;
        this.createTime = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodeFrom() {
        return codeFrom;
    }

    public void setCodeFrom(String codeFrom) {
        this.codeFrom = codeFrom;
    }

    public String getCodeTo() {
        return codeTo;
    }

    public void setCodeTo(String codeTo) {
        this.codeTo = codeTo;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordDateString() {
        return recordDateString;
    }

    public void setRecordDateString(String recordDateString) {
        this.recordDateString = recordDateString;
    }

    public String getExRate() {
        return exRate;
    }

    public void setExRate(String exRate) {
        this.exRate = exRate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
