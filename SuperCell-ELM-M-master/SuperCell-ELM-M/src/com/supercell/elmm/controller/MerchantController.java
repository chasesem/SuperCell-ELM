package com.supercell.elmm.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.supercell.elmm.entity.Merchant;
import com.supercell.elmm.global.Global;
import com.supercell.elmm.service.MerchantService;
import com.supercell.elmm.service.ProducerService;
import com.supercell.elmm.util.FileUploadUtil;
import com.supercell.elmm.util.JSONUtil;
import com.supercell.elmm.util.PicShowUtil;
import com.supercell.elmm.util.WebServiceUtil;
import com.supercell.elmm.validator.MerchantValidator;
import com.supercell.elmm.vo.MerchantState;

@Controller
@RequestMapping("/merchant")
public class MerchantController {
	private MerchantService service;
	@Resource(name="merchantService")
	public void setService(MerchantService service) {
		this.service = service;
	}
	
	private ProducerService producerService;
	@Resource(name="producerService")
	public void setProducerService(ProducerService producerService) {
		this.producerService = producerService;
	}
	
	private Destination destination;
	@Resource(name="queueDestination")
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	private Client client;
	@Resource(name="jerseyClient")
	public void setClient(Client client) {
		this.client = client;
	}
	@Value("${MerchantStateWebservice}")
	private String merchantStateDestination;
	@Value("${FOWARD_WAY}")
	private String forwardWay;
	@Value("${REDIRECT_WAY}")
	private String redirectWay;
	@Value("${LOGIN_PAGE}")
	private String loginPage;
	@Value("${LOGIN_PAGE_WHOLE_NAME}")
	private String loginPageWholeName;
	@Value("${REGISTER_PAGE}")
	private String registerPage;
	@Value("${REGISTER_PAGE_WHOLE_NAME}")
	private String registerPageWholeName;
	@Value("${NOT_LOGIN_PAGE}")
	private String notLoginPage;
	@Value("${HOME_PAGE}")
	private String homePage;
	@Value("${MERCHANT_INFO_MANAGE_PAGE}")
	private String merchantInfoManagePage;
	@Value("${MERCHANT_INFO_UPDATE_PAGE}")
	private String merchantInfoUpdatePage;
	@Value("${IMG_SAVE_PATH}")
	private String imgSavePath;
	
	@RequestMapping(value="/login.do")
	public ModelAndView login(String phoneNumber,String password,HttpSession session){
		boolean result = service.login(phoneNumber, password);
		if (result) {
			int merchantId = service.findMerchantIDByPhoneNumber(phoneNumber);
			String destination = merchantStateDestination+merchantId;
			String value = (String) WebServiceUtil.getValue(client, destination, String.class);
			MerchantState state = (MerchantState) JSONUtil.jsonToObject(value, MerchantState.class);
			System.out.println("在这里。。。。。。。。。"+phoneNumber);
			if (state.getMerchantState()==MerchantState.PASS_AUDIT_MERCHANT_STATE) {
				session.setAttribute(Global.CURRENT_MERCHANT, phoneNumber);
				System.out.println("要开始登陆啦。。。。。。。。。"+phoneNumber);
				return new ModelAndView(redirectWay+homePage);
			}else {
				String loginFailTips = Global.stateMap.get(state.getMerchantState()).getDescription();
				return new ModelAndView(forwardWay+notLoginPage,"loginFailTips",loginFailTips);
			}
		}else {
//			System.out.println(forwardWay+Global.LOGIN_PAGE);
			String tips="登录失败，用户名或密码错误，请重新输入....";
			return new ModelAndView(loginPage,Global.LOGIN_FAIL_TIP,tips);
		}
	}
	
	@RequestMapping(value="/register.do")
	public ModelAndView register(List<MultipartFile> files,Merchant merchant){
		Set<String> errorTipsList=MerchantValidator.validate(merchant);
		if (files.size()<3) {
			errorTipsList.add("sorry，以上所有选项都不能为空");
		}
		if (errorTipsList.size()>0) {
			return new ModelAndView(registerPage,Global.REGISTER_FAIL_TIP,errorTipsList);
		}else{
			String registerTipString="";
			if (service.findMerchantByPhoneNumber(merchant.getPhoneNumber())==null) {
				List<String> fileName = FileUploadUtil.upload(imgSavePath, files);
				if (!fileName.isEmpty()) {
					merchant.setShopPicPath(fileName.get(0));
					merchant.setLicensePicPath(fileName.get(1));
					merchant.setIdCardPicPath(fileName.get(2));
				}
				boolean result = service.register(merchant);
				if (result) {
					MerchantState merchantState = new MerchantState(merchant.getId(),MerchantState.AUDIT_MERCHANT_STATE);
					String message = JSONUtil.convertToJSON(merchantState);
					producerService.sendMessage(destination, message);
					return new ModelAndView(redirectWay+loginPageWholeName);
				}else {
					registerTipString="Sorry,注册失败...";
				}
			}else {
				registerTipString="Sorry,该手机号码已被注册,请重新输入...";
			}
			return new ModelAndView(registerPage,Global.REGISTER_FAIL_TIP,registerTipString);
		}
	}
	
