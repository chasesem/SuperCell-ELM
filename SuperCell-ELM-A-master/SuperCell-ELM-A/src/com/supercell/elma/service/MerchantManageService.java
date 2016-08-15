package com.supercell.elma.service;

import java.util.List;

import com.supercell.elma.entity.Complaint;
import com.supercell.elma.entity.Merchant;
import com.supercell.elma.entity.MerchantState;
import com.supercell.elma.entity.vo.MerchantIDVO;
import com.supercell.elma.entity.vo.MerchantRating;
import com.supercell.elma.entity.vo.MerchantStateVO;
import com.supercell.elma.entity.vo.OrderDetailComplaint;
import com.supercell.elma.entity.vo.OrderDetails;

public interface MerchantManageService {

	public Merchant getMerchantInfo(String id);

	public boolean changeStates(MerchantState merchantState);

	public List<MerchantState> getRating();

	public List<Complaint> getAllComplaint();
	
	public Complaint getComplaint(Complaint complaint);
	
	public List<MerchantState> getLowRating();
	
	public OrderDetailComplaint getOrderDetails(String id);
	
	public List<MerchantIDVO> getMerchantID();

	public MerchantStateVO getMerchantState(String id);

	public Complaint getComplaintState(String id);
	
	public void changeComplaintState(Integer id , Integer state, Integer merchantId);
}
