package com.supercell.elmm.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.supercell.elmm.dao.CustomerOrderDao;
import com.supercell.elmm.dao.MerchantDao;
import com.supercell.elmm.dao.OrderItemDao;
import com.supercell.elmm.entity.CustomerOrder;
import com.supercell.elmm.misc.enums.OrderState;
import com.supercell.elmm.service.OrderService;
import com.supercell.elmm.vo.OrderDetails;
import com.supercell.elmm.vo.OrderItemDetails;
import com.supercell.elmm.vo.OrderSummary;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
	private CustomerOrderDao dao;
	@Resource(name="orderDao")
	public void setDao(CustomerOrderDao dao){
		this.dao = dao;
	}
	
	private OrderItemDao itemDao;
	@Resource(name="orderItemDao")
	public void setItemDao(OrderItemDao itemDao){
		this.itemDao = itemDao;
	}

	@Override
	public List<OrderDetails> queryAllOrderByMerchant(int merchantId) {
		// TODO Auto-generated method stub
		List<OrderDetails> details = new ArrayList<>();
//		List<CustomerOrder> orders = dao.queryByMerchant(merchantId);
		List<OrderSummary> orders = dao.queryOrderSummaryByMerchant(merchantId);
		for (OrderSummary order:orders) {
//			int price = order.getOrder().getTotal();
//			order.getOrder().setTotal(price/100);
			List<OrderItemDetails> itemDetails = itemDao.queryByOrderId(order.getOrder().getId());
			OrderDetails orderDetail = new OrderDetails(order,itemDetails);
			details.add(orderDetail);
		}
		return details;
	}

	@Override
	public OrderDetails findOrderDetailsById(int id) {
//		CustomerOrder order = dao.get(id);
		OrderSummary order = dao.queryOrderSummaryById(id);
		List<OrderItemDetails> itemDetails = itemDao.queryByOrderId(id);
		OrderDetails details = new OrderDetails(order, itemDetails);
		return details;
	}
	
	@Override
	@Transactional
	public boolean receiveOrder(CustomerOrder order) {
		// TODO Auto-generated method stub
		CustomerOrder targetOrder = dao.get(order.getId());
		targetOrder.setState(OrderState.MERCHANT_ACKNOWLEDGED);
		boolean result = dao.update(targetOrder);
		return result;
	}
	
	@Override
	@Transactional
	public boolean refuseOrder(CustomerOrder order) {
		// TODO Auto-generated method stub
		CustomerOrder targetOrder = dao.get(order.getId());
		targetOrder.setState(OrderState.MERCHANT_REFUSED);
		boolean result = dao.update(targetOrder);
		return result;
	}

	@Override
	public CustomerOrder findOrderById(int id) {
		CustomerOrder order = dao.get(id);
		return order;
	}

	@Override
	public List<OrderDetails> queryRefusedOrderByMerchant(int merchantId) {
		// TODO Auto-generated method stub
		List<OrderDetails> details = new ArrayList<>();
		List<OrderSummary> orders = dao.queryByMerchantAndState(merchantId,OrderState.MERCHANT_REFUSED);
		for (OrderSummary order:orders) {
			List<OrderItemDetails> itemDetails = itemDao.queryByOrderId(order.getOrder().getId());
			OrderDetails orderDetail = new OrderDetails(order,itemDetails);
			details.add(orderDetail);
		}
		return details;
	}

	@Override
	public List<OrderDetails> queryRecevicedOrderByMerchant(int merchantId) {
		List<OrderDetails> details = new ArrayList<>();
		List<OrderSummary> orders = dao.queryByMerchantAndState(merchantId,OrderState.MERCHANT_ACKNOWLEDGED);
		orders.addAll(dao.queryByMerchantAndState(merchantId,OrderState.CUSTOMER_ACKNOWLEDGED));
		for (OrderSummary order:orders) {
			List<OrderItemDetails> itemDetails = itemDao.queryByOrderId(order.getOrder().getId());
			OrderDetails orderDetail = new OrderDetails(order,itemDetails);
			details.add(orderDetail);
		}
		return details;
	}

	@Override
	public List<OrderDetails> queryNewOrderByMerchant(int merchantId) {
		List<OrderDetails> details = new ArrayList<>();
		List<OrderSummary> orders = dao.queryByMerchantAndState(merchantId,OrderState.MERCHANT_NOT_ACKNOWLEDGED);
		for (OrderSummary order:orders) {
			List<OrderItemDetails> itemDetails = itemDao.queryByOrderId(order.getOrder().getId());
			OrderDetails orderDetail = new OrderDetails(order,itemDetails);
			details.add(orderDetail);
		}
		return details;
	}
	
}
