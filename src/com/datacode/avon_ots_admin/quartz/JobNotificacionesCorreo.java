package com.datacode.avon_ots_admin.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class JobNotificacionesCorreo {

	public void iniciarJob() {
		System.out.println("JobNotificacionesCorreo - iniciarJob()");
		try {
			SchedulerFactory sf1 = new StdSchedulerFactory();
			Scheduler sched1 = sf1.getScheduler();
			sched1.start();
			JobDetail jd1 = newJob(TareaNotificacionesCorreo.class).withIdentity(
					"myJobLlegProg1", "groupLlegProg1")
					.build();
			SimpleTrigger trigger = newTrigger()
					.withIdentity("JobLlegadaProgramada-1", "grupoNotificaLleg1")
					.startAt(new Date(System.currentTimeMillis() + 15000))
					.withSchedule(simpleSchedule().withIntervalInSeconds(60).repeatForever())
					.forJob(jd1)
					.build();
			sched1.scheduleJob(jd1, trigger);
		} catch (SchedulerException e) {
			System.err.println("SchedulerException: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
