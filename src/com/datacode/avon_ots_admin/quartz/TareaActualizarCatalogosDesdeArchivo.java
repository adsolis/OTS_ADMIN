/**
 *
 *  @since 09/02/2012
 *
 */
package com.datacode.avon_ots_admin.quartz;

import java.rmi.RemoteException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.ActualizacionCatalogosDesdeArchivoStub;

/**
 * @author jessica.leon
 * 
 */
public class TareaActualizarCatalogosDesdeArchivo implements Job {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		ActualizacionCatalogosDesdeArchivoStub customer = null;
		ActualizacionCatalogosDesdeArchivoStub.IniciarActualizacionViaMonitoreo actualizacion = null;

		try {
			customer = new ActualizacionCatalogosDesdeArchivoStub();

			String url = Utils.modificarUrlServicioWeb(customer
					._getServiceClient().getOptions().getTo().getAddress());
			customer._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			actualizacion = new ActualizacionCatalogosDesdeArchivoStub.IniciarActualizacionViaMonitoreo();
			customer.iniciarActualizacionViaMonitoreo(actualizacion);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
