package com.oukele.model;

import java.util.Date;

public class Express {
    private Integer expressId;

    private String expressNumber;

    private Date expressDatetime;

    private Integer expressTypeId;

    public Integer getExpressId() {
        return expressId;
    }

    public void setExpressId(Integer expressId) {
        this.expressId = expressId;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber == null ? null : expressNumber.trim();
    }

    public Date getExpressDatetime() {
        return expressDatetime;
    }

    public void setExpressDatetime(Date expressDatetime) {
        this.expressDatetime = expressDatetime;
    }

    public Integer getExpressTypeId() {
        return expressTypeId;
    }

    public void setExpressTypeId(Integer expressTypeId) {
        this.expressTypeId = expressTypeId;
    }
}