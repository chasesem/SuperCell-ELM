package com.supercell.elmm.vo;

public class OrderItemDetails{
	private Integer dishId;
	private Integer merchantId;
	private String dishesName;
	private String dishesPicPath;
	private Integer price;
	private Boolean avaliable;
	private Integer stock;
	private int count;
	
	public OrderItemDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderItemDetails(Integer dishId, Integer merchantId,
			String dishesName, String dishesPicPath, Integer price,
			Boolean avaliable, Integer stock, int count) {
		super();
		this.dishId = dishId;
		this.merchantId = merchantId;
		this.dishesName = dishesName;
		this.dishesPicPath = dishesPicPath;
		this.price = price;
		this.avaliable = avaliable;
		this.stock = stock;
		this.count = count;
	}

	public Integer getDishId() {
		return dishId;
	}

	public void setDishId(Integer dishId) {
		this.dishId = dishId;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
