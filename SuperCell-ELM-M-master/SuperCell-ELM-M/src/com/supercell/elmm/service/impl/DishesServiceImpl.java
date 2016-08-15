package com.supercell.elmm.service.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supercell.elmm.dao.DishesDao;
import com.supercell.elmm.entity.Dishes;
import com.supercell.elmm.service.DishesService;
import com.supercell.elmm.vo.RecommendDish;

@Service("dishService")
public class DishesServiceImpl implements DishesService{
	private DishesDao dao;
	
	@Resource(name="dishesDao")
	public void setDao(DishesDao dao) {
		this.dao = dao;
	}
	private Client client;
	@Resource(name="jerseyClient")
	public void setClient(Client client) {
		this.client = client;
	}
	
//	@Value("${RecommendDishWebservice}")
//	private String recommendDishDestination;
	
	@Override
	@Transactional
	public boolean addDish(Dishes dish) {
		dish.setAvaliable(true);
		return dao.persist(dish);
	}

	@Override
	@Transactional
	public boolean deleteDish(int dishId) {
		Dishes dishes = dao.get(dishId);
		dishes.setAvaliable(false);
		return dao.update(dishes);
	}

	@Override
	@Transactional
	public boolean updateDish(Dishes dishes) {
//		Dishes targetdishes = dao.get(dishes.getId());
//		targetdishes.setDishesName(dishes.getDishesName());
//		targetdishes.setDishesPicPath(dishes.getDishesPicPath());
//		targetdishes.setPrice(dishes.getPrice());
//		targetdishes.setStock(dishes.getStock());
//		targetdishes.setAvaliable(dishes.getAvaliable());
//		return dao.update(dishes);
		boolean r1 = deleteDish(dishes.getId());
		dishes.setId(null);
		boolean r2 = addDish(dishes);
		if (r1 && r2) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Dishes findDishById(int id) {
		Dishes dishes = dao.findDishById(id);
//		int price = dishes.getPrice()/100;
//		dishes.setPrice(price);
		return dishes;
	}

	@Override
	public List<Dishes> queryDishesByMerchant(int merchantId) {
		List<Dishes> dishes = dao.queryDishesByMerchant(merchantId);
//		for(int i=0;i < dishes.size();){
//			int price = dishes.get(i).getPrice()/100;
//			dishes.get(i).setPrice(price);
//		}
		return dishes;
	}

	@Override
	public RecommendDish reWebService(int merchantId) {
		// TODO Auto-generated method stub
		String  reString="http://10.222.232.30:8080/SuperCell-ELM-A/recommandedDishes/getdishes.do?merchantId="+merchantId;
//		String  reString=recommendDishDestination+"="+merchantId;
		WebTarget taget=client.target(reString);
		Response response=taget.request().get();
		String value=response.readEntity(String.class);
		System.out.println(value);
		System.out.println("----------------------------------???");
		String json = value.replace("[", "");
		json = json.replace("]", "");
		ObjectMapper mapper = new ObjectMapper();
		RecommendDish rd = null;
		try {
			rd = mapper.readValue(json, RecommendDish.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rd;
	}

}
