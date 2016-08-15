package com.supercell.service.impl;

import com.supercell.service.JMSProducerService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by WUJO2 on 8/9/2016.
 */
@Service
public class JMSProducerServiceImpl implements JMSProducerService {
    @Resource
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessageToAdmin(final String message) {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

    @Override
    public void sendCustomerComplaintToAdmin(final String message) {
        jmsTemplate.send("customer-complaint", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

    @Override
    public void sendMerchantRatingToAdmin(final String message) {
        jmsTemplate.send("merchant-rating", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }
}
