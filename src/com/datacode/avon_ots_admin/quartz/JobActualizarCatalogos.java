/**
 *
 *  @since 09/02/2012
 *
 */
package com.datacode.avon_ots_admin.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.datacode.avon_ots_admin.utils.Utils;

import static org.quartz.CronScheduleBuilder.*;

/**
 * @author jessica.leon
 *
 */
public class JobActualizarCatalogos {

	private String horaEjecucion1;
	private String horaEjecucion2;
	private String minutoEjecucion;
	
	private void obtenerTiemposEjecucion(){
		horaEjecucion1 = Utils.obtenerPropiedadArchivoConfig("horaPrimeraEjecucion");
		horaEjecucion2 = Utils.obtenerPropiedadArchivoConfig("horaSegundaEjecucion");
		minutoEjecucion = Utils.obtenerPropiedadArchivoConfig("minutos");
	}
	
	
	public void iniciarJobs()  throws Exception{
		
		obtenerTiemposEjecucion();
		String schedule = "0 " + minutoEjecucion + " " + horaEjecucion1+","+horaEjecucion2 + " ? * MON-FRI";
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		sched.start();
		
		
		JobDetail jd = newJob(TareaActualizarCatalogos.class).withIdentity(
				"JobActualizarCatalogos", "grupoActualizacion") // name "myJob", group "group1"
				.build();

		CronTrigger trigger = newTrigger().withIdentity("triggerActualiza","grupoActualizacion")
			    .withSchedule(cronSchedule(schedule))
			    .forJob("JobActualizarCatalogos","grupoActualizacion")
			    .build();
		
		//"0 0 7,19 ? * MON-FRI"
		//"0 30 10-13 ? * MON-FRI"
		//.withSchedule( dailyAtHourAndMinute(7,00))

		sched.scheduleJob(jd, trigger);
	}

}
