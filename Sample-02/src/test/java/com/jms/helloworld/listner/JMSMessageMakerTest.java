package com.jms.helloworld.listner;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:JMSConfigProducer.xml"})
public class JMSMessageMakerTest {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private JmsTemplate jtm;
	
	
	@Test
	public void test() {
		Assert.assertNotNull(context);
		
		jtm.send(new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("Hello World at the time : "+new Date());
			}
		});
		
		System.out.println("completed! send");
	}
	

}
