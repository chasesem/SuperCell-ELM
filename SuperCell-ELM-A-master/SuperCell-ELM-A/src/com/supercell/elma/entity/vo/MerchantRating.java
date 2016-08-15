package com.supercell.elma.entity.vo;

public class MerchantRating {
	private Integer merchantId;
	private double rating;
	public MerchantRating(){
		
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
}
