package com.supercell.elmm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.client.Client;

import oracle.net.aso.s;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.supercell.elmm.global.Global;
import com.supercell.elmm.service.ComplaintService;
import com.supercell.elmm.service.MerchantService;
import com.supercell.elmm.util.JSONUtil;
import com.supercell.elmm.util.WebServiceUtil;
import com.supercell.elmm.vo.ComplaintInfo;
import com.supercell.elmm.vo.ComplaintState;

@Controller
@RequestMapping("/complaint")
public class ComplaintController {
	private ComplaintService service;
	@Resource(name="complaintService")
	public void setService(ComplaintService service) {
		this.service = service;
	}
	private MerchantService merchantService;
	@Resource(name="merchantService")
	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}
	
	private Client client;
	@Resource(name="jerseyClient")
	public void setClient(Client client) {
		this.client = client;
	}
	@Value("${ComplaintStateWebservice}")
	private String complaintStateDestination;
	
	@Value("${COMPLIANT_PAGE}")
	private String complaintPage;

	@RequestMapping("/getComplaintInfos/{phoneNumber}")
	public ModelAndView getComplaintInfosByMerchantId(@PathVariable String phoneNumber){
		int merchantId = merchantService.findMerchantIDByPhoneNumber(phoneNumber);
		List<ComplaintInfo> complaintInfos = service.getComplaintInfosByMerchantId(merchantId);
		for(int i = 0;i < complaintInfos.size();i++){
//			complaintStateDestination = complaintStateDestination+complaintInfos.get(i).getComplaint().getId();
			String destination = complaintStateDestination+complaintInfos.get(i).getComplaint().getOrderId();
			System.out.println(destination);
			String value = (String) WebServiceUtil.getValue(client, destination, String.class);
			ComplaintState state = (ComplaintState) JSONUtil.jsonToObject(value, ComplaintState.class);
			if (state==null) {
				complaintInfos.get(i).setState(1);
			}else {
				complaintInfos.get(i).setState(state.getComplainState());
			}
		}
		return new ModelAndView(complaintPage,"complaintInfos",complaintInfos);
	}
}
