package com.diy.schedulinghelper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

@WebListener
public class Config implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler s = sf.getScheduler();
			event
				.getServletContext()
				.setAttribute("Scheduler", s);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		try {
			Scheduler s = (Scheduler)event
				.getServletContext()
				.getAttribute("Scheduler");
			s.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}