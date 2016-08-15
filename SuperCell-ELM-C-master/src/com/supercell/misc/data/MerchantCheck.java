package com.supercell.misc.data;

/**
 * Created by WUJO2 on 8/14/2016.
 */
public class MerchantCheck {
    private Integer merchantId;
    private String merchantStateText;
    private Integer merchantState;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantStateText() {
        return merchantStateText;
    }

    public void setMerchantStateText(String merchantStateText) {
        this.merchantStateText = merchantStateText;
    }

    public Integer getMerchantState() {
        return merchantState;
    }

    public void setMerchantState(Integer merchantState) {
        this.merchantState = merchantState;
    }
}
