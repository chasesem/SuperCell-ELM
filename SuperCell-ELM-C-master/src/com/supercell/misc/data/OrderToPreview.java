package com.supercell.misc.data;

import java.util.List;

public class OrderToPreview {

    private String shopName;
    private Integer merchantId;
    private List<OrderItemDetails> oidList;
    private String username;
    private String address;
    private Integer total;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<OrderItemDetails> getOidList() {
        return oidList;
    }

    public void setOipList(List<OrderItemDetails> oidList) {
        this.oidList = oidList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
