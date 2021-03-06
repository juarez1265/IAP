package com.example.demo.config;

import java.util.Arrays;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiTemplate;

public class ConfigConnectionFactory {

	private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";

	private static final String ORDER_RESPONSE_QUEUE = "order-response-queue";

	@Value("${spring.jms.jndi-name}")
	private String jndi;

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
		connectionFactory.setTrustedPackages(Arrays.asList("com.websystique.spring", "java.util"));
		return connectionFactory;
	}


	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(ORDER_RESPONSE_QUEUE);
		return template;
	}
}
