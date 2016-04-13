package com.datacode.avon_ots_admin.quartz;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.ActualizacionCatalogosStub;

/**
 * @author jessica.leon
 * 
 */
public class TareaActualizarCatalogos implements Job, Runnable {

	private int idUsuario;

	/**
	 * 
	 */
	public TareaActualizarCatalogos(int idUsuario){
		this.idUsuario = 20;
		checarUsuario(idUsuario);
	}

	public TareaActualizarCatalogos() {
		this.idUsuario = 20;
	}

	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		List<Integer> listaEjecucion = null;
		listaEjecucion = listarEjecucionParametrosCarga();
		Integer idParamCarga = 0;

		try {

			for (int i = 0; i < listaEjecucion.size(); i++) {
				idParamCarga = listaEjecucion.get(i);

				System.err.print("Iniciando actualizacion de catalogo--> "
						+ idParamCarga + "\n");

				actualizarCatalogo(idParamCarga);

				System.err
						.print("Finalizacion de actualización de catalogo--> "
								+ idParamCarga + "\n");
			}
		} catch (Exception ex) {
			Utils.GuardarLogMensajeBD("TareaActualizarCatalogos", "M1",
					"Ocurrió un error al ejecutar la tarea de idParamCarga: "
							+ idParamCarga, ex.toString(), 1);
		}
	}

	private List<Integer> listarEjecucionParametrosCarga() {
		List<Integer> listaParametrosCarga = new ArrayList<Integer>();

		listaParametrosCarga.add(1);
		listaParametrosCarga.add(3);
		listaParametrosCarga.add(4);
		listaParametrosCarga.add(7);
		listaParametrosCarga.add(8);
		listaParametrosCarga.add(10);
		listaParametrosCarga.add(9);
		listaParametrosCarga.add(6);
		listaParametrosCarga.add(5);

		return listaParametrosCarga;
	}

	public void ejecutarTareaActualizacionDesdeAdmin() {

		//this.idUsuario = 20;
		// checarUsuario(idUsuario);

		try {
			execute(null);
		} catch (JobExecutionException e) {
			Utils.GuardarLogMensajeBD(
					"TareaActualizarCatalogos",
					"M1",
					"Ocurrió un error al ejecutar la tarea actualización de catalogos desde ADMIN",
					e.toString(), this.idUsuario);
			e.printStackTrace();
		}
	}

	private void actualizarCatalogo(Integer idParamCarga) {

		try {

			ActualizacionCatalogosStub customer = new ActualizacionCatalogosStub();
			String url = Utils.modificarUrlServicioWeb(customer
					._getServiceClient().getOptions().getTo().getAddress());
			customer._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			customer._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(10800000);

			ActualizacionCatalogosStub.RealizarActualizacionCatalogo actualizacionRequest = new ActualizacionCatalogosStub.RealizarActualizacionCatalogo();

			// Cargamos configuración para obtener el IdLDC
			Configuracion config = new Configuracion();
			config.CargarConfiguracion();

			actualizacionRequest.setIdParamCarga(idParamCarga);
			actualizacionRequest.setIdLDC(config.getIdLDC());
			actualizacionRequest.setIdUser(idUsuario);
			customer.realizarActualizacionCatalogo(actualizacionRequest);

		} catch (RemoteException ex) {
			Utils.GuardarLogMensajeBD("TareaActualizarCatalogos", "M1",
					"Ocurrió un error al ejecutar la tarea de idParamCarga: "
							+ idParamCarga, ex.toString(), 1);
		}
	}

	private void checarUsuario(int idUsuario) {

		if (this.idUsuario != idUsuario) {
			this.idUsuario = idUsuario;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		ejecutarTareaActualizacionDesdeAdmin();
	}
}
