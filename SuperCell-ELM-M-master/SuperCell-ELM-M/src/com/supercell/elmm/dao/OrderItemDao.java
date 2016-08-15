package com.supercell.elmm.dao;

import java.util.List;

import com.supercell.elmm.entity.OrderItem;
import com.supercell.elmm.vo.OrderItemDetails;

public interface OrderItemDao extends GenericDao<OrderItem> {
	public List<OrderItemDetails> queryByOrderId(int orderId);
}
