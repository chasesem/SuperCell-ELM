package com.supercell.elma.protocol;

import java.util.List;

public class Protocol {
	public int action;
	public List<Object> list;

	public Protocol() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Protocol(int action, List<Object> list) {
		super();
		this.action = action;
		this.list = list;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}
}
