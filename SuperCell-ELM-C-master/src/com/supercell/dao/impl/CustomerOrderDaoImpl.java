package com.supercell.dao.impl;

import com.supercell.dao.CustomerOrderDao;
import com.supercell.entity.CustomerOrder;
import org.springframework.stereotype.Repository;

/**
 * Created by WUJO2 on 8/6/2016.
 */
@Repository
public class CustomerOrderDaoImpl extends GenericDaoImpl<CustomerOrder> implements CustomerOrderDao {
    @Override
    Class<CustomerOrder> classOfT() {
        return CustomerOrder.class;
    }
}
