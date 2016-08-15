package com.supercell.elma.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.supercell.elma.dao.ComplaintDao;
import com.supercell.elma.entity.Complaint;
import com.supercell.elma.entity.MerchantState;

@Repository("complaintDao")
public class ComplaintDaoImpl  implements ComplaintDao {
	@PersistenceContext(name="elmunit")
    protected EntityManager manager;
	@Override
	@Transactional
	public boolean persist(Complaint complaint) {
		try{
		manager.persist(complaint);
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	
	public List<Complaint> find() {
		String jpql = "select m from Complaint m ";
		Query query = manager.createQuery(jpql);
		List<Complaint> list = query.getResultList();
		return list;
	}

	@Override
	public Complaint find(Complaint complaint) {
		return manager.find(Complaint.class, complaint.getOrderId());
	}




	@Override
	public Complaint getComplaintState(String id) {
		Complaint complaint = manager.find(Complaint.class, Integer.parseInt(id));
		return complaint;
	}


	@Override
	public boolean changeState(Integer id, Integer state) {
		Complaint complaint = manager.find(Complaint.class, id);
		complaint.setComplainState(state);
		manager.merge(complaint);
		// TODO Auto-generated method stub
		return true;
	}
	
	
	


}