	@RequestMapping("/isExistPhoneNumber.do/{phoneNumber}")
	@ResponseBody
	public String isExistPhoneNumber(@PathVariable String phoneNumber){
		String tip="";
		if (phoneNumber.length()!=11) {
			tip="手机号码长度应为11位";
		}else if (service.findMerchantByPhoneNumber(phoneNumber)!=null) {
			tip="Sorry,该手机号码已被注册";
		}else {
			tip="恭喜你,该手机号码可以使用";
		}
		return tip;
	}
	
	@RequestMapping("/getPic.do")
	public void getPic(OutputStream os,String imgPath) throws IOException{
		PicShowUtil.getPic(os, imgSavePath+"\\"+imgPath);
	}
	
	@RequestMapping("/updateMerchant.do")
	public ModelAndView updateMerchantInfo(List<MultipartFile> files,Merchant merchant,HttpServletRequest request){
		List<String> errorTipsList=MerchantValidator.validateNot_Null(merchant.getPhoneNumber());
		errorTipsList.addAll(MerchantValidator.validateNot_Null(merchant.getAddress()));
		errorTipsList.addAll(MerchantValidator.validateNot_Null(merchant.getShopName()));
		if (errorTipsList.size()>0) {
			request.setAttribute("tips", "sorry,更新失败.....");
			return getUpdateMerchantInfo(merchant.getPhoneNumber());
		}else {
			
			if (files.size()>0) {
				List<String> fileName = FileUploadUtil.upload(imgSavePath, files);
				if (fileName.size()>0) {
					merchant.setShopPicPath(fileName.get(0));
				}
			}
			merchant.setId(service.findMerchantIDByPhoneNumber(merchant.getPhoneNumber()));
			boolean result = service.updateMerchant(merchant);
			if (result) {
				request.setAttribute("tips", "恭喜你，更新成功...");
				return getUpdateMerchantInfo(merchant.getPhoneNumber());
			}else {
				request.setAttribute("tips", "sorry， 更新失败.....");
				return getUpdateMerchantInfo(merchant.getPhoneNumber());
			}
			
		}
	}
	
	@RequestMapping("/updatePassword.do")
	@ResponseBody
	public boolean updatePassword(Merchant merchant){
		List<String> error = MerchantValidator.validateNot_Null(merchant.getPassword());
		error.addAll(MerchantValidator.validateNot_Null(merchant.getPhoneNumber()));
		if (error.size()>0) {
			return false;
		}
		merchant.setId(service.findMerchantIDByPhoneNumber(merchant.getPhoneNumber()));
		boolean result = service.updatePassword(merchant);
		return result;
	}
	
	@RequestMapping("/checkPassword.do")
	@ResponseBody
	public boolean checkPassword(String phoneNumber,String oldPassword){
		boolean result = service.login(phoneNumber, oldPassword);
		return result;
	}
	
	@RequestMapping("/getMerchantInfo.do/{id}")
	@ResponseBody
	public Merchant getMerchantInfo(@PathVariable int id){
		Merchant merchant=service.findMerchantByID(id);
		return merchant;
	}
	
	@RequestMapping("/getHomePageMerchant.do/{phoneNumber}")
	@ResponseBody
	public Merchant getHomePageMerchant(@PathVariable String phoneNumber){
		Merchant merchant=service.findMerchantByPhoneNumber(phoneNumber);
		return merchant;
	}
	
	@RequestMapping("/getMerchantInfoByPhoneNumber.do")
	public ModelAndView getMerchantInfoByPhoneNumber(String phoneNumber){
		Merchant merchant=service.findMerchantByPhoneNumber(phoneNumber);
		return new ModelAndView(merchantInfoManagePage,"merchantInfo",merchant);
	}
	
	@RequestMapping("/getUpdateMerchantInfo.do")
	public ModelAndView getUpdateMerchantInfo(String phoneNumber){
		Merchant merchant=service.findMerchantByPhoneNumber(phoneNumber);
		return new ModelAndView(merchantInfoUpdatePage,"merchantInfo",merchant);
	}
	
	@RequestMapping("/logOff.do")
	public String logOff(HttpSession session){
		session.removeAttribute(Global.CURRENT_MERCHANT);
		session.invalidate();
		return redirectWay+loginPageWholeName;
	}
}
