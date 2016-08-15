package com.supercell.elmm.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.supercell.elmm.dao.DishesDao;
import com.supercell.elmm.entity.Dishes;

/**
 * Created by WUJO2 on 8/6/2016.
 */
@Repository("dishesDao")
public class DishesDaoImpl extends GenericDaoImpl<Dishes> implements DishesDao {
    @Override
    Class classOfT() {
        return Dishes.class;
    }

	@Override
	public Dishes findDishById(int id) {
		String jpql = "select d from Dishes d where d.id=:id";
		Query query = createQuery(jpql);
		query.setParameter("id", id);
		Dishes dishes = (Dishes) query.getResultList().get(0);
		return dishes;
	}

	@Override
	public List<Dishes> queryDishesByMerchant(int merchantId) {
		String jpql = "select d from Dishes d where d.merchantId=:merchantId and d.avaliable=:avaliable";
		Query query = createQuery(jpql);
		query.setParameter("merchantId", merchantId);
		query.setParameter("avaliable", true);
		List<Dishes> dishes = query.getResultList();
		return dishes;
	}
}
