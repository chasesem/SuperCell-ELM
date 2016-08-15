package com.supercell.elma.listener;

import java.io.IOException;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.supercell.elma.entity.Complaint;
import com.supercell.elma.service.ListenerService;
import com.supercell.elma.service.MerchantManageService;
import com.supercell.elma.util.JSONUtil;

public class ComplaintListener implements  MessageListener{
	ListenerService service;
	@Resource(name="listenerService")
	public void setService(ListenerService service) {
		this.service = service;
	}
	public void onMessage(Message message) {
		
		TextMessage textMsg = (TextMessage) message;
		try {
			try {
				String json = textMsg.getText().replace("[", "");
				json = json.replace("]", "");
				Complaint c = (Complaint) JSONUtil.json2Object(json, Complaint.class);
				service.addComplaint(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
