package com.oukele.model;

public class CommodityTag {
    private Integer commodityTagId;

    private String commodityTagName;

    public Integer getCommodityTagId() {
        return commodityTagId;
    }

    public void setCommodityTagId(Integer commodityTagId) {
        this.commodityTagId = commodityTagId;
    }

    public String getCommodityTagName() {
        return commodityTagName;
    }

    public void setCommodityTagName(String commodityTagName) {
        this.commodityTagName = commodityTagName == null ? null : commodityTagName.trim();
    }
}