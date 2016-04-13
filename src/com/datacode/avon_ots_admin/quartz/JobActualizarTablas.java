package com.datacode.avon_ots_admin.quartz;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class JobActualizarTablas {
	
	public void iniciarJob()  throws Exception{
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		sched.start();
		
		
		JobDetail jd = newJob(TareaActualizacionTablas.class).withIdentity(
				"JobActualizarTablas", "grupoActualizacionTablas") // name "myJob", group "group1"
				.build();
		
		CronTrigger trigger = newTrigger().withIdentity("triggerActualizaTablas","grupoActualizacionTablas")
			    .withSchedule(cronSchedule("0 * * * * ?"))
			    .forJob("JobActualizarTablas","grupoActualizacionTablas")
			    .build();
		sched.scheduleJob(jd, trigger);
	}
	
}
