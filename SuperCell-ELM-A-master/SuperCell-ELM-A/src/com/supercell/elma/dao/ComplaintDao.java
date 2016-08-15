package com.supercell.elma.dao;

import java.util.List;

import com.supercell.elma.entity.Complaint;


public interface ComplaintDao  {
	public boolean persist(Complaint complaint);
	public List<Complaint> find();
	public Complaint find(Complaint complaint);
	public boolean changeState(Integer id , Integer state);
	public Complaint getComplaintState(String id);
	
}
