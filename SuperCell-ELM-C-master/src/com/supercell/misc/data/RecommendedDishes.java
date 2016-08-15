package com.supercell.misc.data;

/**
 * Created by ZHENGNE2 on 8/10/2016.
 */
public class RecommendedDishes {
    private Integer merchantId;
    private Integer dishesId;
    private String dishName;
    private String merchantName;
    private Boolean recommended;
    private String dishesImgPath;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getDishesId() {
        return dishesId;
    }

    public void setDishesId(Integer dishesId) {
        this.dishesId = dishesId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Boolean getRecommanded() {
        return recommended;
    }

    public void setRecommanded(Boolean recommended) {
        this.recommended = recommended;
    }

    public String getDishesImgPath() {
        return dishesImgPath;
    }

    public void setDishesImgPath(String dishesImgPath) {
        this.dishesImgPath = dishesImgPath;
    }
}
