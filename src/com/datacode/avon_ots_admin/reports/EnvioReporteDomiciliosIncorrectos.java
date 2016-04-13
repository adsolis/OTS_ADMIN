package com.datacode.avon_ots_admin.reports;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;

import org.apache.axis2.AxisFault;

import com.datacode.avon_ots_admin.model.ArchivoCorreo;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.CorreoControllerStub;
import com.datacode.avon_ots_ws.CorreoControllerStub.Archivo;
import com.datacode.avon_ots_ws.CorreoControllerStub.DatosCorreo;

public class EnvioReporteDomiciliosIncorrectos {

	public String mandarCorreo(List<ArchivoCorreo> archivos, int idLDC,
			String recipientes, String zona, String nombreReporte) {
		String error = "";
		List<Archivo> archivosC = new ArrayList<Archivo>();
		for (ArchivoCorreo archivoCo : archivos) {
			Archivo archivoRes = new Archivo();
			DataHandler data = new DataHandler(archivoCo.getBytes()
					.toByteArray(), archivoCo.getType());
			archivoRes.setArchivo(data);
			archivoRes.setNombre(archivoCo.getNombreArchivo());
			archivoRes.setTipoContenido(archivoCo.getType());
			archivosC.add(archivoRes);
		}

		CorreoControllerStub stub;
		CorreoControllerStub.EnviarCorreoResponse response = null;
		CorreoControllerStub.ObtenerDatosCorreoCuentaMaestraResponse respuesta = null;
		DatosCorreo datosC = null;
		String texto = "";
		String asunto = "";
		try {
			stub = new CorreoControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			CorreoControllerStub.EnviarCorreo param = new CorreoControllerStub.EnviarCorreo();
			CorreoControllerStub.ObtenerDatosCorreoCuentaMaestra param2 = new CorreoControllerStub.ObtenerDatosCorreoCuentaMaestra();
			param2.setIdLDC(idLDC);
			respuesta = stub.obtenerDatosCorreoCuentaMaestra(param2);
			datosC = respuesta.get_return();

			texto += "<br/><br/><br/>";
			texto += "Por medio del presente se le informa de los domicilios reportados como incorrectos en la Zona : "
					+ zona;
			texto += "<br/><br/>";
			texto += "Favor de indcicarle al LDC cuando haya sido atendido para que actualice el sistema y desaparezsca de esta lista,"
					+ " de lo contrario cada registro con \"domicilio incorrecto\""
					+ " seguirá considerándose en el presente correo.";
			texto += "<br/><br/>";
			texto += "Atte:" + datosC.getRazonSocial();

			asunto += "{OTS: " + datosC.getClavePorteo()
					+ "}: Envío de Reporte de Domicilios Incorrectos " + zona;
			param.setAsunto(asunto);
			param.setContrasenia(datosC.getPassword());
			param.setDe(datosC.getCuenta());
			param.setEsHtml(true);
			param.setPara(recipientes);
			param.setPuerto(datosC.getPuerto());
			param.setHabilitaSsl(false);// aqui hay que modificar
			param.setServidorSmtp(datosC.getServidor());
			param.setTextoCorreo(texto);
			param.setUsuario(datosC.getUsuario());
			param.setAdjuntos(archivosC.toArray(new Archivo[0]));
			response = stub.enviarCorreo(param);
			if (response != null && response.get_return().equals("")) {
				CorreoControllerStub.RegistrarEnvioMail param3 = new CorreoControllerStub.RegistrarEnvioMail();
				param3.setAsunto(asunto);
				param3.setDe(datosC.getCuenta());
				param3.setIdllegadaProgramada(0);
				param3.setPara(recipientes);
				param3.setTexto(texto);
				param3.setUsuario(datosC.getUsuario());
				CorreoControllerStub.RegistrarEnvioMailResponse res = stub
						.registrarEnvioMail(param3);
				if (res != null && res.get_return() > 0) {
					int idMail = res.get_return();
					CorreoControllerStub.RegistrarEnvioMailReporteSubbodega param4 = new CorreoControllerStub.RegistrarEnvioMailReporteSubbodega();
					param4.setIdCorreoEnviado(idMail);
					param4.setIdUsuario(1);
					CorreoControllerStub.RegistrarEnvioMailReporteSubbodegaResponse resReg = stub
							.registrarEnvioMailReporteSubbodega(param4);
					if (resReg != null && resReg.get_return() > 0) {

					} else {
						error = "Ocurrio un error al registrar el mail enviado a subodega";
						
					}
				} else {
					error = "Ocurrio un error al registrar el mail enviado";
					
				}
			} else {
				error = "No se pudo enviar el correo del Reporte de Domicilios Incorrectos";
				Utils.GuardarLogMensajeBD("UCS36_2", "clave", error, "", 1);
				if (archivos != null && archivos.size() > 0) {
					Utils.encolarCorreos(1, datosC.getCuenta(), recipientes,
							asunto, texto, 25, archivos.get(0), nombreReporte);
					
				}
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			error = "No se pudo enviar el correo del Reporte de Domicilios Incorrectos";
			Utils.GuardarLogMensajeBD("UCS36_2", "clave", e.getMessage(), "", 1);
			if (archivos != null && archivos.size() > 0 && datosC != null) {
				Utils.encolarCorreos(1, datosC.getCuenta(), recipientes,
						asunto, texto, 25, archivos.get(0), nombreReporte);
				
			}
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			error = "No se pudo enviar el correo del Reporte de Domicilios Incorrectos";
			Utils.GuardarLogMensajeBD("UCS36_2", "clave", e.getMessage(), "", 1);
			if (archivos != null && archivos.size() > 0 && datosC != null) {
				Utils.encolarCorreos(1, datosC.getCuenta(), recipientes,
						asunto, texto, 25, archivos.get(0), nombreReporte);
				
			}
			e.printStackTrace();
			
		}
		return error;
	}

}
