package com.supercell.elma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by WUJO2 on 8/6/2016.
 */
@Entity
@Table(name="merchantState")
public class MerchantState {
	@Id
	@Column(name="merchantId")
	private Integer merchantId;
	@Column(name="merchantState")
	private Integer merchantState;
	@Column(name="rating")
	private Double rating;
	public Integer getMerchantId() {
	return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
	this.merchantId = merchantId;
	}
	public Double getRating() {
	return rating;
	}
	public void setRating(Double rating) {
	this.rating = rating;
	}
	public void setMerchantState(Integer merchantState) {
		this.merchantState = merchantState;
	}
	public Integer getMerchantState() {
		return merchantState;
	}
}
