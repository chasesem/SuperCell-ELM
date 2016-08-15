package com.supercell.elma.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Complaint")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Complaint implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@Column(name="orderId")
	private Integer orderId;
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Column(name="customerId")
	private Integer customerId;
	@Column(name="phoneNumber")
	private String phoneNumber;
	@Column(name="merchantId")
	private Integer merchantId;
	@Column(name="merchantName")
	private String merchantName;
	@Column(name="complainMessage")
	private String complainMessage;
	@Column(name="complainState")
	private Integer complainState;
	public Integer getComplainState() {
		return complainState;
	}

	public void setComplainState(Integer complainState) {
		this.complainState = complainState;
	}

	public Complaint(){
		
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
