package com.supercell.elma.listener;

import java.io.IOException;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.supercell.elma.entity.MerchantState;
import com.supercell.elma.service.ListenerService;
import com.supercell.elma.service.MerchantManageService;
import com.supercell.elma.util.JSONUtil;

public class MerchantStateListener implements MessageListener{
	
	private ListenerService service;
	@Resource(name="listenerService")
	public void setService(ListenerService service) {
		this.service = service;
	}
	public void onMessage(Message message) {
		TextMessage textMsg = (TextMessage) message;
		try {
			String json = textMsg.getText().replace("[", "");
			json = json.replace("]", "");
			try {
				MerchantState merchantState = (MerchantState) JSONUtil.json2Object(json, MerchantState.class);
				service.addMerchantState(merchantState);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
