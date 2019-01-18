package com.oukele.model;

public class CommodityCompany {
    private Integer commodityCompanyId;

    private String commodityCompanyName;

    public Integer getCommodityCompanyId() {
        return commodityCompanyId;
    }

    public void setCommodityCompanyId(Integer commodityCompanyId) {
        this.commodityCompanyId = commodityCompanyId;
    }

    public String getCommodityCompanyName() {
        return commodityCompanyName;
    }

    public void setCommodityCompanyName(String commodityCompanyName) {
        this.commodityCompanyName = commodityCompanyName == null ? null : commodityCompanyName.trim();
    }
}