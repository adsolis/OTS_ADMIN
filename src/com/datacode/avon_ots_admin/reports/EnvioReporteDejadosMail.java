package com.datacode.avon_ots_admin.reports;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;

import org.apache.axis2.AxisFault;

import com.datacode.avon_ots_admin.model.ArchivoCorreo;
import com.datacode.avon_ots_admin.reports.model.ModelOrdenesDejadasRecolectadas;
import com.datacode.avon_ots_admin.reports.model.ModelRepMailDejadosCajas;
import com.datacode.avon_ots_admin.reports.model.ModelRepMailDejadosPremios;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.CorreoControllerStub;
import com.datacode.avon_ots_ws.CorreoControllerStub.Archivo;
import com.datacode.avon_ots_ws.CorreoControllerStub.DatosCorreo;
import com.datacode.avon_ots_ws.ReportesControllerStub.DestinatarioReporte;
import com.datacode.avon_ots_ws.ReportesControllerStub.ItemSubBodega;
import com.datacode.avon_ots_ws.ReportesControllerStub.SubBodegaAlmacen;
import com.datacode.avon_ots_ws.UtilsStub.Configuracion;

public class EnvioReporteDejadosMail {

	public String generaReporteMail(int idLDC, String realPath,
			int idSalidaReparto) {
		try {
			ConsultaDatosReportes consulta = new ConsultaDatosReportes();
			List<ArchivoCorreo> archivos = new ArrayList<ArchivoCorreo>();
			SubBodegaAlmacen[] subbodegas = consulta
					.obtenerSubodegas(idSalidaReparto);
			if (subbodegas != null) {
				for (SubBodegaAlmacen subbodega : subbodegas) {
					ItemSubBodega[] items = consulta
							.obtenerListaItemsSubBodega(
									subbodega.getIdSubbodegaAlmacen(),
									idSalidaReparto);
					List<ArchivoCorreo> archivosCajas = null;
					// List<ArchivoCorreo> archivosPremios = null;
					// try {
					Configuracion configuracion = Utils.obtenerConfiguracionLDC();
					
					archivosCajas = llenarModelosCajas(items,
							subbodega.getIdSubbodegaAlmacen(), "XLS", realPath);
					for (ArchivoCorreo archivo : archivosCajas) {
						// OutputStream outputfile = new FileOutputStream(new
						// File(
						// "C:/excel/" + archivo.getNombreArchivo() + ".xls"));
						// outputfile.write(archivo.getBytes().toByteArray());
						String nombre=Utils.generarNombreArchivoCorreo("Reporte de Liquidacion de Reparto", configuracion.getClaveLDC(), "xls");
						archivo.setNombreArchivo(nombre);
						archivo.setType("application/x-excel");
						// outputfile.close();
					}

					// archivosPremios = llenarModelosPremios(items,
					// subbodega.getIdSubbodegaAlmacen(), "XLS", realPath);
					// for (ArchivoCorreo archivo : archivosPremios) {
					// OutputStream outputfile = new FileOutputStream(new File(
					// "E:/excel/" + archivo.getNombreArchivo() + ".xls"));
					// outputfile.write(archivo.getBytes().toByteArray());
					//
					// archivo.setType("application/x-excel");
					//
					// outputfile.close();
					// }
					archivos.addAll(archivosCajas);
					// archivos.addAll(archivosPremios);

					// }
					/*
					 * catch (IOException e) { // TODO Auto-generated catch
					 * block e.printStackTrace();
					 * Utils.GuardarLogMensajeBD("EnvioReporteDejadosSubbodega",
					 * "M1",
					 * "Ocurrió un error el enviar reporte de Pedidos Dejados",
					 * e.getMessage() + e.getStackTrace(), 1); }
					 */

				}

				/*
				 * List<DestinatarioReporte> destinatarios = consulta
				 * .obtenerDestinatariosReporte(8);
				 */
				List<DestinatarioReporte> destinatarios = consulta
						.obtenerDestinatariosReportePorTipoReporte(
								"Reporte de Liquidación de Reparto", 0, 1);
				String recipientes = "";
				int cont = 0;
				for (DestinatarioReporte destinatario : destinatarios) {
					if (cont == 0) {
						recipientes = recipientes + destinatario.getMail();
					} else {
						recipientes = recipientes + ","
								+ destinatario.getMail();
					}
					cont++;
				}
			
				String resultado = mandarArchivosCorreo(archivos, idLDC,
						recipientes, "Reporte de Liquidación de Reparto");
				if (resultado != null && resultado.equals("")) {
					

					// si llega aqui es porque se envio correctamente el mail,
					// ahora se
					// inserta en las tablas de
				} else {
					Utils.GuardarLogMensajeBD(
							"EnvioReporteDejadosSubbodega",
							"M1",
							"Ocurrió un error el enviar reporte de Pedidos Dejados",
							resultado, 1);
					// ocurrio un problema enviando el mail
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			Utils.GuardarLogMensajeBD("EnvioReporteDejadosSubbodega", "M1",
					"Ocurrió un error el enviar reporte de Pedidos Dejados",
					e.getMessage() + e.getStackTrace(), 1);
		}
		return "";
	}

	public String mandarArchivosCorreo(List<ArchivoCorreo> archivos,
			int idLDC, String recipientes, String nombreReporte) {
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
			Map<String, String> mapa = generarCuerpoAsuntoCorreo(nombreReporte, datosC.getRazonSocial());
			String texto = mapa.get("cuerpo");
			String asunto = mapa.get("asunto");
			
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
				if(nombreReporte.equals("Reporte de Liquidación de Reparto")) {
					if (res != null && res.get_return() > 0) {
						int idMail = res.get_return();
						CorreoControllerStub.RegistrarEnvioMailReporteSubbodega param4 = new CorreoControllerStub.RegistrarEnvioMailReporteSubbodega();
						param4.setIdCorreoEnviado(idMail);
						param4.setIdUsuario(1);
						CorreoControllerStub.RegistrarEnvioMailReporteSubbodegaResponse resReg = stub
								.registrarEnvioMailReporteSubbodega(param4);
						if (resReg != null && resReg.get_return() > 0) {
							// for (ArchivoCorreo archivo : archivos) {
							// int cont = 0;
							// String itemsS = "";
							// for (int idItem : archivo.getListaItems()) {
							// if (cont == 0) {
							// itemsS += "" + idItem;
							// } else {
							// itemsS += "," + idItem;
							// }
							// cont++;
							// }
							// //se comenta porque no se tiene el idcampaña para
							// actualizar los items
							// /*CorreoControllerStub.ActualizaEnviadoReporteSubBodega
							// param5 = new
							// CorreoControllerStub.ActualizaEnviadoReporteSubBodega();
							// param5.setListaItems(itemsS);
							// param5.setIdReporteMailDejadoSubBodega(resReg
							// .get_return());
							// param5.setIdCampania(archivo.getIdCampania());
							// CorreoControllerStub.ActualizaEnviadoReporteSubBodegaResponse
							// resAct = stub
							// .actualizaEnviadoReporteSubBodega(param5);
							// if (resAct != null
							// && resAct.get_return().equals("")) {
							//
							// } else {
							// error =
							// "Ocurrio un error al actualizar los items del mail enviado a subbodga";
							// }*/
							// }

						} else {
							error = "Ocurrio un error al registrar el mail enviado a subodega";
							
						}
					} else {
						error = "Ocurrio un error al registrar el mail enviado";
						
					}
				}
			} else {
				error = "Ocurrio un error al enviar el mail";
				if (archivos != null && archivos.size() > 0) {
					Utils.encolarCorreos(1, datosC.getCuenta(), recipientes,
							asunto, texto, 9, archivos.get(0), nombreReporte);
					
				}
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error: " + e.getMessage());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error: " + e.getMessage());
		}
		return error;
	}
	
