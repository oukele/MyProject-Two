package com.oukele.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class Supplier {

    @Min(value = 1, message = "编号出现错误，请联系管理员")
    private Integer supplierId;
    @NotBlank(message = "供应商名称不能为空")
    private String supplierName;
    @NotBlank(message = "供应商名称不能为空")
    private String supplierAddress;

    @NotBlank(message = "供应商电话不能为空")
    @Pattern(regexp = "^[0-9]{11}$",message = "供应商联系电话格式不正确")
    private String supplierTel;

    private Boolean supplierStatus;

    private String supplierRemarks;

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress == null ? null : supplierAddress.trim();
    }

    public String getSupplierTel() {
        return supplierTel;
    }

    public void setSupplierTel(String supplierTel) {
        this.supplierTel = supplierTel == null ? null : supplierTel.trim();
    }

    public Boolean getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(Boolean supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    public String getSupplierRemarks() {
        return supplierRemarks;
    }

    public void setSupplierRemarks(String supplierRemarks) {
        this.supplierRemarks = supplierRemarks == null ? null : supplierRemarks.trim();
    }
}