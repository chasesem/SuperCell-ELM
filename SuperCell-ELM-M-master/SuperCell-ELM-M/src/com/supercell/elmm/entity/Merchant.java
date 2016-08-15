package com.supercell.elmm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by WUJO2 on 8/6/2016.
 */
@Entity
@Table
public class Merchant extends GenericEntity {
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String PASSWORD = "password";
    public static final String SHOP_NAME = "shopName";
    public static final String SHOP_PIC_PATH = "shopPicPath";
    public static final String LICENSE_PIC_PATH = "licensePicPath";
    public static final String ID_CARD_PIC_PATH = "idCardPicPath";
    public static final String ADDRESS = "address";
    public static final String RATING = "rating";
    public static final String NUMBER_OF_ORDERS = "numberOfOrders";

    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @Column(nullable = false, length = 511)
    private String password;
    @Column(nullable = false)
    private String shopName;
    @Column(nullable = false)
    private String shopPicPath;
    @Column(nullable = false)
    private String licensePicPath;
    @Column(nullable = false)
    private String idCardPicPath;
    @Column(nullable = false, length = 511)
    private String address;
    @Column(nullable = false)
    private Double rating;
    @Column(nullable = false)
    private Integer numberOfOrders;

    public Merchant() {
    }

    public Merchant(
            String phoneNumber,
            String password,
            String shopName,
            String shopPicPath,
            String licensePicPath,
            String idCardPicPath,
            String address,
            Double rating,
            Integer numberOfOrders) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.shopName = shopName;
        this.shopPicPath = shopPicPath;
        this.licensePicPath = licensePicPath;
        this.idCardPicPath = idCardPicPath;
        this.address = address;
        this.rating = rating;
        this.numberOfOrders = numberOfOrders;
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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopPicPath() {
        return shopPicPath;
    }

    public void setShopPicPath(String shopPicPath) {
        this.shopPicPath = shopPicPath;
    }

    public String getLicensePicPath() {
        return licensePicPath;
    }

    public void setLicensePicPath(String licensePicPath) {
        this.licensePicPath = licensePicPath;
    }

    public String getIdCardPicPath() {
        return idCardPicPath;
    }

    public void setIdCardPicPath(String idCardPicPath) {
        this.idCardPicPath = idCardPicPath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }
}
