package com.datacode.avon_ots_admin.quartz;

import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.JobBuilder.*;
import java.util.Date;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class JobMandarMailDejados {
	
	
	public JobMandarMailDejados() {
		
	}
	
	public void iniciarJobs()  throws Exception{
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		sched.start();

		JobDetail jd = newJob(TareaMandarMailDejados.class).withIdentity(
				"myJob", "group1") // name "myJob", group "group1"
				.build();

		SimpleTrigger trigger = newTrigger()
				.withIdentity("trigger3", "group1")
				.startAt(new Date(System.currentTimeMillis() + 15000))
				// if a start time is not given (if this line were omitted),
				// "now" is implied
				.withSchedule(
						simpleSchedule().withIntervalInSeconds(180).repeatForever()
								)
//				.withSchedule(
//						simpleSchedule().withIntervalInSeconds(10)
//								.withRepeatCount(1)) // note that 10 repeats
														// will give a total of
														// 11 firings
				.forJob(jd) // identify job with handle to its JobDetail itself
				.build();

		sched.scheduleJob(jd, trigger);
	}

	
}