	/**
	 * Metodo que general el cuerpo del correo dependiendo del tipo de reporte que se va a enviar
	 * @param tipoReporte
	 * @param razonSocial
	 * @return
	 */
	private Map<String, String> generarCuerpoAsuntoCorreo(String tipoReporte, String razonSocial) {
		StringBuilder cuerpoCorreo = new StringBuilder();
		StringBuilder asunto = new StringBuilder();
		Map<String, String> mapaCuerpoAsunto = new HashMap<String, String>();
		if(tipoReporte.equals("Reporte de Liquidación de Reparto")) {
			cuerpoCorreo.append("<br/><br/><br/>");
			cuerpoCorreo.append("A través del presente se adjunta las órdenes, premios e inventario que le fueron dejados en su sub bodega:");
			cuerpoCorreo.append("<br/><br/>");
			cuerpoCorreo.append("Atte:");
			cuerpoCorreo.append(razonSocial);
			asunto.append("OTS_").append(razonSocial).append(": Envío de relación de mercancía dejada para su entrega");
			mapaCuerpoAsunto.put("cuerpo", cuerpoCorreo.toString());
			mapaCuerpoAsunto.put("asunto", asunto.toString());
		}
		else if(tipoReporte.equals("Ordenes Dejadas en PUPs")) {
			cuerpoCorreo.append("<br/><br/><br/>");
			cuerpoCorreo.append("A través del presente se adjunta las Órdenes, Premios e Inventario que le fueron dejados en PUP para su entrega.");
			cuerpoCorreo.append("<br/><br/>");
			cuerpoCorreo.append("Atte: ");
			cuerpoCorreo.append(razonSocial);
			asunto.append("OTS_").append(razonSocial).append(": Envío de relación de mercancía dejada en PUP para su entrega");
			mapaCuerpoAsunto.put("cuerpo", cuerpoCorreo.toString());
			mapaCuerpoAsunto.put("asunto", asunto.toString());
		}
        else if(tipoReporte.equals("Ordenes Recolectadas en PUPs")) {
        	cuerpoCorreo.append("<br/><br/><br/>");
			cuerpoCorreo.append("A través del presente se adjunta las Órdenes, Premios e Inventario que le fueron recolectados de PUP para su entrega.");
			cuerpoCorreo.append("<br/><br/>");
			cuerpoCorreo.append("Atte: ");
			cuerpoCorreo.append(razonSocial);
			asunto.append("OTS_").append(razonSocial).append(": Envío de relación de mercancía recolectada en PUP para su entrega");
			mapaCuerpoAsunto.put("cuerpo", cuerpoCorreo.toString());
			mapaCuerpoAsunto.put("asunto", asunto.toString());
		}
		
		return mapaCuerpoAsunto;
	}

