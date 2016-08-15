package com.supercell.service;

import java.util.List;

import com.supercell.entity.Dishes;

public interface DishesService {
	List<Dishes> getAllDishesOfMerchant(Integer merchantId);
	Dishes getDishes(Integer dishesId);
}