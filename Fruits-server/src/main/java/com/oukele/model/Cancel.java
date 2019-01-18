package com.oukele.model;

import java.util.Date;

public class Cancel {
    private Integer cancelId;

    private Date cancelDatetime;

    private String cancelRemarks;

    private Boolean cancelStatus;

    private Integer oderId;

    private Integer customerId;

    public Integer getCancelId() {
        return cancelId;
    }

    public void setCancelId(Integer cancelId) {
        this.cancelId = cancelId;
    }

    public Date getCancelDatetime() {
        return cancelDatetime;
    }

    public void setCancelDatetime(Date cancelDatetime) {
        this.cancelDatetime = cancelDatetime;
    }

    public String getCancelRemarks() {
        return cancelRemarks;
    }

    public void setCancelRemarks(String cancelRemarks) {
        this.cancelRemarks = cancelRemarks == null ? null : cancelRemarks.trim();
    }

    public Boolean getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(Boolean cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    public Integer getOderId() {
        return oderId;
    }

    public void setOderId(Integer oderId) {
        this.oderId = oderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}