package com.supercell.elma.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.supercell.elma.dao.RecommendedDishesDao;
import com.supercell.elma.entity.RecommendedDishes;
import com.supercell.elma.service.RecommandDishesService;
@Service("recommandDishesService")
public class RecommandDishesServiceImpl implements RecommandDishesService{
	
	RecommendedDishesDao dao;
	@Resource(name="recommendeddishesDao")
	public void setDao(RecommendedDishesDao dao) {
		this.dao = dao;
	}
	@Override
	public List<RecommendedDishes> getAllRecommendedDishes() {
		return dao.getRecommendedDishes();
	}

	@Transactional
	public boolean changeRecommendedDishes(RecommendedDishes recommendedDishes) {
		// TODO Auto-generated method stub
		return dao.updateRecommended(recommendedDishes);
	}
	@Override
	public List<RecommendedDishes> getRecommendedDishesList() {
		return dao.getRecommendedDishesList();
	}
	@Override
	public RecommendedDishes getRecommendedDishesByMerchantId(Integer MerchantId) {
		return dao.getRecommendedDishesByMerchantId(MerchantId);
	}

	
	
}
