package com.datacode.avon_ots_admin.reports;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import com.datacode.avon_ots_admin.model.ArchivoCorreo;
import com.datacode.avon_ots_admin.quartz.TareaMandarMailDejados;
import com.datacode.avon_ots_admin.reports.model.ModelDetalleCajas;
import com.datacode.avon_ots_admin.reports.model.ModelDetalleDocumento;
import com.datacode.avon_ots_admin.reports.model.ModelDetallePremios;
import com.datacode.avon_ots_admin.reports.model.ModelOrdenesDejadasRecolectadas;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.ReportesControllerStub.DestinatarioReporte;
import com.datacode.avon_ots_ws.UtilsStub.Configuracion;

public class GenerarReporteOrdenesDejadasRecolectadasPUP {

	Configuracion configuracion = Utils.obtenerConfiguracionLDC();
	SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
	
	public String generarReporteMail (List<ModelOrdenesDejadasRecolectadas> listaOrdenes,String tipoOrden, String realPath,int idLDC) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			System.out.println("Comienza proceso de generacion:::::::::::");
			ConsultaDatosReportes consulta = new ConsultaDatosReportes();
			EnvioReporteDejadosMail envioMail = new EnvioReporteDejadosMail();
			List<ArchivoCorreo> listaArchivo = null;
			TareaMandarMailDejados tarea = new TareaMandarMailDejados();
			if (listaOrdenes != null && !listaOrdenes.isEmpty()) {
				//OBTENER DESTINATARIOS
				List<DestinatarioReporte> destinatarios = null;
				if (tipoOrden.equals("dejada"))
					destinatarios = consulta.obtenerDestinatariosReportePorTipoReporte(
							"Reporte de Liquidación de Reparto", 0, 1);
				else if (tipoOrden.equals("recolectada"))
					destinatarios = consulta.obtenerDestinatariosReportePorTipoReporte(
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
				System.out.println("DESTINATARIOS:::::::::::::::::::::::::::::::::::::::::::: "+recipientes);
				//RECORRER LISTA DE ORDENES PARA GENERAR Y ENVIAR CORREO DE CADA UNA POR PUP
				int contt = 0;
				for (ModelOrdenesDejadasRecolectadas orden : listaOrdenes) {
					System.out.println("el correo: " + orden.getCorreo());
					ArchivoCorreo archivo = null;
					recipientes = recipientes + ","
							+ orden.getCorreo();
					//SE INVOCA EL RETORNO DE UNA INSTANCIA ARCHIVO QUE CONTIENE EL BYTEARRAY DEL REPORTE GENERADO
					archivo = llenarArchivoCorreo(orden,tipoOrden,"XLS",realPath, contt++);
					if(tipoOrden.equals("dejada"))
						archivo = setNombreTipoArchivo(archivo, "Ordenes Dejadas en PUPs", "xls", "application/x-excel");
					else if (tipoOrden.equals("recolectada"))
						archivo = setNombreTipoArchivo(archivo, "Ordendes Recolectadas en PUPs", "xls", "application/x-excel");
					listaArchivo = new ArrayList<ArchivoCorreo>();
					listaArchivo.add(archivo);
					//LA SIGUIENTE LINEA NO SE USARÍA, YA QUE EL METODO mandarArchivosCorreo ESTÁ AMARRADO 
					//A LA REGLA DE NEGOCIO PERTENECIENTE A LA OTRA FUNCIONALIDAD
					String resultado = null;
					if(tipoOrden.equals("dejada")) {
						resultado = envioMail.mandarArchivosCorreo(listaArchivo, idLDC,
								recipientes, "Ordenes Dejadas en PUPs");
					}
					else {
						resultado = envioMail.mandarArchivosCorreo(listaArchivo, idLDC,
								recipientes, "Ordenes Recolectadas en PUPs");
					}

					
					String tipoLiquidacion = null;
					if(tipoOrden.equals("dejada"))
						tipoLiquidacion = "2";
					else
						tipoLiquidacion= "3";
					
					if (resultado.equals("")) {
						tarea.actualizaStatusListaLiquidaciones((int)(orden.getIdSalidaReparto()), "E", tipoLiquidacion);
					} else {
						tarea.actualizaStatusListaLiquidaciones((int)(orden.getIdSalidaReparto()), "N", tipoLiquidacion);
					}
					if (!(resultado != null && resultado.equals(""))) {
						Utils.GuardarLogMensajeBD(
								"EnvioReporteDejadosSubbodega",
								"M1",
								"Ocurrió un error el enviar reporte de Pedidos Dejados",
								resultado, 1);
						// ocurrio un problema enviando el mail
					}
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Utils.GuardarLogMensajeBD("EnvioReporteOrdenesDejadasRecolectadas", "M1",
					"Ocurrió un error el enviar reporte",
					e.getMessage() + e.getStackTrace(), 1);
		}
		return "";
	}
	
	public ArchivoCorreo llenarArchivoCorreo(ModelOrdenesDejadasRecolectadas orden,String tipoOrden, String formato, String realPath, int cont) throws IOException {
		JasperGenerator generador = new JasperGenerator();
		ArchivoCorreo archivo = new ArchivoCorreo();
		archivo.setNombreArchivo(orden.getIdPup() + "_"
				+ simpleDateformat.format(new Date().getTime()) + ".xls");
		archivo.setBytes(generador.generaReporteEmailOrdenesDejadasRecolectadasPUP(
				orden, tipoOrden, formato, realPath));
		archivo.setType("excel");
		OutputStream outputfile = new FileOutputStream(new File("/Users/ironhide/Documents/" + archivo.getNombreArchivo()+ "_" + cont));
		outputfile.write(archivo.getBytes().toByteArray());
		outputfile.close();
		return archivo;
	}
	
	public ArchivoCorreo setNombreTipoArchivo(ArchivoCorreo archivo,
			String nombreReporte,String formatoReporte,String tipoReporte) {
		String nombre=Utils.generarNombreArchivoCorreo(nombreReporte, configuracion.getClaveLDC(), formatoReporte);
		archivo.setNombreArchivo(nombre);
		archivo.setType(tipoReporte);
		return archivo;
	}
	
}
