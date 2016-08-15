package com.supercell.elmm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.supercell.elmm.dao.ComplaintDao;
import com.supercell.elmm.dao.CustomerOrderDao;
import com.supercell.elmm.dao.OrderItemDao;
import com.supercell.elmm.entity.Complaint;
import com.supercell.elmm.entity.CustomerOrder;
import com.supercell.elmm.service.ComplaintService;
import com.supercell.elmm.util.DateFormatUtil;
import com.supercell.elmm.vo.ComplaintInfo;
import com.supercell.elmm.vo.OrderItemDetails;

@Service("complaintService")
public class ComplaintServiceImpl implements ComplaintService{
	private ComplaintDao dao;
	@Resource(name="complaintDao")
	public void setDao(ComplaintDao dao) {
		this.dao = dao;
	}
	private CustomerOrderDao orderDao;
	@Resource(name="orderDao")
	public void setDao(CustomerOrderDao orderDao){
		this.orderDao = orderDao;
	}
	private OrderItemDao orderItemDao;
	@Resource(name="orderItemDao")
	public void setOrderItemDao(OrderItemDao orderItemDao) {
		this.orderItemDao = orderItemDao;
	}


	@Override
	public List<ComplaintInfo> getComplaintInfosByMerchantId(int merchantId) {
		// TODO Auto-generated method stub
		List<ComplaintInfo> complaintInfos = new ArrayList<>();
		List<Complaint> complaints = dao.getComplaintsByMerchant(merchantId);
		for(Complaint complaint:complaints){
			CustomerOrder customerOrder = orderDao.get(complaint.getOrderId());
			List<OrderItemDetails> itemDetails = orderItemDao.queryByOrderId(complaint.getOrderId());
			String dateOfOrder = DateFormatUtil.dateToString(customerOrder.getDateOfOrder(), "yyyy-MM-dd HH:mm:ss");
			ComplaintInfo complaintInfo = new ComplaintInfo(complaint, itemDetails, dateOfOrder);
			complaintInfos.add(complaintInfo);
		}
		
		return complaintInfos;
	}

}
