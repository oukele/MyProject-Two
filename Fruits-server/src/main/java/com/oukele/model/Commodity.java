package com.oukele.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Commodity {

    private Integer commodityId;

    @NotBlank(message = "商品简介未填写。")
    private String commodityName;

    private String commodityPhoto;
    @DecimalMin(value = "0.01",message = "价格最低为0.01")
    private BigDecimal commodityPrice;
    @Min(value = 1,message = "数量最少为1")
    private Double commodityCount;

    private Boolean commodityStatus;
    @Min(value = 1,message = "提交数据有错误")
    private Integer stockId;
    @NotNull
    private Integer commodityCompanyId;

    //商品单位
    private CommodityCompany commodityCompany;

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public String getCommodityPhoto() {
        return commodityPhoto;
    }

    public void setCommodityPhoto(String commodityPhoto) {
        this.commodityPhoto = commodityPhoto == null ? null : commodityPhoto.trim();
    }

    public BigDecimal getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(BigDecimal commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public Double getCommodityCount() {
        return commodityCount;
    }

    public void setCommodityCount(Double commodityCount) {
        this.commodityCount = commodityCount;
    }

    public Boolean getCommodityStatus() {
        return commodityStatus;
    }

    public void setCommodityStatus(Boolean commodityStatus) {
        this.commodityStatus = commodityStatus;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getCommodityCompanyId() {
        return commodityCompanyId;
    }

    public void setCommodityCompanyId(Integer commodityCompanyId) {
        this.commodityCompanyId = commodityCompanyId;
    }

    public CommodityCompany getCommodityCompany() {
        return commodityCompany;
    }

    public void setCommodityCompany(CommodityCompany commodityCompany) {
        this.commodityCompany = commodityCompany;
    }
}