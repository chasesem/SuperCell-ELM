package com.supercell.elma.entity.vo;

public class OrderDetailComplaint {
	private OrderDetails orderDetails;
	private String complaint;
	public OrderDetails getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}
	public String getComplaint() {
		return complaint;
	}
	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}
	public OrderDetailComplaint() {
		
	}
}
