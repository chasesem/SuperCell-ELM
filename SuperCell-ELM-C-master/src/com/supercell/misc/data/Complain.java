package com.supercell.misc.data;

public class Complain {

	private Integer customerId;
	private String phoneNumber;
	private Integer merchantId;
	private String merchantName;
	private Integer orderId;
	private String complainMessage;
	private Integer complainState;

	public Integer getComplainState() {
		return complainState;
	}

	public void setComplainState(Integer complainState) {
		this.complainState = complainState;
	}

	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public String getComplainMessage() {
		return complainMessage;
	}
	public void setComplainMessage(String complainMessage) {
		this.complainMessage = complainMessage;
	}
	
}
