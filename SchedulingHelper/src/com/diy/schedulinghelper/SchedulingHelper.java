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

public class SchedulingHelper {

	Scheduler my_sched;
	Logger log = LoggerFactory.getLogger(SchedulingHelper.class);    
	
	public static void main(String[] args) {
		try {
			SchedulingHelper sh = new SchedulingHelper();
			sh.startScheduler();
			sh.scheduleWSCall(new Date(), true, "test");
			Thread.sleep(10000L);
			sh.getScheduledJobsStats();
			Thread.sleep(60000L);
			sh.stopScheduler();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String startScheduler() {
		String strReturn = null;
		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			my_sched = sf.getScheduler();
			my_sched.start();
			log.info("sched was started");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return strReturn;		
	}
	
	public String stopScheduler() {
		String strReturn = null;
		try {
			if (my_sched!=null) {
				my_sched.shutdown(true);
				log.info("sched was stopped");
			} else {
				log.info("sched was down");
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return strReturn;		
	}
	
	public String scheduleWSCall(
			Date d,
			Boolean bRecurent,
			String strSOAPMessage
	) {
		String strReturn = null;
		try {
			Date runTime = DateBuilder.evenMinuteDate(new Date());
			if (my_sched!=null) {
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
				log.info(job.getKey() + " will run at: " + runTime);
			} else {
				log.info("sched was down");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strReturn;		
	}
	
	public String getScheduledJobsStats() {
		String strReturn = null;
		log.info("stats requested");
		try {
			if (my_sched!=null) {
				List<String> strGroupsList = my_sched.getJobGroupNames();
				ListIterator<String> liGroups = strGroupsList.listIterator();
				if (strGroupsList.isEmpty()) {
					log.info("no groups");
				}
				while(liGroups.hasNext()) {
					log.info("Group: "+liGroups.next());					
				}
			} else {
				log.info("sched was down");
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return strReturn;
	}

}
