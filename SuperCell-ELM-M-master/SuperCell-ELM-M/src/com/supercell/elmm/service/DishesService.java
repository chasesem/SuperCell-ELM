package com.supercell.elmm.service;

import java.util.List;
import com.supercell.elmm.entity.Dishes;
import com.supercell.elmm.entity.Merchant;
import com.supercell.elmm.vo.RecommendDish;

public interface DishesService {
	public boolean addDish(Dishes dish);
	public boolean deleteDish(int dishId);
	public boolean updateDish(Dishes dishes);
	public Dishes findDishById(int id);
	public List<Dishes> queryDishesByMerchant(int merchantId);
	public RecommendDish reWebService(int merchantId) ;
}
