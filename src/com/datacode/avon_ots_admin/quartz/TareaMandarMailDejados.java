package com.datacode.avon_ots_admin.quartz;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.DisallowConcurrentExecution;

import com.datacode.avon_ots_admin.reports.ConsultaDatosReportes;
import com.datacode.avon_ots_admin.reports.EnvioReporteDejadosMail;
import com.datacode.avon_ots_admin.reports.GenerarReporteOrdenesDejadasRecolectadasPUP;
import com.datacode.avon_ots_admin.reports.model.ModelOrdenesDejadasRecolectadas;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.ActualizarStatusLiquidacionesMail;
import com.datacode.avon_ots_ws.ActualizarStatusLiquidacionesMailResponse;
import com.datacode.avon_ots_ws.CorreoControllerStub;
import com.datacode.avon_ots_ws.ObtenerListaLiquidacionesMailResponse;
import com.datacode.avon_ots_ws.UtilsStub;
import com.datacode.avon_ots_ws.UtilsStub.Configuracion;
import com.datacode.avon_ots_ws.model.xsd.LiquidacionRepartoDTO;

@DisallowConcurrentExecution
public class TareaMandarMailDejados implements Job {

	@Override
	public synchronized void execute(JobExecutionContext arg0) throws JobExecutionException {
		TareaNotificacionesAutomaticas.generarNotificacionesAutomaticas();
		
		String realPath1 = com.datacode.avon_ots_admin.quartz.ListenerJobs.pathRep;
		int ldcConf = ldcConf();
		
		// String realPath1 =
		// com.datacode.avon_ots_admin.quartz.ListenerJobs.path;
		List<LiquidacionRepartoDTO> lista = obtieneListaLiquidaciones();
		List<LiquidacionRepartoDTO> listaTemporal = filtrarLiquidacionesPorEstatus(lista, 1);
		
		if(listaTemporal != null && !listaTemporal.isEmpty())
			procesoEnviarReportesSubbodegas(listaTemporal, realPath1, ldcConf);
		
		listaTemporal = filtrarLiquidacionesPorEstatus(lista, 2);
		if(listaTemporal != null && !listaTemporal.isEmpty())
			procesoEnviarReportesOrdenesDejadasRecolectadas(listaTemporal, realPath1, ldcConf, "dejada");
		
		listaTemporal = filtrarLiquidacionesPorEstatus(lista, 3);
		if(listaTemporal != null && !listaTemporal.isEmpty())
		procesoEnviarReportesOrdenesDejadasRecolectadas(lista, realPath1, ldcConf, "recolectada");
		
		lista = null;
		listaTemporal = null;
		
		TareaEnviarReporteRepresentantesDomiciliosIncorrectos tarea = new TareaEnviarReporteRepresentantesDomiciliosIncorrectos();
		tarea.procesarEnvioDeCorreos();
	}

