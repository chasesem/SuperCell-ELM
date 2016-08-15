package com.supercell.elma.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.supercell.elma.dao.ComplaintDao;
import com.supercell.elma.dao.MerchantStateDao;
import com.supercell.elma.dao.RecommendedDishesDao;
import com.supercell.elma.entity.Complaint;
import com.supercell.elma.entity.MerchantState;
import com.supercell.elma.entity.RecommendedDishes;
import com.supercell.elma.entity.vo.MerchantRating;
import com.supercell.elma.service.ListenerService;
@Service("listenerService")
public class ListenerServiceImpl implements ListenerService{
	private ComplaintDao complaintDao;
	private MerchantStateDao merchantStatdao;
	private RecommendedDishesDao recommendeddishesDao;
	@Resource(name="complaintDao")
	public void setComplaintDao(ComplaintDao complaintDao) {
		this.complaintDao = complaintDao;
	}
	@Resource(name="merchantStateDao")
	public void setMerchantStatdao(MerchantStateDao merchantStatdao) {
		this.merchantStatdao = merchantStatdao;
	}
	@Resource(name="recommendeddishesDao")
	public void setRecommendeddishesDao(RecommendedDishesDao recommendeddishesDao) {
		this.recommendeddishesDao = recommendeddishesDao;
	}
	
	@Transactional
	public void addRecommendedDishes(RecommendedDishes recommendedDishes) {
		if(!recommendeddishesDao.persist(recommendedDishes)){
			recommendeddishesDao.updateDishes(recommendedDishes);
		}
	}
	@Transactional
	public void addComplaint(Complaint complaint) {
		complaintDao.persist(complaint);
	}
	@Transactional
	public void addMerchantState(MerchantState merchantState) {
		merchantStatdao.persist(merchantState);
	}
	@Transactional
	public void addRating(MerchantRating merchantRating) {
		MerchantState merchantState = new MerchantState();
		merchantState.setMerchantId(merchantRating.getMerchantId());
		merchantState.setRating(merchantRating.getRating());
		merchantStatdao.updateRating(merchantState);
	}

}
