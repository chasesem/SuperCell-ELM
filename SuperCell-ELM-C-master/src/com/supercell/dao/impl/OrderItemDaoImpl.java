package com.supercell.dao.impl;

import com.supercell.dao.OrderItemDao;
import com.supercell.entity.OrderItem;
import org.springframework.stereotype.Repository;

/**
 * Created by WUJO2 on 8/6/2016.
 */
@Repository
public class OrderItemDaoImpl extends GenericDaoImpl<OrderItem> implements OrderItemDao {
    @Override
    Class<OrderItem> classOfT() {
        return OrderItem.class;
    }
}
