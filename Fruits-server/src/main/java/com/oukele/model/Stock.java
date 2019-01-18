package com.oukele.model;

import java.util.Date;

public class Stock {
    private Integer stockId;

    private Double stockCount;

    private String stockRemarks;

    private Date stockDatetime;

    private Integer purchaseId;

    private Integer commodityTagId;

    private Integer commodityCompanyId;

    private CommodityTag commodityTag;

    private CommodityCompany commodityCompany;

    public CommodityCompany getCommodityCompany() {
        return commodityCompany;
    }

    public void setCommodityCompany(CommodityCompany commodityCompany) {
        this.commodityCompany = commodityCompany;
    }

    public CommodityTag getCommodityTag() {
        return commodityTag;
    }

    public void setCommodityTag(CommodityTag commodityTag) {
        this.commodityTag = commodityTag;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Double getStockCount() {
        return stockCount;
    }

    public void setStockCount(Double stockCount) {
        this.stockCount = stockCount;
    }

    public String getStockRemarks() {
        return stockRemarks;
    }

    public void setStockRemarks(String stockRemarks) {
        this.stockRemarks = stockRemarks == null ? null : stockRemarks.trim();
    }

    public Date getStockDatetime() {
        return stockDatetime;
    }

    public void setStockDatetime(Date stockDatetime) {
        this.stockDatetime = stockDatetime;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getCommodityTagId() {
        return commodityTagId;
    }

    public void setCommodityTagId(Integer commodityTagId) {
        this.commodityTagId = commodityTagId;
    }

    public Integer getCommodityCompanyId() {
        return commodityCompanyId;
    }

    public void setCommodityCompanyId(Integer commodityCompanyId) {
        this.commodityCompanyId = commodityCompanyId;
    }
}