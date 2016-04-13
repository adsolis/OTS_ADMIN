package com.datacode.avon_ots_admin.quartz;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.DisallowConcurrentExecution;

import com.datacode.avon_ots_admin.reports.EnvioReporteDejadosMail;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.CorreoControllerStub;
import com.datacode.avon_ots_ws.UtilsStub;
import com.datacode.avon_ots_ws.UtilsStub.Configuracion;

@DisallowConcurrentExecution
public class TareaMandarMailDejados implements Job {

	@Override
	public synchronized void execute(JobExecutionContext arg0) throws JobExecutionException {
		TareaNotificacionesAutomaticas.generarNotificacionesAutomaticas();
		
		String realPath1 = com.datacode.avon_ots_admin.quartz.ListenerJobs.pathRep;

		// String realPath1 =
		// com.datacode.avon_ots_admin.quartz.ListenerJobs.path;
		List<Integer> lista = obtieneListaLiquidaciones();
		if (lista.size() > 0) {
			EnvioReporteDejadosMail envio = new EnvioReporteDejadosMail();
			for (int i = 0; i < lista.size(); i++) {
				actualizaStatusListaLiquidaciones(lista.get(i), "P");
				String resultado = envio.generaReporteMail(ldcConf(),
						realPath1, lista.get(i));
				if (resultado.equals("")) {
					actualizaStatusListaLiquidaciones(lista.get(i), "E");
				} else {
					actualizaStatusListaLiquidaciones(lista.get(i), "N");
				}
			}
		}
		TareaEnviarReporteRepresentantesDomiciliosIncorrectos tarea = new TareaEnviarReporteRepresentantesDomiciliosIncorrectos();
		tarea.procesarEnvioDeCorreos();
	}

	private List<Integer> obtieneListaLiquidaciones() {
		List<Integer> lista = new ArrayList<Integer>();
		int[] arreglo = null;
		try {
			CorreoControllerStub Stub = new CorreoControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(Stub._getServiceClient()
					.getOptions().getTo().getAddress());
			Stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			CorreoControllerStub.ObtenerListaLiquidacionesMail param = new CorreoControllerStub.ObtenerListaLiquidacionesMail();
			CorreoControllerStub.ObtenerListaLiquidacionesMailResponse response = Stub
					.obtenerListaLiquidacionesMail(param);

			arreglo = response.get_return();
			if (arreglo != null) {
				for (int des : arreglo) {
					lista.add(des);
				}
			}
		} catch (AxisFault e) {
			Utils.GuardarLogMensajeBD("obtieneListaLiquidaciones", "M1",
					"No se pudo consultar la lista de liquidaciones",
					e.getMessage(), 1);
			e.printStackTrace();
		} catch (RemoteException e) {
			Utils.GuardarLogMensajeBD("obtieneListaLiquidaciones", "M1",
					"No se pudo consultar la lista de liquidaciones",
					e.getMessage(), 1);
			e.printStackTrace();
		}
		return lista;
	}

	private String actualizaStatusListaLiquidaciones(int idSalidaReparto,
			String statusNuevo) {

		String arreglo = "";
		try {
			CorreoControllerStub Stub = new CorreoControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(Stub._getServiceClient()
					.getOptions().getTo().getAddress());
			Stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			CorreoControllerStub.ActualizarStatusLiquidacionesMail param = new CorreoControllerStub.ActualizarStatusLiquidacionesMail();
			param.setIdSalidaReparto(idSalidaReparto);
			param.setStatusNuevo(statusNuevo);
			CorreoControllerStub.ActualizarStatusLiquidacionesMailResponse response = Stub
					.actualizarStatusLiquidacionesMail(param);

			arreglo = response.get_return();

		} catch (AxisFault e) {
			Utils.GuardarLogMensajeBD("actualizaStatusListaLiquidaciones",
					"M1", "No se pudo actualizar la lista de liquidaciones",
					e.getMessage(), 1);
			e.printStackTrace();
		} catch (RemoteException e) {
			Utils.GuardarLogMensajeBD("actualizaStatusListaLiquidaciones",
					"M1", "No se pudo actualizar la lista de liquidaciones",
					e.getMessage(), 1);
			e.printStackTrace();
		}
		return arreglo;
	}

	private int ldcConf() {
		int idLDC = 5;
		Configuracion arreglo = null;
		try {
			UtilsStub Stub = new UtilsStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(Stub._getServiceClient()
					.getOptions().getTo().getAddress());
			Stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			UtilsStub.CargarConfiguracion param = new UtilsStub.CargarConfiguracion();
			UtilsStub.CargarConfiguracionResponse response = Stub
					.cargarConfiguracion(param);

			arreglo = response.get_return();
			if (arreglo != null) {
				idLDC = arreglo.getIdLDC();
			}

		} catch (AxisFault e) {
			Utils.GuardarLogMensajeBD("ldcConf", "M1",
					"No se pudo consultar la configuracion del LDC",
					e.getMessage(), 1);
			e.printStackTrace();
		} catch (RemoteException e) {
			Utils.GuardarLogMensajeBD("ldcConf", "M1",
					"No se pudo consultar la confugracion del LDC",
					e.getMessage(), 1);
			e.printStackTrace();
		}
		return idLDC;
	}
}