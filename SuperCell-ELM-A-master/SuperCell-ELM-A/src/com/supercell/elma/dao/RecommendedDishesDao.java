package com.supercell.elma.dao;

import java.util.List;

import com.supercell.elma.entity.RecommendedDishes;

public interface RecommendedDishesDao  {
	public boolean persist(RecommendedDishes recommendedDishes);
	public boolean updateDishes(RecommendedDishes recommendedDishes);
	public List<RecommendedDishes> getRecommendedDishes();
	boolean updateRecommended(RecommendedDishes recommendedDishes);
	public List<RecommendedDishes> getRecommendedDishesList();
	public RecommendedDishes getRecommendedDishesByMerchantId(Integer MerchantId);
}
