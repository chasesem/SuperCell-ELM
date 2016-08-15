package com.supercell.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;

import com.supercell.dao.OrderItemDao;
import com.supercell.entity.OrderItem;
import com.supercell.service.OrderItemService;

import static org.hibernate.criterion.Restrictions.eq;

public class OrderItemServiceImpl implements OrderItemService{

	@Resource
	private OrderItemDao orderItemDao;
	
	@Override
	public List<OrderItem> viewOrderItems(Integer orderId) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrderItem.class);
		detachedCriteria.add(eq(OrderItem.ORDER_ID,orderId));
		List<OrderItem> orderItemsList = orderItemDao.search(detachedCriteria);
		return orderItemsList;
	}

}
