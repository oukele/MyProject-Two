package com.oukele.model;



public class Customer {
    private Integer customerId;

    private String customerUser;

    private String customerPassword;

    private String customerName;

    private String customerPhoto;

    private String customerTel;

    private String customerCard;

    private boolean customerState;

    public boolean getCustomerState() {
        return customerState;
    }

    public void setCustomerState(boolean customerState) {
        this.customerState = customerState;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerUser() {
        return customerUser;
    }

    public void setCustomerUser(String customerUser) {
        this.customerUser = customerUser == null ? null : customerUser.trim();
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword == null ? null : customerPassword.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerPhoto() {
        return customerPhoto;
    }

    public void setCustomerPhoto(String customerPhoto) {
        this.customerPhoto = customerPhoto == null ? null : customerPhoto.trim();
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel == null ? null : customerTel.trim();
    }

    public String getCustomerCard() {
        return customerCard;
    }

    public void setCustomerCard(String customerCard) {
        this.customerCard = customerCard == null ? null : customerCard.trim();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerUser='" + customerUser + '\'' +
                ", customerPassword='" + customerPassword + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhoto='" + customerPhoto + '\'' +
                ", customerTel='" + customerTel + '\'' +
                ", customerCard='" + customerCard + '\'' +
                '}';
    }
}