package com.supercell.elmm.vo;

import java.util.List;

import com.supercell.elmm.entity.Complaint;

public class ComplaintInfo {
	private Complaint complaint;
	private List<OrderItemDetails> itemDetails;
	private String orderOfDate;
	private Integer state;
	
	public ComplaintInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ComplaintInfo(Complaint complaint,
			List<OrderItemDetails> itemDetails, String orderOfDate) {
		super();
		this.complaint = complaint;
		this.itemDetails = itemDetails;
		this.orderOfDate = orderOfDate;
	}
	public Complaint getComplaint() {
		return complaint;
	}
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
	
	public List<OrderItemDetails> getItemDetails() {
		return itemDetails;
	}
	public void setItemDetails(List<OrderItemDetails> itemDetails) {
		this.itemDetails = itemDetails;
	}
	public String getOrderOfDate() {
		return orderOfDate;
	}
	public void setOrderOfDate(String orderOfDate) {
		this.orderOfDate = orderOfDate;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
