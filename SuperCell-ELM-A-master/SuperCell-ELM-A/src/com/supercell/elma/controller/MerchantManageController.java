package com.supercell.elma.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
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


@Controller
@RequestMapping("/merchantmanage")
public class MerchantManageController {
	
	MerchantManageService service;
	@Resource(name="merchantManageService")
	public void setService(MerchantManageService service) {
		this.service = service;
	}

	@RequestMapping(value="/getinfo.do",method = RequestMethod.GET)
	public ModelAndView getMerchantInfo(String id ,int state) {
		Merchant merchant = service.getMerchantInfo(id);
		merchant.setMerchantState(state);
		//return new ModelAndView("merchantApproval","merchantList",list);
		return new ModelAndView("merchantDetail","merchant",merchant);
	}
	
	@RequestMapping("/changestates.do")
	public ModelAndView changeStates(Integer merchantID , Integer state){
		MerchantState merchantState = new MerchantState();
		merchantState.setMerchantId(merchantID);
		merchantState.setMerchantState(state);
		
		service.changeStates(merchantState);
		
		List<MerchantState> list = service.getRating();
		List<MerchantStateVO> vList= new ArrayList<>();
		for(int i = 0 ; i < list.size() ; i++){
			MerchantStateVO msv = new MerchantStateVO();
			msv.setMerchantId(list.get(i).getMerchantId()+"");
			msv.setMerchantStateText(list.get(i).getMerchantState());
			msv.setMerchantState(list.get(i).getMerchantState()+"");
			vList.add(msv);
		}
		return new ModelAndView("redirect:/merchantmanage/getmerchantstate.do");
	}

	
	@RequestMapping("/getcomplain.do")
	public ModelAndView getComplain(){
		List<Complaint> list = service.getAllComplaint();
		return new ModelAndView("complaint","conplaintList",list);
	}
	
	@RequestMapping("/getLowRating.do")
	public ModelAndView getLowRating(){
		List<MerchantState>list = service.getLowRating();
		return new ModelAndView("merchantRating","list",list);
	}
	@RequestMapping("/getmerchantstate.do")
	public ModelAndView getMerchantState(){
		List<MerchantState> list = service.getRating();
		List<MerchantStateVO> vList= new ArrayList<>();
		for(int i = 0 ; i < list.size() ; i++){
			MerchantStateVO msv = new MerchantStateVO();
			msv.setMerchantId(list.get(i).getMerchantId()+"");
			msv.setMerchantStateText(list.get(i).getMerchantState());
			msv.setMerchantState(list.get(i).getMerchantState()+"");
			vList.add(msv);
		}
		return new ModelAndView("merchantstate","list",vList);
	}
	
	@RequestMapping("/getOrderItem.do/{id}")
	public ModelAndView getOrderItem(@PathVariable String id){
		OrderDetailComplaint orderDetailComplaint = service.getOrderDetails(id);
		return new ModelAndView("ComplaintDetails","orderDetailComplaint",orderDetailComplaint);
	}
	@RequestMapping("/getNormalMerchant.do")
	@ResponseBody
	public String getNormalMerchant(){
		List<MerchantIDVO> midvo = service.getMerchantID();
		String json = "";
		try {
			json = JSONUtil.objetc2Json(midvo);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping(value="/getSimpleMerchantState.do/{id}",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getSimpleMerchantState(@PathVariable String id){
		MerchantStateVO msvo = service.getMerchantState(id);
		String json = "";
		try {
			json = JSONUtil.objetc2Json(msvo);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping(value="/getComplaintState.do/{id}",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getComplaintState(@PathVariable String id){
		Complaint complaint = service.getComplaintState(id);
		String json = "";
		try {
			json = JSONUtil.objetc2Json(complaint);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping("/changeComplaintState.do")
	public ModelAndView changeComplaintState(Integer id , Integer state , Integer merchantId){
		service.changeComplaintState(id, state,merchantId);
		return new ModelAndView("redirect:/merchantmanage/getcomplain.do");
	}
}
