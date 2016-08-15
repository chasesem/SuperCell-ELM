package com.supercell.elmm.dao;

import java.util.List;

import com.supercell.elmm.entity.Complaint;

public interface ComplaintDao extends GenericDao<Complaint>{
	public List<Complaint> getComplaintsByMerchant(int merchantId);
}
