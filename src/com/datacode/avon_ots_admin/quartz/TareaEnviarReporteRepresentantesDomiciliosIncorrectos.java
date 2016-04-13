package com.datacode.avon_ots_admin.quartz;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.axis2.AxisFault;
import com.datacode.avon_ots_admin.model.ArchivoCorreo;
import com.datacode.avon_ots_admin.reports.ConsultaDatosReportes;
import com.datacode.avon_ots_admin.reports.EnvioReporteDomiciliosIncorrectos;
import com.datacode.avon_ots_admin.reports.JasperGenerator;
import com.datacode.avon_ots_admin.reports.model.ModelRepDomiciliosIncorrectos;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.UtilsStub;
import com.datacode.avon_ots_ws.ReportesControllerStub.DestinatarioReporte;
import com.datacode.avon_ots_ws.UtilsStub.Configuracion;

public class TareaEnviarReporteRepresentantesDomiciliosIncorrectos {

	public void procesarEnvioDeCorreos() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		ConsultaDatosReportes reportes = new ConsultaDatosReportes();
		List<ModelRepDomiciliosIncorrectos> datosReporte = reportes
				.datosReporteDomiciliosIncorrectos(1);
		if (datosReporte != null && datosReporte.size() > 0) {

			// primero tenemos que llenar una lista de zonas
			String zona = "0";
			List<String> zonas = new ArrayList<String>();
			for (ModelRepDomiciliosIncorrectos m : datosReporte) {
				if ("0".equals(zona)) {
					// es la primera vez que entramos
					zona = m.getZona();
					zonas.add(m.getZona());
				} else {
					// checamos si la zona es la misma que traemos
					if (!zona.equals(m.getZona())) {
						// si es diferente entones agragamos otra
						zona = m.getZona();
						zonas.add(m.getZona());
					}
				}
			}

			for (String z : zonas) {
				// por cada zona generamos un archivo

				List<ModelRepDomiciliosIncorrectos> listaEnv = new ArrayList<ModelRepDomiciliosIncorrectos>();
				for (ModelRepDomiciliosIncorrectos m : datosReporte) {
					if (z.equals(m.getZona())) {
						listaEnv.add(m);
					}
				}

				JasperGenerator generador = new JasperGenerator();
				try {
					String realPath2 = com.datacode.avon_ots_admin.quartz.ListenerJobs.path;

					ArchivoCorreo archivoCorreo = new ArchivoCorreo();
					Configuracion config = Utils.obtenerConfiguracionLDC();
					String nombreArchivo = Utils.generarNombreArchivoCorreo(
							"Reporte Domicilios Incorrectos",
							config.getClaveLDC(), "pdf");
					archivoCorreo.setNombreArchivo(nombreArchivo);
					archivoCorreo.setBytes(generador
							.generaReporteDomiciliosIncorrectos(listaEnv,
									"PDF", realPath2));
					archivoCorreo.setType("application/pdf");
					List<ArchivoCorreo> archivos = new ArrayList<ArchivoCorreo>();
					archivos.add(archivoCorreo);
					int ldc = ldcConf();
					String recipientes = obtenerDestinatarios(listaEnv.get(0)
							.getEmail(), ldc);
					if (recipientes.length() > 0) {
						EnvioReporteDomiciliosIncorrectos e = new EnvioReporteDomiciliosIncorrectos();
						e.mandarCorreo(archivos, ldc, recipientes, z,
								"Reporte de Domicilios Incorrectos");
					} else {
						Utils.GuardarLogMensajeBD(
								"UCS36_2",
								"clave",
								df.format(new Date())
										+ "No existe correo de la GZ: "
										+ zona
										+ " configurado para el envío del Reporte de Domicilios Incorrectos",
								"", 1);

					}

				} catch (IOException e) {
					Utils.GuardarLogMensajeBD(
							"UCS36_2",
							"clave",
							df.format(new Date())
									+ "Ocurrio un problema generando el reporte de domicilios incorrectos:"
									+ e.getMessage(), "", 1);
				} catch (Exception e) {
					Utils.GuardarLogMensajeBD(
							"UCS36_2",
							"clave",
							df.format(new Date())
									+ "Ocurrio un problema generando el reporte de domicilios incorrectos:"
									+ e.getMessage(), "", 1);
				}

			}
		}
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

	private String obtenerDestinatarios(String destinatarioObligatorio, int ldc) {
		String recipientes = "";
		if (destinatarioObligatorio != null
				&& !destinatarioObligatorio.equals("")) {
			ConsultaDatosReportes consulta = new ConsultaDatosReportes();
			List<DestinatarioReporte> destinatarios = consulta
					.obtenerDestinatariosReportePorTipoReporte(
							"Reporte de Domicilios Incorrectos", ldc, 1);
			recipientes = destinatarioObligatorio;
			if (destinatarios != null && destinatarios.size() > 0) {
				for (DestinatarioReporte destinatario : destinatarios) {

					recipientes = recipientes + "," + destinatario.getMail();

				}
			}
		}

		return recipientes;
	}

}
