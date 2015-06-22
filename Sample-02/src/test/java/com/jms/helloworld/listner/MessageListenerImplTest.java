package com.jms.helloworld.listner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:JMSConfig.xml"})
public class MessageListenerImplTest {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private JmsTemplate tmp;
	
	@Test
	public void test() {
		Assert.assertTrue(true);
//		System.out.println((TextMessage)tmp.receiveAndConvert());
	}

}
