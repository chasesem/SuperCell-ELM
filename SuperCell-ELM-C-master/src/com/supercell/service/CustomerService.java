package com.supercell.service;

import com.supercell.entity.Customer;

/**
 * Created by WUJO2 on 8/6/2016.
 */
public interface CustomerService {
    Customer login(String phoneNumber, String password);

	Integer register(Customer customer);

	Customer queryPhoneNumber(String phoneNumber);

	boolean updateMsg(Customer customer);
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           