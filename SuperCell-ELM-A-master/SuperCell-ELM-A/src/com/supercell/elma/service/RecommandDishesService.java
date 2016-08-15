package com.supercell.elma.service;

import java.util.List;

import com.supercell.elma.entity.Complaint;
import com.supercell.elma.entity.Merchant;
import com.supercell.elma.entity.MerchantState;
import com.supercell.elma.entity.RecommendedDishes;

public interface RecommandDishesService {
	public List<RecommendedDishes> getAllRecommendedDishes();
	public boolean changeRecommendedDishes(RecommendedDishes recommendedDishes);
	public List<RecommendedDishes> getRecommendedDishesList();
	public RecommendedDishes getRecommendedDishesByMerchantId(Integer MerchantId);
}
