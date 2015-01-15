package com.diy.schedulinghelper;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;

public class SchedulingHelper {

	Scheduler my_sched;
	Logger log = LoggerFactory.getLogger(SchedulingHelper.class);    
	
	public ServletContext getServletContext() {
		HttpServlet servlet = (HttpServlet)(
				MessageContext
					.getCurrentContext()
					.getProperty(HTTPConstants.MC_HTTP_SERVLET)
		);
		ServletContext ctx = servlet.getServletContext();
		return ctx;
	}
	
	public static void main(String[] args) {
		try {
			//	s.shutdown();
			SchedulingHelper sh = new SchedulingHelper();
			sh.startScheduler();
			sh.scheduleWSCall("2015-01-15", "17:00", true, "test");
			Thread.sleep(10000L);
			sh.getScheduledJobsStats();
			Thread.sleep(60000L);
			sh.stopScheduler();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String startScheduler() {
	    Log("******************************************************************************");
	    Log("startScheduler ");
	    Log("******************************************************************************");
		String strReturn = null;
		try {
			if (
				(Scheduler)(getServletContext().getAttribute("Scheduler"))
				!=
				null
			) {
				my_sched = (Scheduler)(getServletContext().getAttribute("Scheduler"));
				Log("sched existed");
			} else {
				SchedulerFactory sf = new StdSchedulerFactory();
				my_sched = sf.getScheduler();
				getServletContext().setAttribute("Scheduler", my_sched);
				Log("sched did not exist");
			}
			my_sched.start();
			Log("sched was started");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return strReturn;		
	}
	
	public String stopScheduler() {
	    Log("******************************************************************************");
	    Log("stopScheduler ");
	    Log("******************************************************************************");
		String strReturn = null;
		try {
			if (
				(Scheduler)(getServletContext().getAttribute("Scheduler"))
				!=
				null
			) {
				my_sched = (Scheduler)(getServletContext().getAttribute("Scheduler"));
				my_sched.shutdown(true);
				Log("sched existed and was shutdown");
			} else {
				//SchedulerFactory sf = new StdSchedulerFactory();
				//my_sched = sf.getScheduler();
				//getServletContext().setAttribute("Scheduler", my_sched);
				Log("sched did not exist");
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return strReturn;		
	}
	
	public String scheduleWSCall(
			String strDate,
			String strTime,
			Boolean bRecurent,
			String strSOAPMessage
	) {
	    Log("******************************************************************************");
	    Log("scheduleWSCall ");
	    Log("******************************************************************************");
		String strReturn = null;
		try {
			if (
				(Scheduler)(getServletContext().getAttribute("Scheduler"))
				!=
				null
			) {
				my_sched = (Scheduler)(getServletContext().getAttribute("Scheduler"));
				Date runTime = DateBuilder.evenMinuteDate(new Date());
				JobDetail job = JobBuilder
						.newJob(WSCallJob.class)
						.withIdentity("job1", "group1")
						.build();
				job.getJobDataMap().put("SOAPMessage", "Vive le savon!");
				SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder
						.newTrigger()
						.withIdentity("trigger1", "group1")
						.startAt(runTime)
						.withSchedule(
								SimpleScheduleBuilder
									.simpleSchedule()
									.withIntervalInSeconds(10)
									//.withIntervalInHours(1)
									//.repeatSecondlyForTotalCount(5)
									.repeatForever()
								)
						//		.modifiedByCalendar("holidays")
						.build();
				my_sched.scheduleJob(job, trigger);
				Log(job.getKey() + " will run at: " + runTime);
			} else {
				Log("sched was down");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strReturn;		
	}
	
	public String getScheduledJobsStats() {
	    Log("******************************************************************************");
	    Log("getScheduledJobsStats ");
	    Log("******************************************************************************");
		String strReturn = null;
		try {
			if (
				(Scheduler)(getServletContext().getAttribute("Scheduler"))
				!=
				null
			) {
				my_sched = (Scheduler)(getServletContext().getAttribute("Scheduler"));
				List<String> strGroupsList = my_sched.getJobGroupNames();
				ListIterator<String> liGroups = strGroupsList.listIterator();
				if (strGroupsList.isEmpty()) {
					Log("no groups");
				}
				while(liGroups.hasNext()) {
					Log("Group: "+liGroups.next());					
				}
			} else {
				Log("sched was down");
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return strReturn;
	}

	void Log(String strMessage) {
		System.out.println(strMessage);
	}

}
