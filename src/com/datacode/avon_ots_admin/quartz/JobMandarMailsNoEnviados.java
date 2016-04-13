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

import com.datacode.avon_ots_admin.utils.Utils;

public class JobMandarMailsNoEnviados {

	public JobMandarMailsNoEnviados() {

	}

	public void iniciarJobs() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		sched.start();

		JobDetail jd = newJob(TareaMandarMailsNoEnviados.class).withIdentity(
				"myJob1", "group2") // name "myJob", group "group1"
				.build();
		String tiempoMin = Utils
				.obtenerParametroConfiguracionCentral("TiempoReenvioCorreoNoEnviado");
		int minutos = 30;
		try {
			minutos = Integer.parseInt(tiempoMin);
		} catch (NumberFormatException nfe) {

		}
		minutos = minutos * 60;

		SimpleTrigger trigger = newTrigger().withIdentity("trigger5", "group2")
				.startAt(new Date(System.currentTimeMillis() + 15000))
				// if a start time is not given (if this line were omitted),
				// "now" is implied
				.withSchedule(
						simpleSchedule().withIntervalInSeconds(minutos)
								.repeatForever())
				// .withSchedule(
				// simpleSchedule().withIntervalInSeconds(10)
				// .withRepeatCount(1)) // note that 10 repeats
				// will give a total of
				// 11 firings
				.forJob(jd) // identify job with handle to its JobDetail itself
				.build();

		sched.scheduleJob(jd, trigger);
	}

}