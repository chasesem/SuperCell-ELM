package com.supercell.elma.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.naming.ldap.ManageReferralControl;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.loader.custom.Return;
import org.springframework.stereotype.Repository;

import com.supercell.elma.dao.MerchantStateDao;
import com.supercell.elma.entity.Merchant;
import com.supercell.elma.entity.MerchantState;

import oracle.jdbc.dcn.QueryChangeDescription.QueryChangeEventType;

@Repository("merchantStateDao")
public class MerchantStateDaoImpl  implements MerchantStateDao {
	EntityManager manager;
	@PersistenceContext(name="elmunit")
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	public boolean persist(MerchantState merchantState){
		try {
			merchantState.setRating(10.0);
			manager.persist(merchantState);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean updateRating(MerchantState merchantState){
		try {
			MerchantState merchant2 = manager.find(MerchantState.class, merchantState.getMerchantId());
			merchant2.setRating(merchantState.getRating());
			manager.merge(merchant2);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public boolean updateState(MerchantState merchantState){
		try {
			MerchantState merchant2 = manager.find(MerchantState.class, merchantState.getMerchantId());
			merchant2.setMerchantState(merchantState.getMerchantState());
			manager.merge(merchant2);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public MerchantState getData(String id){
		MerchantState merchantState = manager.find(MerchantState.class, id);
		return merchantState;
	}
	
	public List<MerchantState> getAll(){
		String jpql = "select m from MerchantState m ";
		Query query = manager.createQuery(jpql);
		List<MerchantState> list = query.getResultList();
		return list;
	}
//	public List<Merchant> get
	//public 
	public List<MerchantState> getLowRating(){
		String jpql = "select m from MerchantState m where m.rating <:lowRating";
		Query query = manager.createQuery(jpql);
		query.setParameter("lowRating", 5.0);
		List<MerchantState> list = query.getResultList();
		return list;
	}
	@Override
	public List<MerchantState> getNormalMerchant() {
		String jpql = "select m from MerchantState m where m.merchantState =:normal";
		Query query = manager.createQuery(jpql);
		query.setParameter("normal", 1);
		List<MerchantState> list = query.getResultList();
		return list;
		
	}
	@Override
	public MerchantState getMerchantState(String id) {
		// TODO Auto-generated method stub
		MerchantState merchantState = manager.find(MerchantState.class, Integer.parseInt(id));
		return merchantState;
	}
}
