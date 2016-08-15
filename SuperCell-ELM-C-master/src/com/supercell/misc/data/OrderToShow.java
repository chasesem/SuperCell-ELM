
package com.supercell.misc.data;

import java.util.Date;

import com.supercell.misc.enums.OrderState;

public class OrderToShow {

	private Integer orderId;
	private Date dateOfOrder;
	private Integer total;
	private String shopName;
	private String shopPicPath;
	private OrderState state;
	private Integer complaintState;

	public Integer getComplaintState() {
		return complaintState;
	}

	public void setComplaintState(Integer complaintState) {
		this.complaintState = complaintState;
	}

	public OrderState getState() {
		return state;
	}
	public void setState(OrderState state) {
		this.state = state;
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
	
}
