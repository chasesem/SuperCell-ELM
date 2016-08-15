package com.supercell.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by WUJO2 on 8/6/2016.
 */
@Entity
@Table
public class OrderItem extends GenericEntity {
    public static final String ORDER_ID = "orderId";
    public static final String DISHES_ID = "dishesId";
    public static final String COUNT = "count";

    @Column(nullable = false)
    private Integer orderId;
    @Column(nullable = false)
    private Integer dishesId;
    @Column(nullable = false)
    private Integer count;

    public OrderItem() {
    }

    public OrderItem(Integer orderId, Integer dishesId, Integer count) {
        this.orderId = orderId;
        this.dishesId = dishesId;
        this.count = count;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDishesId() {
        return dishesId;
    }

    public void setDishesId(Integer dishesId) {
        this.dishesId = dishesId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
