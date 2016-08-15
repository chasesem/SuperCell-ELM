package com.supercell.controller;

import com.supercell.entity.Customer;
import com.supercell.entity.CustomerOrder;
import com.supercell.entity.OrderItem;
import com.supercell.misc.JSONUtil;
import com.supercell.misc.data.*;
import com.supercell.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "customerOrder")
public class CustomerOrderController {
	@Value("${Customer}")
	private String customerAttr;

	@Value("${cart_items}")
	private String cart_Items;

	@Resource
	private CustomerOrderService customerOrderService;
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "makeOrder.do")
	public String makeOrder(HttpSession httpSession,HttpServletResponse response){


		Customer customer = (Customer) httpSession.getAttribute(customerAttr);
		Integer customerId = customer.getId();
		
		List<CartItem> cartItems = (List<CartItem>) httpSession.getAttribute(cart_Items);
		if(customerOrderService.makeOrder(customerId,cartItems)){
			httpSession.removeAttribute(cart_Items);
			return "redirect:getOrders.do/0";
		}else{
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value = "getOrderHistory.do",method = RequestMethod.GET)
	public void getOrderHistory(Integer customerId,HttpServletResponse response){
		//Customer customer = (Customer)request.getSession().getAttribute("customerAttr");	
		Map<CustomerOrder, List<OrderItem>> orderHistory = customerOrderService.getOrderHistory(customerId);
		JSONUtil.writeJSONToFrontEnd(response, JSONUtil.convertToJSON(orderHistory));
	}
	
	@RequestMapping(value = "acknowledgeOrder.do")
	public String acknowledgeOrder(@RequestParam(value="orderId") Integer orderId,@RequestParam(value="rating") Double rating,HttpServletResponse response){
		if(rating!=null){
			customerOrderService.acknowledgeOrder(orderId, rating);
			return "redirect:getOrders.do/1";
			
		}
		else{
			customerOrderService.acknowledgeOrder(orderId, 10D);
			return "redirect:getOrders.do/1";
		}
		
	}
	
	@RequestMapping(value = "complain.do")
	public String complain(@RequestParam("orderId") Integer orderId,@RequestParam("message") String message,HttpServletResponse response){
		System.out.println();
		if(orderId!=null&&message!=null){
			if(customerOrderService.complain(orderId, message)==true){
				return "redirect:getOrders.do/2";
			}else{

			}
		}
		
			return "redirect:/";
		
	}
	
	@RequestMapping(value = "getOrders.do/{state}")
	public ModelAndView getOrdersDone(@PathVariable(value="state") Integer state,HttpSession httpSession,HttpServletResponse response){
		Customer customer = (Customer) httpSession.getAttribute(customerAttr);
		if(customer!=null){
			Integer customerId = customer.getId();
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("orderState",state);
			List<OrderToShow> list = customerOrderService.getOrder(customerId,state);
			modelAndView.addObject("ordersList",list);
			modelAndView.setViewName("orderHistory");
			
			
			return modelAndView;
		}
		else{
			return new ModelAndView("redirect:/");
		}
	}

	@RequestMapping(value = "viewOrderDetails.do/{orderId}")
	public ModelAndView viewOrderDetails(@PathVariable("orderId") Integer orderId,HttpServletResponse response){
		if(orderId!=null){
			try {
				OrderDetails orderDetails = customerOrderService.viewOrderDetails(orderId);
				return new ModelAndView("acknowledgeOrder","orderDetails",orderDetails);
			} catch (Exception e) {
				return new ModelAndView("redirect:/");
			}
		}
		else{
			return new ModelAndView("redirect:/");
		}
	}
	
	@RequestMapping(value = "complainOrderDetails.do/{orderId}")
	@ResponseBody
	public String complainOrderDetails(@PathVariable("orderId") Integer orderId,HttpServletResponse response){
		if(orderId!=null){
			try {
				OrderDetails orderDetails = customerOrderService.viewOrderDetails(orderId);
				return JSONUtil.convertToJSON(orderDetails);
			} catch (Exception e) {
				return JSONUtil.convertToJSON(false);
			}
		}
		else{
			return JSONUtil.convertToJSON(false);
		}
	}
	 @RequestMapping(value="previewOrder.do")
	 public ModelAndView previewOrder(HttpSession httpSession){
		 try {

			 Customer customer = (Customer) httpSession.getAttribute(customerAttr);
			 Integer customerId = customer.getId();
				
			 List<CartItem> cartItems = (List<CartItem>) httpSession.getAttribute(cart_Items);
			 OrderToPreview otp = customerOrderService.previewOrder(customerId, cartItems);
			 return new ModelAndView("orderPreview","otp",otp);
		} catch (Exception e) {
			return new ModelAndView("redirect:../dishes/viewDishes");
		}
		 	
	    }

	@RequestMapping(value = "viewComplaints.do")
	public ModelAndView viewComplains(HttpSession httpSession){
		try{
			Customer customer = (Customer) httpSession.getAttribute(customerAttr);
			Integer customerId = customer.getId();
			List<ComplaintDetails> cdList = customerOrderService.viewComplaints(customerId);
			return new ModelAndView("viewComplaints","cdList",cdList);

		}catch (Exception e){
			return new ModelAndView("redirect:/");

		}
	}
}
