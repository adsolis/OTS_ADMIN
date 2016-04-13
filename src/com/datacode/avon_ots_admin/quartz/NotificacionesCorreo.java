package com.datacode.avon_ots_admin.quartz;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.axis2.AxisFault;

import com.datacode.avon_ots_admin.model.ComboDTO;
import com.datacode.avon_ots_admin.utils.CorreoUtil;
import com.datacode.avon_ots_admin.utils.ExcepcionNegocio;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.NotificacionesCorreoStub;
import com.datacode.avon_ots_ws.NotificacionesCorreoStub.NotificacionesCorreoDTO;
import com.datacode.avon_ots_ws.UtilsStub;
import com.datacode.avon_ots_ws.UtilsStub.Configuracion;

public abstract class NotificacionesCorreo {

	private static final int ID_USUARIO = 1;

	public static synchronized void generarNotificacionesCorreo() {
		Date date = new Date();
		List<NotificacionesCorreoDTO> v_notificaciones = obtenerNotificacionesCorreoVencidas(ID_USUARIO);
		for (NotificacionesCorreoDTO v_notificacion : v_notificaciones) {
			if (validarHorarioNotificacion(v_notificacion, date)) {
				actualizarFechaUltimaEjecucion(v_notificacion, 1);
				if (v_notificacion.getDestinatarios() == null || "".equals(v_notificacion.getDestinatarios().trim())) {
					Utils.GuardarLogMensajeBD(
							"generarNotificacionesAutomaticas", "M1",
							"No existen destinatarios para la notificacion",
							"No existen destinatarios para la notificacion", ID_USUARIO);
				} else {
					String asunto = v_notificacion.getAsunto();
					List<String> v_proceduresStoredAsunto = evaluarTexto(v_notificacion.getAsunto());
					if (v_proceduresStoredAsunto != null && v_proceduresStoredAsunto.size() > 0) {
						for (String v_procedureAsunto : v_proceduresStoredAsunto) {
							String v_asunto = obtenerTablaStoredAsunto(v_procedureAsunto);
							if (v_asunto != null && !"".equals(v_asunto.trim())) {
								asunto = asunto.replace("[" + v_procedureAsunto + "]", v_asunto.trim());
							} else {
								asunto = asunto.replace("[" + v_procedureAsunto + "]", "");
							}
						}
					}
					v_notificacion.setAsunto(asunto);
					// Validamos procedures entre { }
					List<String> v_proceduresString = evaluarTextoString(v_notificacion.getCuerpo());
					String cuerpoFinal = v_notificacion.getCuerpo();
					for (String v_procedure : v_proceduresString) {
						String v_tabla1 = obtenerTablaStoredAsunto(v_procedure);
						cuerpoFinal = cuerpoFinal.replace("{" + v_procedure + "}", v_tabla1);
					}
					v_notificacion.setCuerpo(cuerpoFinal);
					// Validamos procedures entre [ ]
					List<ComboDTO> v_storedCuerpo = new ArrayList<ComboDTO>();
					List<String> v_procedures = evaluarTexto(v_notificacion.getCuerpo());
					for (String v_procedure : v_procedures) {
						ComboDTO v_tabla1 = obtenerTablaStoredCuerpo(v_procedure);
						if (!"0".equals(v_tabla1.getId())) {
							ComboDTO v_tabla2 = new ComboDTO();
							v_tabla2.setId(v_procedure);
							v_tabla2.setDescripcion(v_tabla1.getDescripcion());
							v_storedCuerpo.add(v_tabla2);
						}
					}
					if (v_storedCuerpo.size() > 0) {
						String texto = v_notificacion.getCuerpo();
						String textoCompleto = "";
						for (ComboDTO v_iter : v_storedCuerpo) {
							textoCompleto = texto.replace("[" + v_iter.getId() + "]", v_iter.getDescripcion());
						}
						try {
							CorreoUtil.enviarCorreo(v_notificacion.getDestinatarios(), v_notificacion.getAsunto(), null, textoCompleto, obtenerIdLDC());
							actualizarFechaUltimaEjecucion(v_notificacion, 1);
						} catch (ExcepcionNegocio e) {
							actualizarFechaUltimaEjecucion(v_notificacion, 0);
							System.err.println(e.getMensaje());
						}
					} else {
						actualizarFechaUltimaEjecucion(v_notificacion, 0);
					}
				}
			}
		}
	}

