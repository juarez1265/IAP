package com.example.demo.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsMessageProducer {

	private JmsTemplate jmsTemplate;

	@Autowired
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void sendMessage(String destination, String message) {
		System.out.println("destination " + destination);
		System.out.println("message " + message);
		jmsTemplate.send(destination, txtMessage -> txtMessage.createTextMessage(message));
	}

}
