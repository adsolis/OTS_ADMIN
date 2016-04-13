package com.datacode.avon_ots_admin.utils;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;

import org.apache.axis2.AxisFault;

import com.datacode.avon_ots_admin.model.ArchivoCorreo;
import com.datacode.avon_ots_ws.CorreoControllerStub;
import com.datacode.avon_ots_ws.CorreoControllerStub.Archivo;
import com.datacode.avon_ots_ws.CorreoControllerStub.DatosCorreo;

public class CorreoUtil {

	public static void enviarCorreo(String p_destinatarios, String p_asunto,
			List<ArchivoCorreo> p_adjuntos, String p_texto, int p_idLDC) throws ExcepcionNegocio {
		List<Archivo> archivosC = new ArrayList<Archivo>();
		if (p_adjuntos != null) {
			for (ArchivoCorreo archivoCo : p_adjuntos) {
				Archivo archivoRes = new Archivo();
				DataHandler data = new DataHandler(archivoCo.getBytes()
						.toByteArray(), archivoCo.getType());
				archivoRes.setArchivo(data);
				archivoRes.setNombre(archivoCo.getNombreArchivo());
				archivoRes.setTipoContenido(archivoCo.getType());
				archivosC.add(archivoRes);
			}
		}
		CorreoControllerStub stub;
		CorreoControllerStub.EnviarCorreoResponse response = null;
		CorreoControllerStub.ObtenerDatosCorreoCuentaMaestraResponse respuesta = null;
		DatosCorreo datosC = null;
		try {
			stub = new CorreoControllerStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			CorreoControllerStub.EnviarCorreo param = new CorreoControllerStub.EnviarCorreo();
			CorreoControllerStub.ObtenerDatosCorreoCuentaMaestra param2 = new CorreoControllerStub.ObtenerDatosCorreoCuentaMaestra();
			param2.setIdLDC(p_idLDC);
			respuesta = stub.obtenerDatosCorreoCuentaMaestra(param2);
			datosC = respuesta.get_return();
			param.setAsunto(p_asunto);
			param.setContrasenia(datosC.getPassword());
			param.setDe(datosC.getCuenta());
			param.setEsHtml(true);
			param.setPara(p_destinatarios);
			param.setPuerto(datosC.getPuerto());
			param.setHabilitaSsl(false);
			param.setServidorSmtp(datosC.getServidor());
			param.setTextoCorreo(p_texto);
			param.setUsuario(datosC.getUsuario());
			param.setAdjuntos(archivosC.toArray(new Archivo[0]));
			response = stub.enviarCorreo(param);
			if (response != null && response.get_return().equals("")) {
				CorreoControllerStub.RegistrarEnvioMail param3 = new CorreoControllerStub.RegistrarEnvioMail();
				param3.setAsunto(p_asunto);
				param3.setDe(datosC.getCuenta());
				param3.setIdllegadaProgramada(0);
				param3.setPara(p_destinatarios);
				param3.setTexto(p_texto);
				param3.setUsuario(datosC.getUsuario());
				stub.registrarEnvioMail(param3);
				System.out.println("Correo de notificacion con asunto " + p_asunto + " enviado exitosamente a las " + new Date());
				return;
			} else {
				try {
					System.err.println("Correo de notificacion con asunto " + p_asunto + " no se pudo enviar a las " + new Date());
					if (p_adjuntos != null && p_adjuntos.size() > 0) {
						Utils.encolarCorreos(1, datosC.getCuenta(), p_destinatarios, p_asunto, p_texto, 0, p_adjuntos.get(0), "");
					} else {
						Utils.encolarCorreos(1, datosC.getCuenta(), p_destinatarios, p_asunto, p_texto, 0, null, "");
					}
				} catch (Exception e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
				throw new ExcepcionNegocio("Error al obtener los datos de la cuenta maestra.", null);
			}
		} catch (AxisFault e) {
			System.err.println(e);
			throw new ExcepcionNegocio("Error al enviar el correo", e);
		} catch (RemoteException e) {
			System.err.println(e);
			throw new ExcepcionNegocio("Error al enviar el correo", e);
		}
	}

}
