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
	
	private ServletContext getServletContext() {
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
			sh.scheduleWSCall("joby", "2015-01-15", "17:00", true, 5, 10, "test");
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
			strReturn = "sched was started";
			Log(strReturn);
		} catch (SchedulerException e) {
			e.printStackTrace();
			strReturn = e.getMessage();
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
			String strName,
			String strDate,
			String strTime,
			Boolean bRecurent,
			int runCount,
			int delay,
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
						.withIdentity(
								strName+"_Job",
								"DefaultGroup"
								)
						.build();
				job.getJobDataMap().put("SOAPMessage", "Vive le savon!");
				SimpleScheduleBuilder
					.simpleSchedule()
					.withIntervalInSeconds(10);
				SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder
						.newTrigger()
						.withIdentity(strName+"_Trig", "DefaultGroup")
						.startAt(runTime)
						.withSchedule(
								SimpleScheduleBuilder
									//.withIntervalInHours(1)
									//.repeatSecondlyForTotalCount(5)
									//.repeatForever()
									.repeatSecondlyForTotalCount(
											runCount,
											delay
											)
								)
						//		.modifiedByCalendar("holidays")
						.build();
				my_sched.scheduleJob(job, trigger);
				strReturn = job.getKey() + " will run at: " + runTime;
			} else {
				strReturn = "sched was down";
			}
			Log(strReturn);
		} catch (Exception e) {
			e.printStackTrace();
			strReturn =  e.getMessage();
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
					//my_sched.getJ
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
