package com.supercell.elma.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.supercell.elma.entity.RecommendedDishes;
import com.supercell.elma.service.RecommandDishesService;
import com.supercell.elma.util.JSONUtil;

@Controller("recommandDishesController")
@RequestMapping("/recommandedDishes")
public class RecommandDishesController {
	
	RecommandDishesService service;
	@Resource(name="recommandDishesService")
	public void setService(RecommandDishesService service) {
		this.service = service;
	}
	@RequestMapping(value="/getdishes.do",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getRecommendedDishes(Integer merchantId) throws UnsupportedEncodingException{
		String json = "";
//		List<RecommendedDishes> allRecommededDishes = service.getAllRecommendedDishes();
		RecommendedDishes recommendedDishes = service.getRecommendedDishesByMerchantId(merchantId);
		if(recommendedDishes==null){
			recommendedDishes = new RecommendedDishes();
			recommendedDishes.setDishesId(0);
		}
		try {
			json = JSONUtil.objetc2Json(recommendedDishes);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;

	}
	
	@RequestMapping("/getlist.do")
	public ModelAndView getList(){
		return new ModelAndView("recommendedDishes","list",service.getRecommendedDishesList());
	}
	
	@RequestMapping("/changestate.do")
	@ResponseBody
	public boolean changeState(Integer id , String state){
		RecommendedDishes recommendedDishes = new RecommendedDishes();
		recommendedDishes.setDishesId(id);
		boolean flag;
		if("up".equals(state)){
			flag = true;
		}else{
			flag = false;
		}
		recommendedDishes.setRecommended(flag);
		return service.changeRecommendedDishes(recommendedDishes);
	}
	
	
	
	@RequestMapping(value = "getRecommendDishlist.do",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getRecommendDishlist(){
		List<RecommendedDishes> allRecommededDishes = service.getAllRecommendedDishes();
		String json = "";
		try {
			json = JSONUtil.objetc2Json(allRecommededDishes);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
}
