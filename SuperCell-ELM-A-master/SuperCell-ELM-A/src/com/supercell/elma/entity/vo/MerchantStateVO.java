package com.supercell.elma.entity.vo;


public class MerchantStateVO {
	private String merchantId;
	private String merchantStateText;
	private String merchantState;
	public String getMerchantId() {
		return merchantId;
	}
	public String getMerchantStateText() {
		return merchantStateText;
	}
	public void setMerchantStateText(String merchantStateText) {
		this.merchantStateText = merchantStateText;
	}
	public void setMerchantState(String merchantState) {
		this.merchantState = merchantState;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantState() {
		return merchantState;
	}
	public void setMerchantStateText(int i) {
		switch (i) {
		case 1:
			merchantStateText = "审核通过";
			break;
		case 2:
			merchantStateText = "待审核";
			break;
		case 3:
			merchantStateText = "审核未通过";
			break;
		case 4:
			merchantStateText = "已拉黑";
		default:
			break;
		}
	}
	
}
