package com.supercell.elmm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.supercell.elmm.dao.MerchantDao;
import com.supercell.elmm.entity.Merchant;
import com.supercell.elmm.service.MerchantService;

@Service("merchantService")
public class MerchantServiceImpl implements MerchantService{
	private MerchantDao dao;
	@Resource(name="merchantDao")
	public void setDao(MerchantDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean login(String phoneNumber, String password) {
		boolean result = dao.findMerchant(phoneNumber, password);
		return result;
	}

	@Override
	@Transactional
	public boolean register(Merchant merchant) {
		merchant.setNumberOfOrders(0);
		merchant.setRating(10.0);
		boolean result = dao.addMerchant(merchant);
		return result;
	}

	@Override
	@Transactional
	public boolean updateMerchant(Merchant merchant) {
		Merchant targetMerchant = dao.get(merchant.getId());
		targetMerchant.setPhoneNumber(merchant.getPhoneNumber());
//		targetMerchant.setPassword(merchant.getPassword());
		targetMerchant.setAddress(merchant.getAddress());
//		targetMerchant.setNumberOfOrders(merchant.getNumberOfOrders());
//		targetMerchant.setRating(merchant.getRating());
		targetMerchant.setShopName(merchant.getShopName());
		if (merchant.getShopPicPath()!=null && !merchant.getShopPicPath().equals("")) {
			targetMerchant.setShopPicPath(merchant.getShopPicPath());
		}
		boolean result = dao.update(targetMerchant);
		return result;
	}

	@Override
	public List<Merchant> queryAllMerchants() {
		List<Merchant> merchants = dao.getAll();
		return merchants;
	}

	@Override
	public Merchant findMerchantByID(int id) {
		Merchant merchant = dao.get(id);
		return merchant;
	}

	@Override
	public Merchant findMerchantByPhoneNumber(String phoneNumber) {
		Merchant merchant = dao.findMerchantByPhoneNumber(phoneNumber);
		return merchant;
	}

	@Override
	public int findMerchantIDByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		int merchantId = dao.findMerchantIDByPhoneNumber(phoneNumber);
		return merchantId;
	}

	@Override
	@Transactional
	public boolean updatePassword(Merchant merchant) {
		// TODO Auto-generated method stub
		Merchant targetMerchant = dao.get(merchant.getId());
		targetMerchant.setPassword(merchant.getPassword());
		boolean result = dao.update(targetMerchant);
		return result;
	}
	
	
}
