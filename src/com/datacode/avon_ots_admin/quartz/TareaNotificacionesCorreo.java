package com.datacode.avon_ots_admin.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TareaNotificacionesCorreo implements Job {

	public synchronized void execute(JobExecutionContext arg0) throws JobExecutionException {
		NotificacionesCorreo.generarNotificacionesCorreo();
	}

}
