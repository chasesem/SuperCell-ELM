package com.supercell.elmm.vo;

public class RecommendDish {
	private int merchantId;
	private int dishesId;
	private String merchantName;
	private String dishName;
	private boolean recommended;
	
	public boolean isRecommended() {
		return recommended;
	}
	public void setRecommended(boolean recommended) {
		this.recommended = recommended;
	}
	public RecommendDish() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RecommendDish(int merchantId, int dishesId, String merchantName,
			String dishName) {
		super();
		this.merchantId = merchantId;
		this.dishesId = dishesId;
		this.merchantName = merchantName;
		this.dishName = dishName;
	}
	public int getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	public int getDishesId() {
		return dishesId;
	}
	public void setDishesId(int dishesId) {
		this.dishesId = dishesId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	
	
}
