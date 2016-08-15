package com.supercell.elmm.vo;

import com.supercell.elmm.entity.Customer;
import com.supercell.elmm.entity.CustomerOrder;

public class OrderSummary {
	private CustomerOrder order;
	private String customerPhoneNumber;
	public OrderSummary(CustomerOrder order, String customerPhoneNumber) {
		super();
		this.order = order;
		this.customerPhoneNumber = customerPhoneNumber;
	}
	public OrderSummary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerOrder getOrder() {
		return order;
	}
	public void setOrder(CustomerOrder order) {
		this.order = order;
	}
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}
	
	
}
