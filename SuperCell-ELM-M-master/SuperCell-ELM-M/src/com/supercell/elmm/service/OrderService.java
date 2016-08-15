package com.supercell.elmm.service;

import java.util.List;

import com.supercell.elmm.entity.CustomerOrder;
import com.supercell.elmm.vo.OrderDetails;
import com.supercell.elmm.vo.OrderItemDetails;

public interface OrderService {
	public List<OrderDetails> queryAllOrderByMerchant(int merchantId);
	public OrderDetails findOrderDetailsById(int id);
	public boolean receiveOrder(CustomerOrder order);
	public boolean refuseOrder(CustomerOrder order);
	public CustomerOrder findOrderById(int id);
	public List<OrderDetails> queryRefusedOrderByMerchant(int merchantId);
	public List<OrderDetails> queryRecevicedOrderByMerchant(int merchantId);
	public List<OrderDetails> queryNewOrderByMerchant(int merchantId);
}
