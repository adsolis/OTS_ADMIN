package com.datacode.avon_ots_admin.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.Map;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import com.datacode.avon_ots_admin.replicacion.Replicacion;
import com.datacode.avon_ots_admin.replicacion.ReplicacionData;

public class JobEnviarSmsPendientes {
	
	private ReplicacionData replicacionData = new ReplicacionData();
	private Integer v_TiempoEnviarInformacionSMS;
	
	public void iniciarJob() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		sched.start();

		JobDetail jd = newJob(TareaEnviarSmsPendientes.class).withIdentity(
				"JobEnviarSmsPendientes", "grupoEnviaSmsPendientes") // name
																		// "myJob",
																		// group
																		// "group1"
				.build();

		SimpleTrigger trigger = newTrigger()
				.withIdentity("triggerEnviaSmsPendientes",
						"grupoEnviaSmsPendientes")
				.startAt(new Date(System.currentTimeMillis() + 15000))
				// if a start time is not given (if this line were omitted),
				// "now" is implied
				.withSchedule(
						simpleSchedule().withIntervalInSeconds(Intervalo())
								.repeatForever()).forJob(jd).build();

		sched.scheduleJob(jd, trigger);
	}
	
	
	private Integer Intervalo() {
		Replicacion replicacion = new Replicacion();
		Map<String, String> parametros = replicacionData.obtieneParametros(replicacion);
		v_TiempoEnviarInformacionSMS = Integer.valueOf(parametros
				.get("SMSTiempoEnvioMensajes"));
		return v_TiempoEnviarInformacionSMS;
	}

	
}
