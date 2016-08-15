package com.supercell.elmm.dao;

import java.util.List;

import com.supercell.elmm.entity.CustomerOrder;
import com.supercell.elmm.misc.enums.OrderState;
import com.supercell.elmm.vo.OrderDetails;
import com.supercell.elmm.vo.OrderSummary;



/**
 * Created by WUJO2 on 8/6/2016.
 */
public interface CustomerOrderDao extends GenericDao<CustomerOrder> {
	List<CustomerOrder> queryByMerchant(int merchantId);
	List<OrderSummary> queryOrderSummaryByMerchant(int merchantId);
	public OrderSummary queryOrderSummaryById(int id);
	public List<OrderSummary> queryByMerchantAndState(int merchantId,OrderState state);
}