	/**
	 * Metodo con la recuperacion de liquidaciones en base al estatus del correo, se modifico el comportamiento de este para
	 * que regrese una lista de objetos y no una lista de enteros como el metodo abajo comentado
	 * @return List<LiquidacionRepartoDTO> lista de objetos con las liquidaciones recuperadas en base al estatus del correo
	 */
	private List<LiquidacionRepartoDTO> obtieneListaLiquidaciones() {
		List<LiquidacionRepartoDTO> lista = new ArrayList<LiquidacionRepartoDTO>();
		LiquidacionRepartoDTO[] arreglo = null;
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
			ObtenerListaLiquidacionesMailResponse response = Stub
					.obtenerListaLiquidacionesMail(param);

			arreglo = response.get_return();
			if (arreglo != null) {
				for (LiquidacionRepartoDTO des : arreglo) {
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
	
	/**
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
	 */

	public String actualizaStatusListaLiquidaciones(int idSalidaReparto,
			String statusNuevo, String tipoLiquidacion) {

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

			ActualizarStatusLiquidacionesMail param = new ActualizarStatusLiquidacionesMail();
			param.setIdSalidaReparto(idSalidaReparto);
			param.setStatusNuevo(statusNuevo);
			param.setTipoLiquidacion(tipoLiquidacion);
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
	
	/**
	 * Metodo para separar el proceso de envio de reporte de subbodegas
	 * @param lista
	 * @param realPath1
	 * @param ldcConf
	 */
	private void procesoEnviarReportesSubbodegas(List<LiquidacionRepartoDTO> lista, String realPath1, int ldcConf) {
		if (lista.size() > 0) {
			EnvioReporteDejadosMail envio = new EnvioReporteDejadosMail();
			for (LiquidacionRepartoDTO liquidacion: lista) {
				actualizaStatusListaLiquidaciones(liquidacion.getIdSalidaReparto(), "P", "1");
				String resultado = envio.generaReporteMail(ldcConf,
						realPath1, liquidacion.getIdSalidaReparto());
				if (resultado.equals("")) {
					actualizaStatusListaLiquidaciones(liquidacion.getIdSalidaReparto(), "E", "1");
				} else {
					actualizaStatusListaLiquidaciones(liquidacion.getIdSalidaReparto(), "N", "1");
				}
			}
		}
	}
	
	/**
	 * Metodo para separar el proceso de enviar los reportes de ordenes dejadas y recolectadas
	 * @param lista
	 * @param realPath1
	 * @param ldcConf
	 */
	private void procesoEnviarReportesOrdenesDejadasRecolectadas(List<LiquidacionRepartoDTO> lista, 
			String realPath1, int ldcConf, String tipoSubreporte) {
		ConsultaDatosReportes consulta = new ConsultaDatosReportes();
		GenerarReporteOrdenesDejadasRecolectadasPUP generadorReporte = new GenerarReporteOrdenesDejadasRecolectadasPUP();
		List<ModelOrdenesDejadasRecolectadas> ordenesDejadasRecolectadasReportes = null;
		Configuracion config = null;
		int estatus = 0;
		
		if(tipoSubreporte.equals("recolectada"))
			estatus = 1;
		
		try {
			ordenesDejadasRecolectadasReportes = 
					consulta.generarListaReportesOrdenesDejadasRecolectadas(lista, estatus, config.getIdUsuario());
			generadorReporte.generarReporteMail(ordenesDejadasRecolectadasReportes, tipoSubreporte, realPath1, ldcConf);
		} catch (AxisFault e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Metodo para filtrar las liquidaciones que esten pendientes por enviarse a su correo el reporte correspondiente
	 * @param lista
	 * @param tipoLiquidacion 1 si se trata de Subbodegas 2 si se trata de Ordendes Dejadas 3 para Ordenes Recolectadas
	 * @return
	 */
	private List<LiquidacionRepartoDTO> filtrarLiquidacionesPorEstatus(List<LiquidacionRepartoDTO> lista, int tipoLiquidacion) {
		List<LiquidacionRepartoDTO> listaTemporal = new ArrayList<LiquidacionRepartoDTO>();
		if(tipoLiquidacion==1) {
			for(LiquidacionRepartoDTO liquidacion: lista) {
				if(liquidacion.getEstatusCorreo().equals(null) || liquidacion.equals("") 
						|| liquidacion.equals("P") || liquidacion.equals("N"))
					listaTemporal.add(liquidacion);
			}
		}
		else if(tipoLiquidacion==2) {
			for(LiquidacionRepartoDTO liquidacion: lista) {
				if(liquidacion.getEstatusCorreoDejadasPUP().equals(null) || liquidacion.equals("") 
						|| liquidacion.equals("P") || liquidacion.equals("N"))
					listaTemporal.add(liquidacion);
			}
		}
		else {
			for(LiquidacionRepartoDTO liquidacion: lista) {
				if(liquidacion.getEstatusCorreoRecolectadasPUP().equals(null) || liquidacion.equals("") 
						|| liquidacion.equals("P") || liquidacion.equals("N"))
					listaTemporal.add(liquidacion);
			}
		}
		return listaTemporal;
	}
}