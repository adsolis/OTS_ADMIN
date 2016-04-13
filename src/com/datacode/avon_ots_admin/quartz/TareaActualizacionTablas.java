package com.datacode.avon_ots_admin.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.datacode.avon_ots_admin.replicacion.ReplicacionBajadaLDCController;
import com.datacode.avon_ots_admin.replicacion.ReplicacionLDCController;


@DisallowConcurrentExecution
public class TareaActualizacionTablas implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//Replicación Bajada
		ReplicacionBajadaLDCController bajadaLDCController = new ReplicacionBajadaLDCController();
		bajadaLDCController.replicacionBajadaLDC();
		//Replicación Subida
		ReplicacionLDCController replicacionLDCController = new ReplicacionLDCController();
		replicacionLDCController.replicacionLDC();
	}

}
