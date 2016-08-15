package com.supercell.elmm.service;

import java.util.List;

import com.supercell.elmm.entity.Merchant;

public interface MerchantService {
	public boolean login(String phoneNumber,String password);
	public boolean register(Merchant merchant);
	public boolean updateMerchant(Merchant merchant);
	public boolean updatePassword(Merchant merchant);
	public List<Merchant> queryAllMerchants();
	public Merchant findMerchantByID(int id);
	public Merchant findMerchantByPhoneNumber(String phoneNumber);
	public int findMerchantIDByPhoneNumber(String phoneNumber);
}
