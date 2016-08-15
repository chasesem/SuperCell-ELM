package com.supercell.elmm.service;

import java.util.List;

import com.supercell.elmm.vo.ComplaintInfo;

public interface ComplaintService {
	public List<ComplaintInfo> getComplaintInfosByMerchantId(int merchantId);
}
