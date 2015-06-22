package com.jms.helloworld.listner;

import javax.jms.JMSException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class JMSApp {
	public static void main(String[] args) throws JMSException {
		System.out.println("LISTNER STARTED");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "JMSConfigConsumer.xml" });
	}
}