	private List<ArchivoCorreo> llenarModelosCajas(ItemSubBodega[] items,
			int idSubBodega, String formato, String realPath)
			throws IOException {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
		List<ModelRepMailDejadosCajas> dejados = new ArrayList<ModelRepMailDejadosCajas>();
		List<ArchivoCorreo> archivos = new ArrayList<ArchivoCorreo>();
		JasperGenerator generador = new JasperGenerator();
		int cont = 1;
		List<Integer> idsItems = new ArrayList<Integer>();
		String zona = "", campania = "";
		for (ItemSubBodega item : items) {
			// if (item.getTipoArchivo().equals("O")) {
			ModelRepMailDejadosCajas res = new ModelRepMailDejadosCajas();
			res.setAccount(item.getAccount());
			res.setCampania(item.getCampania());
			res.setCantidad(1);
			res.setCodigo(item.getCodigo());
			res.setDescripcion(item.getDescripcion());
			res.setMontoCobrar(item.getMontoCobrar());
			res.setOrden(item.getOrden());
			res.setTipoItem(item.getTipoItem());
			res.setZona(item.getZona());
			res.setCont(cont);
			res.setQuantityToCollect(item.getQuantityToCollect());
			res.setCollectedQuantity(item.getCollectedQuantity());
			idsItems.add(item.getIdItem());

			if (!zona.equals(item.getZona())
					|| !campania.equals(item.getCampania())) {
				if (!zona.equals("")) {

					ArchivoCorreo archivo = new ArchivoCorreo();
					archivo.setNombreArchivo(zona + "_" + idSubBodega + "_"
							+ simpleDateformat.format(new Date()) + "_"
							+ campania + "_" + item.getTipoArchivo() + ".xls");
					archivo.setBytes(generador.generaReporteEmailDejadosCajas(
							dejados, formato, realPath));
					archivo.setIdCampania(Integer.parseInt(campania));
					archivo.setType("excel");
					archivo.setListaItems(idsItems);
					archivos.add(archivo);
					idsItems = new ArrayList<Integer>();
					dejados = new ArrayList<ModelRepMailDejadosCajas>();
					cont = 1;
					res.setCont(cont);
					dejados.add(res);
					zona = item.getZona();
					campania = item.getCampania();

				} else {
					dejados.add(res);
					zona = item.getZona();
					campania = item.getCampania();
					cont++;
				}
			} else {
				dejados.add(res);
				cont++;
			}

			// }
		}
		ArchivoCorreo archivo = new ArchivoCorreo();
		archivo.setNombreArchivo(zona + "_" + idSubBodega + "_"
				+ simpleDateformat.format(new Date()) + "_" + campania + "_"
				+ "O.xls");
		archivo.setBytes(generador.generaReporteEmailDejadosCajas(dejados,
				"XLS", realPath));
		// archivo.setIdCampania(Integer.parseInt(campania));
		archivo.setListaItems(idsItems);
		archivo.setType("excel");
		archivos.add(archivo);
		return archivos;

	}
	
