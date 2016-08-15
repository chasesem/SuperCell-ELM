package com.supercell.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by WUJO2 on 8/6/2016.
 */
@Entity
@Table
public class Dishes extends GenericEntity {
	public static final String MERCHANT_ID = "merchantId";
	public static final String DISHES_NAME = "dishesName";
	public static final String DISHES_PIC_PATH = "dishesPicPath";
	public static final String PRICE = "price";
	public static final String AVAILABLE = "avaliable";
	public static final String STOCK = "stock";

	@Column(nullable = false)
	private Integer merchantId;
	@Column(nullable = false)
	private String dishesName;
	@Column(nullable = false)
	private String dishesPicPath;
	@Column(nullable = false)
	private Integer price;
	@Column(nullable = false)
	private Boolean avaliable;
	@Column
	private Integer stock;

	public Dishes() {
	}

	public Dishes(Integer merchantId, String dishesName, String dishesPicPath, Integer price, Boolean avaliable,
			Integer stock) {
		this.merchantId = merchantId;
		this.dishesName = dishesName;
		this.dishesPicPath = dishesPicPath;
		this.price = price;
		this.avaliable = avaliable;
		this.stock = stock;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getDishesName() {
		return dishesName;
	}

	public void setDishesName(String dishesName) {
		this.dishesName = dishesName;
	}

	public String getDishesPicPath() {
		return dishesPicPath;
	}

	public void setDishesPicPath(String dishesPicPath) {
		this.dishesPicPath = dishesPicPath;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Boolean getAvaliable() {
		return avaliable;
	}

	public void setAvaliable(Boolean avaliable) {
		this.avaliable = avaliable;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
