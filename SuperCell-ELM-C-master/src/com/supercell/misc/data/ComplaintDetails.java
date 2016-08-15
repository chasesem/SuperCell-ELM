package com.supercell.misc.data;

import java.util.Date;
import java.util.List;

/**
 * Created by SHAZA on 8/13/2016.
 */
public class ComplaintDetails {
    private Integer customerId;
    private String phoneNumber;
    private Integer merchantId;
    private String merchantName;
    private Integer orderId;
    private String complainMessage;
    private String shopPicPath;
    private Date dateOfOrder;
    private List<OrderItemDetails> oidList;
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getShopPicPath() {return shopPicPath;}
    public void setShopPicPath(String shopPicPath) {this.shopPicPath = shopPicPath;}
    public Date getDateOfOrder() {return dateOfOrder;}
    public void setDateOfOrder(Date dateOfOrder) {this.dateOfOrder = dateOfOrder;}
    public List<OrderItemDetails> getOidList() {return oidList;}
    public void setOidList(List<OrderItemDetails> oidList) {this.oidList = oidList;}

    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getMerchantName() {
        return merchantName;
    }
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getMerchantId() {
        return merchantId;
    }
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }
    public String getComplainMessage() {
        return complainMessage;
    }
    public void setComplainMessage(String complainMessage) {
        this.complainMessage = complainMessage;
    }
}
