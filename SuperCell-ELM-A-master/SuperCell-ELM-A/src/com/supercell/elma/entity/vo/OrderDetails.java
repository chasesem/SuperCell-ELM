package com.supercell.elma.entity.vo;

import java.util.Date;
import java.util.List;

public class OrderDetails {
	private Integer orderId;
	private String dateOfOrder;
	private Integer total;
	private String shopName;
	private String shopPicPath;
	private String username;
	private String address;
	private List<OrderItemDetails> orderItemsList;
	private String state;
	private String complaintMessage;
	private String merchantId;
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setComplaintMessage(String complaintMessage) {
		this.complaintMessage = complaintMessage;
	}
	public String getComplaintMessage() {
		return complaintMessage;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getDateOfOrder() {
		return dateOfOrder;
	}
	public void setDateOfOrder(String dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopPicPath() {
		return shopPicPath;
	}
	public void setShopPicPath(String shopPicPath) {
		this.shopPicPath = shopPicPath;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<OrderItemDetails> getOrderItemsList() {
		return orderItemsList;
	}
	public void setOrderItemsList(List<OrderItemDetails> orderItemsList) {
		this.orderItemsList = orderItemsList;
	}
	
}
