package com.example.demo.controllers;

import java.util.List;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.components.JmsMessageConsumer;
import com.example.demo.components.JmsMessageProducer;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@RestController
public class EmployeeControllerRest {

	@Autowired
	private EmployeeRepository repo;

	@Autowired
	private JmsMessageProducer producer;

	@Autowired
	private JmsMessageConsumer consumer;

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/employees")
	public @ResponseBody List<Employee> getAll() {
		List<Employee> result = (List<Employee>) repo.findAll();
		return result;
	}

	@RequestMapping(value = "/jmsMessage", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getProduct(@RequestParam("id") String message) {

		System.out.println("id message " + message);
		String queueName = "DemoQueue";
		producer.sendMessage(queueName, message);
		return "ok";
	}

	@RequestMapping(value = "/jmsConsumer")
	public @ResponseBody String getConsumer() throws JMSException {
		String queueName = "DemoQueue";
		return "ok";
	}
}
