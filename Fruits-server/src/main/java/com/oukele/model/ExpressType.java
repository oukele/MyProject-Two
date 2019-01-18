package com.oukele.model;

public class ExpressType {
    private Integer expressTypeId;

    private String expressTypeName;

    public Integer getExpressTypeId() {
        return expressTypeId;
    }

    public void setExpressTypeId(Integer expressTypeId) {
        this.expressTypeId = expressTypeId;
    }

    public String getExpressTypeName() {
        return expressTypeName;
    }

    public void setExpressTypeName(String expressTypeName) {
        this.expressTypeName = expressTypeName == null ? null : expressTypeName.trim();
    }
}