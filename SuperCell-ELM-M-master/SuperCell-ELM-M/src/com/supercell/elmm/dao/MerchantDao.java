package com.supercell.elmm.dao;

import java.util.List;

import com.supercell.elmm.entity.Merchant;

/**
 * Created by WUJO2 on 8/6/2016.
 */
public interface MerchantDao extends GenericDao<Merchant> {
	boolean findMerchant(String phoneNumber, String password);
	boolean addMerchant(Merchant merchant);
	List<Merchant> queryAll();
	public Merchant findMerchantByPhoneNumber(String phoneNumber);
	public int findMerchantIDByPhoneNumber(String phoneNumber);
}
