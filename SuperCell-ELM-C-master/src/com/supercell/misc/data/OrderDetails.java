
package com.supercell.misc.data;

import java.util.Date;
import java.util.List;

import com.supercell.misc.enums.OrderState;

public class OrderDetails {
	private Integer orderId;
	private Date dateOfOrder;
	private Integer total;
	private String shopName;
	private String shopPicPath;

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	private String username;
	private String address;
	private OrderState state;
	private String complaintMessage;
	private Integer merchantId;


	public String getComplaintMessage() {
		return complaintMessage;
	}

	public void setComplaintMessage(String complaintMessage) {
		this.complaintMessage = complaintMessage;
	}

	public OrderState getState() {
		return state;
	}
	public void setState(OrderState state) {
		this.state = state;
	}
	private List<OrderItemDetails> orderItemsList;
	
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
	public Date getDateOfOrder() {
		return dateOfOrder;
	}
	public void setDateOfOrder(Date dateOfOrder) {
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

