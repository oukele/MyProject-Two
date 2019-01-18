package com.oukele.model;

public class CustomerAddress {
    private Integer customerAddressId;

    private String customerAddressTake;

    private String customerAddressTel;

    private String customerAddressInfo;

    private Integer customerId;

    public Integer getCustomerAddressId() {
        return customerAddressId;
    }

    public void setCustomerAddressId(Integer customerAddressId) {
        this.customerAddressId = customerAddressId;
    }

    public String getCustomerAddressTake() {
        return customerAddressTake;
    }

    public void setCustomerAddressTake(String customerAddressTake) {
        this.customerAddressTake = customerAddressTake == null ? null : customerAddressTake.trim();
    }

    public String getCustomerAddressTel() {
        return customerAddressTel;
    }

    public void setCustomerAddressTel(String customerAddressTel) {
        this.customerAddressTel = customerAddressTel == null ? null : customerAddressTel.trim();
    }

    public String getCustomerAddressInfo() {
        return customerAddressInfo;
    }

    public void setCustomerAddressInfo(String customerAddressInfo) {
        this.customerAddressInfo = customerAddressInfo == null ? null : customerAddressInfo.trim();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}