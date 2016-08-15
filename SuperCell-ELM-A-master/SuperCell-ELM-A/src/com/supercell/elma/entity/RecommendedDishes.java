package com.supercell.elma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class RecommendedDishes{
	@Id
	@Column(name="merchantId",nullable = false)
	private Integer merchantId;
	@Column(name="dishesId" ,nullable = false)
	private Integer dishesId;
	@Column(name="dishesName",nullable = false)
	private String dishName;
	@Column(name="merchantName",nullable = false)
	private String merchantName;
	@Column(name="recommended" ,nullable = false)
	private Boolean recommended;

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

	public RecommendedDishes() {
	}

	public RecommendedDishes(Integer dishesId, Integer merchantId, Boolean recommended) {
		this.dishesId = dishesId;
		this.merchantId = merchantId;
		this.recommended = recommended;
	}

	public Integer getDishesId() {
		return dishesId;
	}

	public void setDishesId(Integer dishesId) {
		this.dishesId = dishesId;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public Boolean getRecommended() {
		return recommended;
	}

	public void setRecommended(Boolean recommended) {
		this.recommended = recommended;
	}

}
