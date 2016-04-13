package com.datacode.avon_ots_admin.quartz;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;

import org.apache.axis2.AxisFault;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.datacode.avon_ots_admin.model.ArchivoCorreo;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.CorreoControllerStub;
import com.datacode.avon_ots_ws.CorreoControllerStub.Archivo;
import com.datacode.avon_ots_ws.CorreoControllerStub.DatosCorreo;
import com.datacode.avon_ots_ws.CorreoControllerStub.EliminarCorreoNoEnviadoResponse;
import com.datacode.avon_ots_ws.CorreoControllerStub.ModelCorreoNoEnviado;
import com.datacode.avon_ots_ws.UtilsStub;
import com.datacode.avon_ots_ws.UtilsStub.Configuracion;

public class TareaMandarMailsNoEnviados implements Job {
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		if (Utils.comprobarConexionInternet()) {

			ModelCorreoNoEnviado[] correos = obtenerCorreosNoEnviados();
			if (correos != null && correos.length > 0) {
				for (ModelCorreoNoEnviado c : correos) {

					List<ArchivoCorreo> archivos = new ArrayList<ArchivoCorreo>();
					ArchivoCorreo archivo = obtenerArchivoCorreo(
							c.getIdCorreoPendiente(), c.getNombreArchivo());
					if (archivo != null) {
						archivos.add(archivo);
					}
					String resultado = enviarCorreoNoEnviado(archivos,
							ldcConf(), c.getPara(), c.getTexto(),
							c.getAsunto(), c.getIdCorreoPendiente());
					if (resultado.equals("")) {
						// esporque no ocurrio ningun error, borramos el archivo
						try {
							File fichero = new File(archivo.getNombreBorrar());
							if (fichero.delete()) {
								
							} else {
								
							}
						} catch (Exception e) {
							
						}
					}

				}
			} else {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

				
				Utils.GuardarLogMensajeBD("UCS001_3", "clave",
						df.format(new Date()) + "Envio de Correos No enviados:"
								+ "No hay correos pendientes de enviar", "", 1);
			}
		} else {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			System.err
					.println(df.format(new Date())
							+ "Envio de Correos No enviados:"
							+ "No se pudo enviar los correos porque no hay conexion a internet");
			Utils.GuardarLogMensajeBD(
					"UCS001_3",
					"clave",
					df.format(new Date())
							+ "Envio de Correos No enviados:"
							+ "No se pudo enviar los correos porque no hay conexion a internet",
					"", 1);

		}
	}

	private ArchivoCorreo obtenerArchivoCorreo(int idCorreoNoEnviado,
			String nombreReporte) {
		ArchivoCorreo a = new ArchivoCorreo();
		String extension = "";
		String type = "";
		String ruta = Utils.obtenerParametroRuta();
		String id = "" + idCorreoNoEnviado;
		File file = null;
		if (new File(ruta + nombreReporte).exists()) {

			file = new File(ruta + nombreReporte);

		}
		if (file != null) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];

				for (int readNum; (readNum = fis.read(buf)) != -1;) {
					bos.write(buf, 0, readNum); // no doubt here is 0

				}
				a.setBytes(bos);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					if (e.getMessage() != null) {
						System.err.println("Error al cerrar el fis"
								+ e.getMessage());
					}
					e.printStackTrace();
				}
			}
			a.setNombreArchivo(nombreReporte);
			String ext = nombreReporte.substring(nombreReporte.length() - 3,
					nombreReporte.length());

			if ("pdf".equals(ext)) {
				type = "application/pdf";
			} else if ("xls".equals(ext)) {
				type = "application/vnd.ms-excel";
			} else if ("csv".equals(ext)) {
				type = "text/csv";
			}
			a.setType(type);
			a.setNombreBorrar(ruta + nombreReporte);
		} else {
			a = null;
		}
		return a;
	}

	private ModelCorreoNoEnviado[] obtenerCorreosNoEnviados() {
		ModelCorreoNoEnviado[] arreglo = null;
		try {
			CorreoControllerStub Stub = new CorreoControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(Stub._getServiceClient()
					.getOptions().getTo().getAddress());
			Stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			CorreoControllerStub.ObtenerCorreosNoEnviados param = new CorreoControllerStub.ObtenerCorreosNoEnviados();
			CorreoControllerStub.ObtenerCorreosNoEnviadosResponse response = Stub
					.obtenerCorreosNoEnviados(param);

			arreglo = response.get_return();

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arreglo;
	}

	public String enviarCorreoNoEnviado(List<ArchivoCorreo> archivos,
			int idLDC, String recipientes, String textoMail, String asuntoMail,
			int idCorreoNoEnviado) {

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
			String asunto = asuntoMail;
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

			if (response != null && response.get_return().equals("")) {

				// si todo salio ok eliminamos de la tabla
				CorreoControllerStub.EliminarCorreoNoEnviado pamae = new CorreoControllerStub.EliminarCorreoNoEnviado();
				pamae.setIdCorreoPendiente(idCorreoNoEnviado);
				EliminarCorreoNoEnviadoResponse res3 = stub
						.eliminarCorreoNoEnviado(pamae);
				boolean exito = res3.get_return();

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idLDC;
	}

}