package com.supercell.service.impl;

import static org.hibernate.criterion.Restrictions.eq;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.supercell.dao.DishesDao;
import com.supercell.entity.Dishes;
import com.supercell.service.DishesService;

/**
 * @author ZHENGNE2
 */

@Service
public class DishesServiceImpl implements DishesService {

    @Resource
    private DishesDao dishesDao;

    @Override
    public List<Dishes> getAllDishesOfMerchant(Integer merchantId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Dishes.class);
        detachedCriteria.add(eq(Dishes.MERCHANT_ID, merchantId));
        detachedCriteria.add(eq(Dishes.AVAILABLE, true));
        return dishesDao.search(detachedCriteria);
    }

    @Override
    public Dishes getDishes(Integer dishesId) {
        return dishesDao.get(dishesId);
    }

}
