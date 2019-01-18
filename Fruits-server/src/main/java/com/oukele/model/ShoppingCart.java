package com.oukele.model;

public class ShoppingCart {
    private Integer shoppingCartId;

    private Integer shoppingCartCount;

    private Boolean shoppingCartStatus;

    private Integer commodityId;

    private Integer customerId;

    public Integer getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Integer shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Integer getShoppingCartCount() {
        return shoppingCartCount;
    }

    public void setShoppingCartCount(Integer shoppingCartCount) {
        this.shoppingCartCount = shoppingCartCount;
    }

    public Boolean getShoppingCartStatus() {
        return shoppingCartStatus;
    }

    public void setShoppingCartStatus(Boolean shoppingCartStatus) {
        this.shoppingCartStatus = shoppingCartStatus;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}