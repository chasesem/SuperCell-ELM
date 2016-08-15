package com.supercell.elmm.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.supercell.elmm.dao.MerchantDao;
import com.supercell.elmm.entity.Merchant;


@Repository("merchantDao")
public class MerchantDaoImpl extends GenericDaoImpl<Merchant> implements MerchantDao {
    @Override
    Class classOfT() {
        return Merchant.class;
    }

	@Override
	public boolean findMerchant(String phoneNumber, String password) {
		System.out.println("password:"+password);
		CriteriaQuery<Merchant> criteriaQuery = getCriteriaBuilder().createQuery(Merchant.class);
		Root<Merchant> mRoot = criteriaQuery.from(Merchant.class);
		Predicate condition = getCriteriaBuilder().and(getCriteriaBuilder().equal(mRoot.get(Merchant.PHONE_NUMBER), phoneNumber)
				,getCriteriaBuilder().equal(mRoot.get(Merchant.PASSWORD), password));
		criteriaQuery.where(condition);
		List<Merchant> merchants = search(criteriaQuery);
        if (merchants.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean addMerchant(Merchant merchant) {
		return persist(merchant);
	}

	@Override
	public List<Merchant> queryAll() {
		List<Merchant> merchants = getAll();
		return merchants;
	}

	@Override
	public Merchant findMerchantByPhoneNumber(String phoneNumber) {
		String jpql = "select m from Merchant m where m.phoneNumber=:phoneNumber";
		Query query = createQuery(jpql);
		List<Merchant> merchants = query.setParameter("phoneNumber", phoneNumber).getResultList();
		Merchant merchant = null;
		if (!merchants.isEmpty()) {
			merchant = merchants.get(0);
		}
		return merchant;
	}

	@Override
	public int findMerchantIDByPhoneNumber(String phoneNumber) {
		String jpql = "select m.id from Merchant m where m.phoneNumber=:phoneNumber";
		Query query = createQuery(jpql);
		List<Integer> merchantList = query.setParameter("phoneNumber", phoneNumber).getResultList();
		Integer merchantId = null;
		if (!merchantList.isEmpty()) {
			merchantId = merchantList.get(0);
		}
		return merchantId;
	}
}
