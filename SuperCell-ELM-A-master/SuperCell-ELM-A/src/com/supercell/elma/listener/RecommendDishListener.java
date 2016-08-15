package com.supercell.elma.listener;

import java.io.IOException;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.supercell.elma.entity.MerchantState;
import com.supercell.elma.entity.RecommendedDishes;
import com.supercell.elma.service.ListenerService;
import com.supercell.elma.util.JSONUtil;

public class RecommendDishListener implements MessageListener{
	private ListenerService service;
	@Resource(name="listenerService")
	public void setService(ListenerService service) {
		this.service = service;
	}
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		TextMessage textMsg = (TextMessage) message;
		try {
			String json = textMsg.getText().replace("[", "");
			json = json.replace("]", "");
			RecommendedDishes recommendedDishes = null;
			try {
				recommendedDishes = (RecommendedDishes) JSONUtil.json2Object(json, RecommendedDishes.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			service.addRecommendedDishes(recommendedDishes);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
