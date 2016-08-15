package com.supercell.entity;

import com.supercell.misc.enums.OrderState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by WUJO2 on 8/6/2016.
 */
@Entity
@Table
public class CustomerOrder extends GenericEntity {
    public static final String USER_ID = "userId";
    public static final String MERCHANT_ID = "merchantId";
    public static final String TOTAL = "total";
    public static final String RATING = "rating";
    public static final String ADDRESS = "address";
    public static final String DATE_OF_ORDER = "dateOfOrder";
    public static final String STATE = "state";

    @Column(nullable = false)
    private Integer userId;
    @Column(nullable = false)
    private Integer merchantId;
    @Column(nullable = false)
    private Integer total;
    @Column(nullable = false)
    private Double rating;
    @Column(nullable = false, length = 511)
    private String address;
    @Column(nullable = false)
    private Date dateOfOrder;
    @Enumerated
    @Column(nullable = false)
    private OrderState state;

    public CustomerOrder() {
    }

    public CustomerOrder(
            Integer userId,
            Integer merchantId,
            Integer total,
            Double rating,
            String address,
            Date dateOfOrder,
            OrderState state) {
        this.userId = userId;
        this.merchantId = merchantId;
        this.total = total;
        this.rating = rating;
        this.address = address;
        this.dateOfOrder = dateOfOrder;
        this.state = state;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}