	private ArchivoCorreo generarReporteRecolectados(ModelOrdenesDejadasRecolectadas ordenes) {
		
		
		return null;
	}

	private List<ArchivoCorreo> llenarModelosPremios(ItemSubBodega[] items,
			int idSubBodega, String formato, String realPath)
			throws IOException {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
		List<ModelRepMailDejadosPremios> dejados = new ArrayList<ModelRepMailDejadosPremios>();
		List<ArchivoCorreo> archivos = new ArrayList<ArchivoCorreo>();
		JasperGenerator generador = new JasperGenerator();
		int cont = 1;
		List<Integer> idsItems = new ArrayList<Integer>();
		String zona = "", campania = "";
		for (ItemSubBodega item : items) {
			if (item.getTipoArchivo().equals("P")) {
				ModelRepMailDejadosPremios res = new ModelRepMailDejadosPremios();
				res.setAccount(item.getAccount());
				res.setCampania(item.getCampania());
				res.setCantidad(item.getCantidad());
				res.setCodigo(item.getCodigo());
				res.setDescripcion(item.getDescripcion());
				res.setZona(item.getZona());
				res.setCont(cont);
				res.setNombre(item.getNombreRepresentante());
				idsItems.add(item.getIdItem());

				if (!zona.equals(item.getZona())
						|| !campania.equals(item.getCampania())) {
					if (!zona.equals("")) {

						ArchivoCorreo archivo = new ArchivoCorreo();
						archivo.setNombreArchivo(zona + "_" + idSubBodega + "_"
								+ simpleDateformat.format(new Date()) + "_"
								+ campania + "_" + item.getTipoArchivo()
								+ ".xls");
						archivo.setBytes(generador
								.generaReporteEmailDejadosPremios(dejados,
										formato, realPath));
						archivo.setType("excel");
						archivo.setListaItems(idsItems);
						archivos.add(archivo);
						idsItems = new ArrayList<Integer>();
						dejados = new ArrayList<ModelRepMailDejadosPremios>();
						cont = 1;
						res.setCont(cont);
						dejados.add(res);
						zona = item.getZona();
						campania = item.getCampania();

					} else {
						dejados.add(res);
						zona = item.getZona();
						campania = item.getCampania();
						cont++;
					}
				} else {
					dejados.add(res);
					cont++;
				}

			}
		}
		ArchivoCorreo archivo = new ArchivoCorreo();
		archivo.setNombreArchivo(zona + "_" + idSubBodega + "_"
				+ simpleDateformat.format(new Date()) + "_" + campania + "_"
				+ "P.xls");
		archivo.setBytes(generador.generaReporteEmailDejadosPremios(dejados,
				"XLS", realPath));
		archivo.setType("excel");
		archivo.setListaItems(idsItems);
		archivos.add(archivo);
		return archivos;

	}
}
