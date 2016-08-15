package com.supercell.elmm.dao.impl;

import org.springframework.stereotype.Repository;

import com.supercell.elmm.dao.CustomerDao;
import com.supercell.elmm.entity.Customer;


/**
 * Created by WUJO2 on 8/6/2016.
 */
@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer> implements CustomerDao {
    @Override
    Class classOfT() {
        return Customer.class;
    }
}
