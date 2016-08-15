package com.supercell.service.impl;

import com.supercell.dao.CustomerDao;
import com.supercell.entity.Customer;
import com.supercell.service.CustomerService;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.io.Serializable;
import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;

/**
 * Created by WUJO2 on 8/6/2016.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerDao customerDao;

    @Override
    public Customer login(String phoneNumber, String password) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
        detachedCriteria.add(eq(Customer.PHONE_NUMBER, phoneNumber));
        detachedCriteria.add(eq(Customer.PASSWORD, DigestUtils.md5Hex(password)));
        List<Customer> list = customerDao.search(detachedCriteria);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Integer register(Customer customer) {
        customer.setPassword(DigestUtils.md5Hex(customer.getPassword()));
        Integer id = (Integer) customerDao.save(customer);
        return id == 0 ? null : id;
    }

    @Override
    public Customer queryPhoneNumber(String phoneNumber) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
        detachedCriteria.add(Restrictions.eq(Customer.PHONE_NUMBER, phoneNumber));
        List<Customer> list = customerDao.search(detachedCriteria);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public boolean updateMsg(Customer customer) {
        try {
            customerDao.update(customer);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
