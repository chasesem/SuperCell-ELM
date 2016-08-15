package com.supercell.dao.impl;

import com.supercell.dao.CustomerDao;
import com.supercell.entity.Customer;
import org.springframework.stereotype.Repository;

/**
 * Created by WUJO2 on 8/6/2016.
 */
@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer> implements CustomerDao {
    @Override
    Class<Customer> classOfT() {
        return Customer.class;
    }
}
