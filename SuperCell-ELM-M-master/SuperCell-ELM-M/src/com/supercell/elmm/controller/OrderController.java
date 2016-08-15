package com.supercell.elmm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.supercell.elmm.entity.CustomerOrder;
import com.supercell.elmm.global.Global;
import com.supercell.elmm.misc.enums.OrderState;
import com.supercell.elmm.service.MerchantService;
import com.supercell.elmm.service.OrderService;
import com.supercell.elmm.vo.OrderDetails;

@Controller
@RequestMapping("/orderController")
public class OrderController {
	private OrderService service;
	@Resource(name="orderService")
	public void setService(OrderService service) {
		this.service = service;
	}
	@Value("${ORDER_ITEM_PAGE}")
	private String orderItemPage;
	
	private MerchantService merchantService;
	@Resource(name="merchantService")
	public void setMerchantService(MerchantService merchantService){
		this.merchantService = merchantService;
	}
	
	@RequestMapping("/queryAllOrderByMerchant")
	@ResponseBody
	public List<OrderDetails> queryAllOrderByMerchant(String phoneNumber){
		int merchantId = merchantService.findMerchantIDByPhoneNumber(phoneNumber);
		List<OrderDetails> orderDetails = service.queryAllOrderByMerchant(merchantId);
		return orderDetails;
	}
	
	@RequestMapping("/findOrderDetailsById")
	public ModelAndView findOrderDetailsById(int id,HttpServletRequest request){
		OrderDetails orderDetail = service.findOrderDetailsById(id);
		System.out.println(id+"  orderDetail" + orderDetail.getOrderSummary().getCustomerPhoneNumber());
		request.setAttribute("merchantNotAck", OrderState.MERCHANT_NOT_ACKNOWLEDGED);
		return new ModelAndView(orderItemPage,"orderDetail",orderDetail);
	}
	
	@RequestMapping("/receiveOrder")
	@ResponseBody
	public boolean receiveOrder(CustomerOrder order){
		System.out.println("............"+order.getId());
		boolean result = service.receiveOrder(order);
		return result;
	}
	
	@RequestMapping("/refuseOrder")
	@ResponseBody
	public boolean refuseOrder(CustomerOrder order){
		boolean result = service.refuseOrder(order);
		return result;
	}
	
	@RequestMapping("/queryRefusedOrderByMerchant/{phoneNumber}")
	@ResponseBody
	public List<OrderDetails> queryRefusedOrderByMerchant(@PathVariable String phoneNumber){
		int merchantId = merchantService.findMerchantIDByPhoneNumber(phoneNumber);
		List<OrderDetails> orderDetails = service.queryRefusedOrderByMerchant(merchantId);
		return orderDetails;
	}
	
	@RequestMapping("/queryRecevicedOrderByMerchant/{phoneNumber}")
	@ResponseBody
	public List<OrderDetails> queryRecevicedOrderByMerchant(@PathVariable String phoneNumber){
		int merchantId = merchantService.findMerchantIDByPhoneNumber(phoneNumber);
		List<OrderDetails> orderDetails = service.queryRecevicedOrderByMerchant(merchantId);
		return orderDetails;
	}
	
	@RequestMapping("/queryNewOrderByMerchant/{phoneNumber}")
	@ResponseBody
	public List<OrderDetails> queryNewOrderByMerchant(@PathVariable String phoneNumber){
		int merchantId = merchantService.findMerchantIDByPhoneNumber(phoneNumber);
		List<OrderDetails> orderDetails = service.queryNewOrderByMerchant(merchantId);
		return orderDetails;
	}
}
