package com.supercell.elma.dao;

import java.util.List;

import com.supercell.elma.entity.MerchantState;

public interface MerchantStateDao  {
	public boolean persist(MerchantState merchantState);
	public boolean updateRating(MerchantState merchantState);
	public boolean updateState(MerchantState merchantState);
	public MerchantState getData(String id);
	public List<MerchantState> getAll();
	public List<MerchantState> getLowRating();
	public List<MerchantState> getNormalMerchant();
	public MerchantState getMerchantState(String id);
}
