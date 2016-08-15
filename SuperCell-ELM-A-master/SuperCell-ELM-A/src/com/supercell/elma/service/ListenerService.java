package com.supercell.elma.service;

import com.supercell.elma.entity.Complaint;
import com.supercell.elma.entity.MerchantState;
import com.supercell.elma.entity.RecommendedDishes;
import com.supercell.elma.entity.vo.MerchantRating;

public interface ListenerService {
	public void addRecommendedDishes(RecommendedDishes recommendedDishes);
	public void addComplaint(Complaint complaint);
	public void addMerchantState(MerchantState merchantState);
	public void addRating(MerchantRating merchantRating);
	
}
