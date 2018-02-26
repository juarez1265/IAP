package com.example.demo.components;

import java.text.MessageFormat;

import javax.jms.JMSException;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
public class JmsMessageConsumer {

	private final String destination = "DemoQueue";

	// public String messageConsumer(String destination) throws JMSException {
	// TextMessage txt = null;
	// if (destination != null)
	// txt = (TextMessage) jmsTemplate.receive(destination);
	// System.out.println(txt.getText());
	// return txt.getText();
	// }

	@JmsListener(destination = destination)
	public void receiveMessage(final Message message) throws JMSException {
		MessageHeaders headers = message.getHeaders();

		String txt = message.getPayload().toString();
		String saludo = "Hola amiwo {0}";
		System.out.println(MessageFormat.format(saludo, txt));

	}
}
