package com.supercell.elma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Merchant  {
	@Id
	@Column(name="id")
	private String id;
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
    private int merchantState;
    public int getMerchantState() {
		return merchantState;
	}

	public void setMerchantState(int merchantState) {
		this.merchantState = merchantState;
	}

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
    public void setId(String id) {
		this.id = id;
	}
    public String getId() {
		return id;
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
