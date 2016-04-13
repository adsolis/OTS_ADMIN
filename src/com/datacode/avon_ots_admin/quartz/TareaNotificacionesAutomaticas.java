package com.datacode.avon_ots_admin.quartz;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.AxisFault;

import com.datacode.avon_ots_admin.model.ComboDTO;
import com.datacode.avon_ots_admin.utils.CorreoUtil;
import com.datacode.avon_ots_admin.utils.ExcepcionNegocio;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.NotificacionesAutomaticasStub;
import com.datacode.avon_ots_ws.UtilsStub;
import com.datacode.avon_ots_ws.NotificacionesAutomaticasStub.NotificacionesAutomaticasDTO;
import com.datacode.avon_ots_ws.UtilsStub.Configuracion;

public abstract class TareaNotificacionesAutomaticas {

	private static final int ID_USUARIO = 1;

	public static synchronized void generarNotificacionesAutomaticas() {
		List<NotificacionesAutomaticasDTO> v_notificaciones = obtenerNotificacionesVencidas(ID_USUARIO);
		for (NotificacionesAutomaticasDTO v_notificacion : v_notificaciones) {
			if (v_notificacion.getDestinatarios() == null || "".equals(v_notificacion.getDestinatarios().trim())) {
				Utils.GuardarLogMensajeBD(
						"generarNotificacionesAutomaticas", "M1",
						"No existen destinatarios para la notificacion",
						"No existen destinatarios para la notificacion", ID_USUARIO);
			} else {
				List<ComboDTO> v_tablas = new ArrayList<ComboDTO>();
				List<String> v_procedures = evaluarTexto(v_notificacion.getTexto());
				for (String v_procedure : v_procedures) {
					ComboDTO v_tabla1 = obtenerTablaStore(v_procedure);
					if (!"0".equals(v_tabla1.getId())) {
						ComboDTO v_tabla2 = new ComboDTO();
						v_tabla2.setId(v_procedure);
						v_tabla2.setDescripcion(v_tabla1.getDescripcion());
						v_tablas.add(v_tabla2);
					}
				}
				if (v_tablas.size() > 0) {
					String texto = v_notificacion.getTexto();
					String textoCompleto = "";
					for (ComboDTO v_iter : v_tablas) {
						textoCompleto = texto.replace("[" + v_iter.getId() + "]", v_iter.getDescripcion());
					}
					try {
						CorreoUtil.enviarCorreo(v_notificacion.getDestinatarios(), "Notificaciones AVON", null, textoCompleto, obtenerIdLDC());
						actualizarFechaUltimaEjecucion(v_notificacion);
					} catch (ExcepcionNegocio e) {
						System.err.println(e.getMensaje());
					}
				}
			}
		}
	}

	private static synchronized List<String> evaluarTexto(String p_texto) {
		List<String> v_procedures = new ArrayList<String>();
		String v_auxiliar = p_texto;
		Boolean v_aunHay = true;
		if (p_texto != null && p_texto.length() > 0) {
			while (v_aunHay) {
				if (v_auxiliar.length() > 0) {
					int v_inicio = v_auxiliar.indexOf("[");
					int v_fin = v_auxiliar.indexOf("]");
					if (v_inicio == -1 || v_fin == -1) {
						v_aunHay = false;
					} else {
						String v_procedure = v_auxiliar.substring(v_inicio + 1, v_fin);
						v_procedures.add(v_procedure);
						v_auxiliar = v_auxiliar.substring(v_fin + 1);
					}
				} else {
					v_aunHay = false;
				}
			}
		}
		return v_procedures;
	}

	private static synchronized List<NotificacionesAutomaticasDTO> obtenerNotificacionesVencidas(int p_idUsuario) {
		List<NotificacionesAutomaticasDTO> v_notificaciones = new ArrayList<NotificacionesAutomaticasDTO>();
		try {
			NotificacionesAutomaticasStub stub = new NotificacionesAutomaticasStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			NotificacionesAutomaticasStub.ObtenerNotificacionesVencidas param = new NotificacionesAutomaticasStub.ObtenerNotificacionesVencidas();
			NotificacionesAutomaticasStub.ObtenerNotificacionesVencidasResponse response = stub.obtenerNotificacionesVencidas(param);
			if (response.get_return() != null) {
				NotificacionesAutomaticasStub.NotificacionesAutomaticasDTO[] respuesta = response.get_return();
				if (respuesta != null) {
					for (NotificacionesAutomaticasStub.NotificacionesAutomaticasDTO iter : respuesta) {
						v_notificaciones.add(iter);
					}
				}
			}
		} catch (AxisFault e) {
			System.err.println(e.getMessage());
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
		}
		return v_notificaciones;
	}

	private static synchronized ComboDTO obtenerTablaStore(String stored) {
		ComboDTO c = new ComboDTO();
		try {
			NotificacionesAutomaticasStub stub = new NotificacionesAutomaticasStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			NotificacionesAutomaticasStub.ObtenerTablaStore param = new NotificacionesAutomaticasStub.ObtenerTablaStore();
			param.setIdUsuario(ID_USUARIO);
			param.setStored(stored);
			NotificacionesAutomaticasStub.ObtenerTablaStoreResponse response = stub.obtenerTablaStore(param);
			if (response.get_return() != null) {
				NotificacionesAutomaticasStub.NotificacionesAutomaticasDTO respuesta = response.get_return();
				if (respuesta != null) {
					c.setId(respuesta.getId());
					c.setDescripcion(respuesta.getDescripcion());
				}
			}
		} catch (AxisFault e) {
			System.err.println(e.getMessage());
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
		}
		return c;
	}

	private static synchronized boolean actualizarFechaUltimaEjecucion(NotificacionesAutomaticasDTO p_notificacion) {
		boolean v_res = false;
		try {
			NotificacionesAutomaticasStub stub = new NotificacionesAutomaticasStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			NotificacionesAutomaticasStub.ActualizarFechaUltimaEjecucion param = new NotificacionesAutomaticasStub.ActualizarFechaUltimaEjecucion();
			param.setP_idUsuario(ID_USUARIO);
			param.setP_notificacion(p_notificacion);
			NotificacionesAutomaticasStub.ActualizarFechaUltimaEjecucionResponse response = stub.actualizarFechaUltimaEjecucion(param);
			v_res = response.get_return();
		} catch (AxisFault e) {
			System.err.println(e.getMessage());
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
		}
		return v_res;
	}

	private static synchronized int obtenerIdLDC() {
		int idLDC = 5;
		Configuracion arreglo = null;
		try {
			UtilsStub Stub = new UtilsStub();
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
			System.err.println(e.getMessage());
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
		}
		return idLDC;
	}

}
