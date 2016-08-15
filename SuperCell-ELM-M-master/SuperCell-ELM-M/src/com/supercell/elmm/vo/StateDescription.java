package com.supercell.elmm.vo;

public class StateDescription {
	private String action;
	private String description;
	private String url;
	public StateDescription(){}
	public StateDescription(String action, String description, String url) {
		super();
		this.action = action;
		this.description = description;
		this.url = url;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
