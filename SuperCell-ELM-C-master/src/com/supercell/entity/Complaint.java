package com.supercell.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Complaint extends GenericEntity {

    public static final String CUSTOMER_ID = "customerId";
    public static final String ORDER_ID = "orderId";

    @Column
    private Integer customerId;
    @Column
    private String phoneNumber;
    @Column
    private Integer merchantId;
    @Column
    private String merchantName;
    @Column
    private String complainMessage;
    @Column
    private Integer orderId;


    public Complaint() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerID) {
        this.customerId = customerID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getComplainMessage() {
        return complainMessage;
    }

    public void setComplainMessage(String complainMessage) {
        this.complainMessage = complainMessage;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderID(Integer orderID) {
        this.orderId = orderID;
    }

}
