package com.supercell.elmm.vo;

import java.util.List;

import com.supercell.elmm.entity.Customer;
import com.supercell.elmm.entity.CustomerOrder;

public class OrderDetails {
	private OrderSummary orderSummary;
	private List<OrderItemDetails> itemDetails;
	
	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public OrderDetails(OrderSummary orderSummary,
			List<OrderItemDetails> itemDetails) {
		super();
		this.orderSummary = orderSummary;
		this.itemDetails = itemDetails;
	}


	public OrderSummary getOrderSummary() {
		return orderSummary;
	}


	public void setOrderSummary(OrderSummary orderSummary) {
		this.orderSummary = orderSummary;
	}


	public List<OrderItemDetails> getItemDetails() {
		return itemDetails;
	}
	public void setItemDetails(List<OrderItemDetails> itemDetails) {
		this.itemDetails = itemDetails;
	}
	
	
}
