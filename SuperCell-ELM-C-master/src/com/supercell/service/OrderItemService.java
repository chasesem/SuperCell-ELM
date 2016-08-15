package com.supercell.service;

import java.util.List;

import com.supercell.entity.OrderItem;

public interface OrderItemService {

	List<OrderItem> viewOrderItems(Integer orderId);
	
}
