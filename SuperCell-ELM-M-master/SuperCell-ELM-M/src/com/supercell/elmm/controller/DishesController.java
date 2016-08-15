package com.supercell.elmm.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
//import com.sun.webkit.ContextMenu.ShowContext;
import com.supercell.elmm.entity.Dishes;
import com.supercell.elmm.entity.Merchant;
import com.supercell.elmm.global.Global;
import com.supercell.elmm.service.DishesService;
import com.supercell.elmm.service.MerchantService;
import com.supercell.elmm.service.ProducerService;
import com.supercell.elmm.util.FileUploadUtil;
import com.supercell.elmm.util.JSONUtil;
import com.supercell.elmm.util.PicShowUtil;
import com.supercell.elmm.vo.RecommendDish;


@Controller
@RequestMapping("/dishesController")
public class DishesController {

	private DishesService dishesService;
	@Resource(name = "dishService")
	public void setDishesService(DishesService dishesService) {
		this.dishesService = dishesService;
	}

	private MerchantService merchantService;

	@Resource(name = "merchantService")
	public void setService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}

	private ProducerService producerService;

	@Resource(name = "producerService")
	public void setProducerService(ProducerService producerService) {
		this.producerService = producerService;
	}

	private Destination destination;

	@Resource(name ="recommendDishQueue")
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	@Value("${IMG_SAVE_PATH}")
	private String imgSavePath;
	
	@RequestMapping("showdishes.do")
        public ModelAndView ShowDishes(HttpSession session) throws IOException {
	if (session.getAttribute(Global.CURRENT_MERCHANT)!=null) {
		Merchant merchant = merchantService
				.findMerchantByPhoneNumber((String) session.getAttribute(Global.CURRENT_MERCHANT));
		System.out.println(merchant.getId());
	
		RecommendDish rd=dishesService.reWebService(merchant.getId());
//		response.close();
//		client.close();	
	    //////////////////////////	
//		Merchant merchant = merchantService
//				.findMerchantByPhoneNumber((String) session.getAttribute(Global.CURRENT_MERCHANT));
		
		System.out.println("the dishes id is:::::"+rd.getDishesId());
		List<Dishes> dList=dishesService.queryDishesByMerchant(merchant.getId());
		if (dList!=null) {
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject("dList",dList);
			modelAndView.addObject("reDishesMsg",rd.getDishesId());
			modelAndView.setViewName("showDishes");
//			return new ModelAndView("showDishes","dList",dList);
			return modelAndView;
		   }
		}

			return new ModelAndView("login");			
		}

	@RequestMapping("picshow.do/{picPath}")
	public void picShow(OutputStream os, @PathVariable String picPath) throws IOException {
		// OutputStream os = null;
		String imgPath;
		imgPath = imgSavePath + "//" + picPath;
		System.out.println("the imgPath is:" + imgPath);
		PicShowUtil.getPic(os, imgPath);

	}

	@RequestMapping("deletedishes.do/{dishesId}")
	@ResponseBody
	public String deleteDishes(@PathVariable String dishesId) {
		System.out.println("deletedController....");
		boolean result = dishesService.deleteDish(Integer.parseInt(dishesId));
		if (result) {
			System.out.println("delete succ");
			return "1";
		}
		return "0";
	}

	@RequestMapping("updatedishes.do")
	public String updadteDishes(List<MultipartFile> ufiles, String dishesId, String dishesName, String dishesPrice) {
		System.out.println("this the updatedishes...");
		System.out.println("the pic name::" + ufiles.get(0).isEmpty());
		System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjs");
		System.out.println(!(ufiles.get(0).getName().equals("ufiles")));
		boolean result;
		Dishes dishes = dishesService.findDishById(Integer.parseInt(dishesId));
		if (dishes != null && dishesName != null && dishesPrice != null) {
			dishes.setDishesName(dishesName);
			dishes.setPrice(Integer.parseInt(dishesPrice));
			
			if (ufiles.get(0).isEmpty()) {
				System.out.println("the filepic is update");
				result = dishesService.updateDish(dishes);
			}else {
				List<String> fileName = FileUploadUtil.upload(imgSavePath, ufiles);
				System.out.println("update the picpath....................");
				dishes.setDishesPicPath(fileName.get(0));
				 result = dishesService.updateDish(dishes);
			}
			if (result) {
				System.out.println("update succ....");
				return "redirect:../dishesController/showdishes.do/" ;
			}
		}
		return "redirect:../dishesController/showdishes.do/";
	}

	@RequestMapping("adddishes.do")
	public String addDishes(List<MultipartFile> files, Dishes dishes, HttpSession session) {
		System.out.println("files name:::::::::"+files.get(0).getName());
		System.out.println("--------------------------------------");
		List<String> fileName = FileUploadUtil.upload(imgSavePath, files);
		System.out.println(dishes.getDishesName() + "...." + dishes.getPrice());
		System.out.println(session.getAttribute(Global.CURRENT_MERCHANT));
		Merchant merchant = merchantService
				.findMerchantByPhoneNumber((String) session.getAttribute(Global.CURRENT_MERCHANT));
		System.out.println("the merchant id is:" + merchant.getId());
		Dishes addDish = new Dishes();
		if (dishes.getDishesName() != null && dishes.getPrice() != null) {
			addDish.setDishesName(dishes.getDishesName());
			addDish.setPrice(dishes.getPrice());
			addDish.setDishesPicPath(fileName.get(0));
			addDish.setMerchantId(merchant.getId());
			addDish.setStock(100);
			System.out.println(addDish.getStock());
			boolean result = dishesService.addDish(addDish);
			if (result) {
				System.out.println("add succ....");
				return "redirect:../dishesController/showdishes.do/";
			}
		} else {
			System.out.println("!!!!");
		}
		return "redirect:../dishesController/showdishes.do/";
	}

	@RequestMapping("recommenddishes.do/{dishesId}")
	@ResponseBody
	public String recommenddishes(@PathVariable String dishesId, HttpSession session) {
		System.out.println("start the recommenddishes");
		System.out.println("start the recommenddishes");
		System.out.println("start the recommenddishes");
		System.out.println(dishesId);
			System.out.println("dishesid is not null..");
			
			Merchant merchant = merchantService
					.findMerchantByPhoneNumber((String) session.getAttribute(Global.CURRENT_MERCHANT));
			if (dishesId!=null) {
				Dishes dishes = dishesService.findDishById(Integer.parseInt(dishesId));
//				Merchant merchant = merchantService
//						.findMerchantByPhoneNumber("12345");
				RecommendDish recommendDish = new RecommendDish();
				recommendDish.setDishesId(Integer.parseInt(dishesId));
				recommendDish.setDishName(dishes.getDishesName());
				recommendDish.setMerchantId(merchant.getId());
				recommendDish.setMerchantName(merchant.getShopName());
				String message = JSONUtil.convertToJSON(recommendDish);
				System.out.println(message);
				System.out.println("dishes name:"+dishes.getDishesName());
				producerService.sendMessage(destination, message);
			}
		   return dishesId;
	}
}
