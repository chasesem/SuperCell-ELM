package com.supercell.elmm.vo;

public class ComplaintState {
	private Integer orderId;
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	private Integer customerId;
	private String phoneNumber;
	private Integer merchantId;
	private String merchantName;
	private String complainMessage;
	private Integer complainState;
	public Integer getComplainState() {
		return complainState;
	}

	public void setComplainState(Integer complainState) {
		this.complainState = complainState;
	}

	public ComplaintState(){
		
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerID) {
		this.customerId = customerID;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getComplainMessage() {
		return complainMessage;
	}
	public void setComplainMessage(String complainMessage) {
		this.complainMessage = complainMessage;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderID(Integer orderID) {
		this.orderId = orderID;
	}
}
