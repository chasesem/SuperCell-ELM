package com.supercell.elmm.dao;

import java.util.List;

import com.supercell.elmm.entity.Dishes;

public interface DishesDao extends GenericDao<Dishes> {
	public Dishes findDishById(int id);
	List<Dishes> queryDishesByMerchant(int merchantId);
}
