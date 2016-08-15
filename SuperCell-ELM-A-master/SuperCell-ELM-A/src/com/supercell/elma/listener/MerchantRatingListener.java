package com.supercell.elma.listener;

import java.io.IOException;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.supercell.elma.entity.vo.MerchantRating;
import com.supercell.elma.service.ListenerService;
import com.supercell.elma.util.JSONUtil;

public class MerchantRatingListener implements  MessageListener{
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
				MerchantRating merchantRating = (MerchantRating) JSONUtil.json2Object(json, MerchantRating.class);
				service.addRating(merchantRating);
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
