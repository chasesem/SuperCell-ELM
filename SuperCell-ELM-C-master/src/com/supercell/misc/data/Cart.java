package com.supercell.misc.data;

import java.util.List;

/**
 * Created by WUJO2 on 8/6/2016.
 */
public class Cart {
    private Integer userId;
    private Integer merchantId;
    private List<CartItem> items;
    public Integer getUserId() {
        return userId;
    }

    public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }
    public Cart(){
    }
}
