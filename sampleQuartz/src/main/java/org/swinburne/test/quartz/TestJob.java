package org.swinburne.test.quartz;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJob implements Job {

	Logger logger = Logger.getLogger(TestJob.class);

	public void execute(final JobExecutionContext ctx) throws JobExecutionException {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BeanFactory factory = context;
		Object test = factory.getBean("writer");

		System.out.println("Executing Job: "+test);
		logger.error("Executing Job");
	}

}