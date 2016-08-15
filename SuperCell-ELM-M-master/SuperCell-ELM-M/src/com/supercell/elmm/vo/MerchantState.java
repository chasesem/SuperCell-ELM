package com.supercell.elmm.vo;

public class MerchantState {
	public static final Integer PASS_AUDIT_MERCHANT_STATE=1;
	public static final Integer AUDIT_MERCHANT_STATE=2;
	public static final Integer NOT_PASS_AUDIT_MERCHANT_STATE=3;
	public static final Integer BLACK_MERCHANT_STATE=4;
	private Integer merchantId;
	private Integer merchantState;
	private String merchantStateText;
	
	public MerchantState() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MerchantState(Integer merchantId, Integer merchantState) {
		super();
		this.merchantId = merchantId;
		this.merchantState = merchantState;
	}

	public MerchantState(Integer merchantId, Integer merchantState,String merchantStateText) {
		super();
		this.merchantId = merchantId;
		this.merchantState = merchantState;
		this.merchantStateText = merchantStateText;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public Integer getMerchantState() {
		return merchantState;
	}
	public void setMerchantState(Integer merchantState) {
		this.merchantState = merchantState;
	}
	public String getMerchantStateText() {
		return merchantStateText;
	}
	public void setMerchantStateText(String merchantStateText) {
		this.merchantStateText = merchantStateText;
	}
	
}
