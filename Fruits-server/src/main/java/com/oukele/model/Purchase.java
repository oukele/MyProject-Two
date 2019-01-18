package com.oukele.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Purchase {

    @Min(value = 1,message = "进货编号出现异常，请重试")
    private Integer purchaseId;
    @Min(value = 0,message = "进货数量不能少于(小于) 0")
    private Double purchaseCount;

    private Boolean purchaseStatus;

    @NotBlank(message = "请填写负责人")
    private String purchaseName;

    private Integer commodityCompanyId;

    private Integer supplierId;

    private Integer commodityTagId;

    private Supplier supplier;

    private CommodityCompany commodityCompany;

    private CommodityTag commodityTag;

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Double getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(Double purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public Boolean getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(Boolean purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName == null ? null : purchaseName.trim();
    }

    public Integer getCommodityCompanyId() {
        return commodityCompanyId;
    }

    public void setCommodityCompanyId(Integer commodityCompanyId) {
        this.commodityCompanyId = commodityCompanyId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }


    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

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

    public Integer getCommodityTagId() {
        return commodityTagId;
    }

    public void setCommodityTagId(Integer commodityTagId) {
        this.commodityTagId = commodityTagId;
    }
}