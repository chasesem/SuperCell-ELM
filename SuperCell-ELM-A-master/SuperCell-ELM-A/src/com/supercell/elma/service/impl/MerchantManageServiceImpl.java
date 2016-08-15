package com.supercell.elma.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.supercell.elma.dao.ComplaintDao;
import com.supercell.elma.dao.MerchantStateDao;
import com.supercell.elma.entity.Complaint;
import com.supercell.elma.entity.Merchant;
import com.supercell.elma.entity.MerchantState;
import com.supercell.elma.entity.vo.MerchantIDVO;
import com.supercell.elma.entity.vo.MerchantRating;
import com.supercell.elma.entity.vo.MerchantStateVO;
import com.supercell.elma.entity.vo.OrderDetailComplaint;
import com.supercell.elma.entity.vo.OrderDetails;
import com.supercell.elma.service.MerchantManageService;
import com.supercell.elma.util.JSONUtil;
@Service("merchantManageService")
public class MerchantManageServiceImpl implements MerchantManageService{
	MerchantStateDao dao;
	ComplaintDao complaintDao;
	Client client;
	@Value("${GET_MERCHANTINFOURL}")
	private String getMerchantInfoURL;
	@Value("${GET_ORDERDETAILURL}")
	private String getOrderDetailURL;
	
	@Resource(name="jerseyClient")
	public void setClient(Client client) {
		this.client = client;
	}
	
	@Resource(name="complaintDao")
	public void setComplaintDao(ComplaintDao complaintDao){
		this.complaintDao = complaintDao;
	}
	@Resource(name="merchantStateDao")
	public void setDao(MerchantStateDao dao) {
		this.dao = dao;
	}

	public Merchant getMerchantInfo(String id) {
		String resourceDestnation = "http://10.222.232.34:8080/SuperCell-ELM-M/merchant/getMerchantInfo.do/"+id;
		WebTarget target = client.target(resourceDestnation);
		Response response = target.request().get();
		String value = response.readEntity(String.class);
		String json = value.replace("[", "");
		json = json.replace("]", "");
		Merchant merchant = null;
		try {
			merchant = (Merchant) JSONUtil.json2Object(value, Merchant.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return merchant;
	}
	@Transactional
	public boolean changeStates(MerchantState merchantState) {
		return dao.updateState(merchantState);
	}

	public List<MerchantState> getRating() {
		return dao.getAll();
	}

	public List<Complaint> getAllComplaint() {
		return complaintDao.find();
	}
	public Complaint getComplaint(Complaint complaint){
		return complaintDao.find(complaint);
	}

	@Override
	public List<MerchantState> getLowRating() {
		List<MerchantState> list = dao.getLowRating();
		return list;
	}

	@Override
	public OrderDetailComplaint getOrderDetails(String id) {
		String resourceDestnation = "http://10.222.232.36:8080/SuperCell-ELM-C/customerOrder/complainOrderDetails.do/"+id;
		WebTarget target = client.target(resourceDestnation);
		Response response = target.request().get();
		String value = response.readEntity(String.class);
		String json = value.substring(1);
		json = json.substring(0, json.length()-1);
		OrderDetails orderDetails = null;
		try {
			orderDetails = (OrderDetails) JSONUtil.json2Object(json, OrderDetails.class);
			orderDetails.setTotal(orderDetails.getTotal()/100);
			for(int i = 0 ; i < orderDetails.getOrderItemsList().size() ; i++){
				orderDetails.getOrderItemsList().get(i).setPrice(orderDetails.getOrderItemsList().get(i).getPrice()/100);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		OrderDetailComplaint orderDetailComplaint = new OrderDetailComplaint();
		orderDetailComplaint.setOrderDetails(orderDetails);
		Complaint complaint = new Complaint();
		complaint.setOrderId(Integer.parseInt(id));
		orderDetailComplaint.setComplaint(complaintDao.find(complaint).getComplainMessage());
		return orderDetailComplaint;
	}
	
	
	
	public List<MerchantIDVO> getMerchantID(){
		List<MerchantIDVO> vList = new ArrayList<MerchantIDVO>();
		List<MerchantState> list = dao.getNormalMerchant();
		for (int i = 0; i < list.size(); i++) {
			MerchantIDVO midvo = new MerchantIDVO();
			midvo.setMerchantId(list.get(i).getMerchantId()+"");
			vList.add(midvo);
		}
		return vList;
	}

	@Override
	public MerchantStateVO getMerchantState(String id) {
		MerchantState merchantState = dao.getMerchantState(id);
		MerchantStateVO merchantStateVO = new MerchantStateVO();
		merchantStateVO.setMerchantId(merchantState.getMerchantId()+"");
		merchantStateVO.setMerchantState(merchantState.getMerchantState()+"");
		merchantStateVO.setMerchantStateText(merchantState.getMerchantState());
		return merchantStateVO;
	}

	@Override
	public Complaint getComplaintState(String id) {
		Complaint complaint = complaintDao.getComplaintState(id);
		return complaint;
	}
	
	public void changeComplaintState(Integer id , Integer state , Integer merchantId){
		if(state==4){
			MerchantState merchantState = new MerchantState();
			merchantState.setMerchantId(merchantId);
			merchantState.setMerchantState(state);
			dao.updateState(merchantState);
		}
		complaintDao.changeState(id, state);
	}
}