	private static synchronized boolean validarHorarioNotificacion(NotificacionesCorreoDTO p_notificacion, Date date) {
	    boolean isWithin = false;
	    try {
		    String d1 = p_notificacion.getInicioIntervaloEjecucion();
		    String d2 = p_notificacion.getFinIntervaloEjecucion();
		    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		    String dToTest = sdf.format(date);
		    boolean isSplit = false;
		    Date dt1 = null, dt2 = null,  dt3 = null;
		    dt1 = sdf.parse(d1);
		    dt2 = sdf.parse(d2);
		    dt3 = sdf.parse(dToTest);
		    isSplit = (dt2.compareTo(dt1) < 0);
		    if (isSplit) {
		    	isWithin = (dt3.compareTo(dt1) >= 0 || dt3.compareTo(dt2) <= 0);
		    } else {
		    	isWithin = (dt3.compareTo(dt1) >= 0 && dt3.compareTo(dt2) <= 0);
		    }
	    } catch (ParseException e) {
	    	isWithin = false;
	    }
	    return isWithin;
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

	private static synchronized List<String> evaluarTextoString(String p_texto) {
		List<String> v_procedures = new ArrayList<String>();
		String v_auxiliar = p_texto;
		Boolean v_aunHay = true;
		if (p_texto != null && p_texto.length() > 0) {
			while (v_aunHay) {
				if (v_auxiliar.length() > 0) {
					int v_inicio = v_auxiliar.indexOf("{");
					int v_fin = v_auxiliar.indexOf("}");
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

	private static synchronized List<NotificacionesCorreoDTO> obtenerNotificacionesCorreoVencidas(int p_idUsuario) {
		List<NotificacionesCorreoDTO> v_notificaciones = new ArrayList<NotificacionesCorreoDTO>();
		try {
			NotificacionesCorreoStub stub = new NotificacionesCorreoStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			NotificacionesCorreoStub.ObtenerNotificacionesCorreoVencidas param = new NotificacionesCorreoStub.ObtenerNotificacionesCorreoVencidas();
			NotificacionesCorreoStub.ObtenerNotificacionesCorreoVencidasResponse response = stub.obtenerNotificacionesCorreoVencidas(param);
			if (response.get_return() != null) {
				NotificacionesCorreoStub.NotificacionesCorreoDTO[] respuesta = response.get_return();
				if (respuesta != null) {
					for (NotificacionesCorreoStub.NotificacionesCorreoDTO iter : respuesta) {
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

	private static synchronized ComboDTO obtenerTablaStoredCuerpo(String stored) {
		ComboDTO c = new ComboDTO();
		try {
			NotificacionesCorreoStub stub = new NotificacionesCorreoStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			NotificacionesCorreoStub.ObtenerTablaStore param = new NotificacionesCorreoStub.ObtenerTablaStore();
			param.setIdUsuario(ID_USUARIO);
			param.setStored(stored);
			NotificacionesCorreoStub.ObtenerTablaStoreResponse response = stub.obtenerTablaStore(param);
			if (response.get_return() != null) {
				NotificacionesCorreoStub.NotificacionesCorreoDTO respuesta = response.get_return();
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

	private static synchronized String obtenerTablaStoredAsunto(String stored) {
		try {
			NotificacionesCorreoStub stub = new NotificacionesCorreoStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			NotificacionesCorreoStub.ObtenerStringStored param = new NotificacionesCorreoStub.ObtenerStringStored();
			param.setIdUsuario(ID_USUARIO);
			param.setStored(stored);
			NotificacionesCorreoStub.ObtenerStringStoredResponse response = stub.obtenerStringStored(param);
			return response.get_return();
		} catch (AxisFault e) {
			System.err.println(e.getMessage());
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
		}
		return "";
	}

	private static synchronized boolean actualizarFechaUltimaEjecucion(NotificacionesCorreoDTO p_notificacion, int p_actualizar) {
		boolean v_res = false;
		try {
			NotificacionesCorreoStub stub = new NotificacionesCorreoStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			NotificacionesCorreoStub.ActualizarFechaUltimaEjecucion param = new NotificacionesCorreoStub.ActualizarFechaUltimaEjecucion();
			param.setP_idUsuario(ID_USUARIO);
			param.setP_notificacionCorreo(p_notificacion);
			param.setP_actualizar(p_actualizar);
			NotificacionesCorreoStub.ActualizarFechaUltimaEjecucionResponse response = stub.actualizarFechaUltimaEjecucion(param);
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
