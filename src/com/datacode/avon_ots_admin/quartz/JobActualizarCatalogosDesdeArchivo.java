/**
 *
 *  @since 09/02/2012
 *
 */
package com.datacode.avon_ots_admin.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.SimpleScheduleBuilder.*;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author jessica.leon
 * 
 */
public class JobActualizarCatalogosDesdeArchivo {

	public void iniciarJobs() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		sched.start();

		JobDetail jd = newJob(TareaActualizarCatalogosDesdeArchivo.class)
				.withIdentity("JobActualizarCatalogosArchivo",
						"grupoActualizaArchivo") // name "myJob", group "group1"
				.build();

		SimpleTrigger trigger = newTrigger().withIdentity("triggerActualizaArchivo", "grupoActualizaArchivo")
				.startAt(new Date(System.currentTimeMillis() + 20000))
				.withSchedule(simpleSchedule()).forJob(jd).build();

		sched.scheduleJob(jd, trigger);
	}
}
