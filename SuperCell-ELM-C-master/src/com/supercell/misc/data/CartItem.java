package com.supercell.misc.data;

/**
 * Created by WUJO2 on 8/6/2016.
 */
public class CartItem {
	private Integer dishesId;
	private Integer count;

	public CartItem() {
	}

	public CartItem(Integer dishesId, Integer count) {
		this.dishesId = dishesId;
		this.count = count;
	}

	public Integer getDishesId() {
		return dishesId;
	}

	public void setDishesId(Integer dishesId) {
		this.dishesId = dishesId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
