package com.supercell.elma.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.supercell.elma.dao.RecommendedDishesDao;
import com.supercell.elma.entity.RecommendedDishes;


@Repository("recommendeddishesDao")
public class RecommendedDishesDaoImpl  implements RecommendedDishesDao {
	
	EntityManager manager;
	@PersistenceContext(name="elmunit")
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public boolean persist(RecommendedDishes recommendedDishes) {
		recommendedDishes.setRecommended(false);
		try {
			manager.merge(recommendedDishes);
		} catch (Exception e) {
			e.printStackTrace();
			return false;// TODO: handle exception
		}
		return true;
	}

	public boolean updateRecommended(RecommendedDishes recommendedDishes) {
		try{
			
		RecommendedDishes recommendedDishes2 = manager.find(RecommendedDishes.class, recommendedDishes.getDishesId());
		recommendedDishes2.setRecommended(recommendedDishes.getRecommended());
		manager.merge(recommendedDishes2);
		
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<RecommendedDishes> getRecommendedDishes() {
		String jpql = "select r from RecommendedDishes r where r.recommended=:avaliable";
		Query query = manager.createQuery(jpql);
		query.setParameter("avaliable", true);
		List<RecommendedDishes> list = query.getResultList();
		return list;
	}

	@Transactional
	public boolean updateDishes(RecommendedDishes recommendedDishes) {
		// TODO Auto-generated method stub
		try{
			RecommendedDishes recommendedDishes2 = manager.find(RecommendedDishes.class, recommendedDishes.getMerchantId());
			recommendedDishes2.setDishesId(recommendedDishes.getDishesId());
			recommendedDishes2.setDishName(recommendedDishes.getDishName());
			recommendedDishes2.setRecommended(false);
			manager.merge(recommendedDishes2);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}
	public List<RecommendedDishes> getRecommendedDishesList() {
		String jpql = "select r from RecommendedDishes r";
		Query query = manager.createQuery(jpql);
		List<RecommendedDishes> list = query.getResultList();
		return list;
	}

	@Override
	public RecommendedDishes getRecommendedDishesByMerchantId(Integer MerchantId) {
		String jpql = "select r from RecommendedDishes r where r.recommended=:avaliable and r.merchantId=:name";
		Query query = manager.createQuery(jpql);
		query.setParameter("name", MerchantId);
		query.setParameter("avaliable", true);
		List<RecommendedDishes> list = query.getResultList();
		if(list.size()==1){
			return list.get(0);
		}
		return null;
	}
	
}
