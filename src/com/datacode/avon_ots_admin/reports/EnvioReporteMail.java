package com.datacode.avon_ots_admin.reports;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;

import org.apache.axis2.AxisFault;

import com.datacode.avon_ots_admin.model.ArchivoCorreo;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.CorreoControllerStub;
import com.datacode.avon_ots_ws.CorreoControllerStub.Archivo;
import com.datacode.avon_ots_ws.CorreoControllerStub.DatosCorreo;

public class EnvioReporteMail {
	/**
	 * 
	 * @param archivos
	 *            lista de archivos adjuntos que se van a enviar en el mail
	 * @param idLDC
	 *            id del LDC de donde se va a obtener la cuenta maestra del mail
	 * @param recipientes
	 *            String de recipientes a los que se va a enviar el mail, debe
	 *            ser en el formato: mail@dominio,mail@dominio
	 * @param textoMail
	 *            String del texto que se va a enviar en el mail, el metodo ya
	 *            agrega la razon social
	 * @param asuntoMail
	 *            String asunto del mail, el metodo ya agrega OTS y el id del
	 *            usuario
	 * @return String vacio si no ocurrió ningun error, String con el error en
	 *         caso contrario
	 */
	public String enviarReporteMail(List<ArchivoCorreo> archivos, int idLDC,
			String recipientes, String textoMail, String asuntoMail,
			int IdUsuario, int idReporte, String nombreReporte) {
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
			DatosCorreo datosC = respuesta.get_return();
			String texto = "";
			texto += "<br/><br/><br/>";
			texto += textoMail;
			texto += "<br/><br/>";
			texto += "Atte:" + datosC.getRazonSocial();
			texto += "<br/>";
			String asunto = "";
			asunto += asuntoMail;
			param.setAsunto(asunto);
			param.setContrasenia(datosC.getPassword());
			param.setDe(datosC.getCuenta());
			param.setEsHtml(true);
			param.setPara(recipientes);
			param.setPuerto(datosC.getPuerto());
			param.setHabilitaSsl(datosC.getHabilitaSSL());
			param.setServidorSmtp(datosC.getServidor());
			param.setTextoCorreo(texto);
			param.setUsuario(datosC.getUsuario());
			param.setAdjuntos(archivosC.toArray(new Archivo[0]));
			response = stub.enviarCorreo(param);
			String regreso = response.get_return();
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

				} else {
					error = "Ocurrio un error al registrar el mail enviado";
				}
			} else {
				error = "Ocurrio un error al enviar el mail";
				if (archivos != null && archivos.size() > 0) {
					String resEncolar = Utils.encolarCorreos(IdUsuario,
							datosC.getCuenta(), recipientes, asunto, texto,
							idReporte, archivos.get(0), nombreReporte);
					if (resEncolar.length() > 0) {
						
					}
				}
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return error;
	}

}
