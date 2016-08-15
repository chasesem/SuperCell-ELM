package com.supercell.dao.impl;

import com.supercell.dao.DishesDao;
import com.supercell.entity.Dishes;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Created by WUJO2 on 8/6/2016.
 */
@Repository
public class DishesDaoImpl extends GenericDaoImpl<Dishes> implements DishesDao {
    @Override
    Class<Dishes> classOfT() {
        return Dishes.class;
    }
}
