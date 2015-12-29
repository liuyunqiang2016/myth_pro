package com.viatt.zhjtpro.time;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class TimeListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
	}

	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();		
		System.getProperties().put("org.quartz.properties", "quartz_dayend.properties");
		try {
			StdSchedulerFactory.getDefaultScheduler().start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
