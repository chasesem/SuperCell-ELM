package com.supercell.elmm.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.supercell.elmm.dao.CustomerOrderDao;
import com.supercell.elmm.entity.CustomerOrder;
import com.supercell.elmm.entity.Dishes;
import com.supercell.elmm.misc.enums.OrderState;
import com.supercell.elmm.vo.OrderDetails;
import com.supercell.elmm.vo.OrderSummary;


@Repository("orderDao")
public class CustomerOrderDaoImpl extends GenericDaoImpl<CustomerOrder> implements CustomerOrderDao {
    @Override
    Class classOfT() {
        return CustomerOrder.class;
    }

	@Override
	public List<CustomerOrder> queryByMerchant(int merchantId) {
		// TODO Auto-generated method stub
		String jpql = "select c from CustomerOrder c where c.merchantId=:merchantId";
		Query query = createQuery(jpql);
		query.setParameter("merchantId", merchantId);
		List<CustomerOrder> customerOrders = query.getResultList();
		return customerOrders;
	}

	@Override
	public List<OrderSummary> queryByMerchantAndState(int merchantId, OrderState state) {
		// TODO Auto-generated method stub
		String jpql = "select new com.supercell.elmm.vo.OrderSummary(o,c.phoneNumber) "
				+ "from CustomerOrder o ,Customer c where o.userId=c.id and  o.merchantId=:merchantId and o.state=:state";
		Query query = createQuery(jpql);
		query.setParameter("merchantId", merchantId);
		query.setParameter("state", state);
		List<OrderSummary> orderSummary = query.getResultList();
		return orderSummary;
	}
	
//	public List<OrderSummary> queryRecevicedOrderByMerchant(int merchantId, OrderState state) {
//		// TODO Auto-generated method stub
//		String jpql = "select new com.supercell.elmm.vo.OrderSummary(o,c.phoneNumber) "
//				+ "from CustomerOrder o ,Customer c where o.userId=c.id and  o.merchantId=:merchantId and o.state=:state";
//		Query query = createQuery(jpql);
//		query.setParameter("merchantId", merchantId);
//		query.setParameter("state", state);
//		List<OrderSummary> orderSummary = query.getResultList();
//		return orderSummary;
//	}

	@Override
	public List<OrderSummary> queryOrderSummaryByMerchant(int merchantId) {
		String jpql = "select new com.supercell.elmm.vo.OrderSummary(o,c.phoneNumber) "
				+ "from CustomerOrder o ,Customer c where o.userId=c.id and o.merchantId=:merchantId";
		Query query = createQuery(jpql);
		query.setParameter("merchantId", merchantId);
		List<OrderSummary> orderSummary = query.getResultList();
		return orderSummary;
	}
	
	public OrderSummary queryOrderSummaryById(int id) {
		String jpql = "select new com.supercell.elmm.vo.OrderSummary(o,c.phoneNumber) "
				+ "from CustomerOrder o ,Customer c where o.userId=c.id and o.id=:id";
		Query query = createQuery(jpql);
		query.setParameter("id", id);
		List<OrderSummary> orderSummaries = query.getResultList();
		OrderSummary summary=null;
		if (!orderSummaries.isEmpty()) {
			summary = orderSummaries.get(0);
		}
		return summary;
	}

	
}
