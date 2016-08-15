package com.supercell.elmm.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.supercell.elmm.dao.ComplaintDao;
import com.supercell.elmm.entity.Complaint;
import com.supercell.elmm.entity.Customer;
import com.supercell.elmm.entity.Merchant;

@Repository("complaintDao")
public class ComplaintDaoImpl extends GenericDaoImpl<Complaint> implements ComplaintDao{
	@Override
	Class classOfT() {
		return Complaint.class;
	}
	
	@Override
	public List<Complaint> getComplaintsByMerchant(int merchantId) {
		String jpql = "select c from Complaint c where c.merchantId=:merchantId";
		Query query = createQuery(jpql);
		List<Complaint> complaints = query.setParameter("merchantId", merchantId).getResultList();
		return complaints;
	}
}
