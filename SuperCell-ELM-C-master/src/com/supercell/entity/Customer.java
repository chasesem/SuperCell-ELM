package com.supercell.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by WUJO2 on 8/6/2016.
 */
@Entity
@Table
public class Customer extends GenericEntity {
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String PASSWORD = "password";
    public static final String ADDRESS = "address";

    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @Column(nullable = false, length = 511)
    private String password;
    @Column(nullable = false, length = 511)
    private String address;

    public Customer() {
    }

    public Customer(String phoneNumber, String password, String address) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
