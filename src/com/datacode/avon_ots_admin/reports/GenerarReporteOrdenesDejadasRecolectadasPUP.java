package com.datacode.avon_ots_admin.reports;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.datacode.avon_ots_admin.model.ArchivoCorreo;
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
			System.out.println("Comienza proceso de generacion:::::::::::");
			//LA SIGUIENTE LINEA ES TEMPORAL, PARA PRUEBAS
			listaOrdenes = setDatosOrdenesTEMPORAL();
			ConsultaDatosReportes consulta = new ConsultaDatosReportes();
			EnvioReporteDejadosMail envioMail = new EnvioReporteDejadosMail();
			List<ArchivoCorreo> listaArchivo = null;
			if (listaOrdenes != null) {
				//OBTENER DESTINATARIOS
				List<DestinatarioReporte> destinatarios = null;
				if (tipoOrden.equals("dejada"))
					destinatarios = consulta.obtenerDestinatariosReportePorTipoReporte(
							"Pedidos Dejados en PUPs", 0, 1);
				else if (tipoOrden.equals("recolectada"))
					destinatarios = consulta.obtenerDestinatariosReportePorTipoReporte(
							"Pedidos Recolectados en PUPs", 0, 1);
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
				for (ModelOrdenesDejadasRecolectadas orden : listaOrdenes) {
					ArchivoCorreo archivo = null;
					//SE INVOCA EL RETORNO DE UNA INSTANCIA ARCHIVO QUE CONTIENE EL BYTEARRAY DEL REPORTE GENERADO
					archivo = llenarArchivoCorreo(orden,tipoOrden,"XLS",realPath);
					if(tipoOrden.equals("dejada"))
						archivo = setNombreTipoArchivo(archivo, "Pedidos Dejados en PUPs", "xls", "application/x-excel");
					else if (tipoOrden.equals("recolectada"))
						archivo = setNombreTipoArchivo(archivo, "Pedidos Recolectados en PUPs", "xls", "application/x-excel");
					listaArchivo = new ArrayList<ArchivoCorreo>();
					listaArchivo.add(archivo);
					//LA SIGUIENTE LINEA NO SE USARÍA, YA QUE EL METODO mandarArchivosCorreo ESTÁ AMARRADO 
					//A LA REGLA DE NEGOCIO PERTENECIENTE A LA OTRA FUNCIONALIDAD
					String resultado = envioMail.mandarArchivosCorreo(listaArchivo, idLDC,
							recipientes, "Reporte de Liquidación de Reparto");
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
	
	public ArchivoCorreo llenarArchivoCorreo(ModelOrdenesDejadasRecolectadas orden,String tipoOrden, String formato, String realPath) throws IOException {
		JasperGenerator generador = new JasperGenerator();
		ArchivoCorreo archivo = new ArchivoCorreo();
		archivo.setNombreArchivo(orden.getIdPup() + "_"
				+ simpleDateformat.format(new Date()) + ".xls");
		archivo.setBytes(generador.generaReporteEmailOrdenesDejadasRecolectadasPUP(
				orden, tipoOrden, formato, realPath));
		archivo.setType("excel");
		OutputStream outputfile = new FileOutputStream(new File("/Users/angelHergon/Documents/" + archivo.getNombreArchivo() + ".xls"));
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
	
	
	////ESTE METODO ES TEMPORAL, SOLO PARA PRUEBAS
	public List<ModelOrdenesDejadasRecolectadas> setDatosOrdenesTEMPORAL() {
		List<ModelOrdenesDejadasRecolectadas> modeloList = new ArrayList<ModelOrdenesDejadasRecolectadas>();
		ModelOrdenesDejadasRecolectadas modelo = new ModelOrdenesDejadasRecolectadas();
		ModelDetalleCajas caja = new ModelDetalleCajas();
		ModelDetalleDocumento documento = new ModelDetalleDocumento();
		ModelDetallePremios premio = new ModelDetallePremios();
		List<ModelDetalleCajas> cajas = new ArrayList<ModelDetalleCajas>();
		List<ModelDetalleDocumento> documentos = new ArrayList<ModelDetalleDocumento>();
		List<ModelDetallePremios> premios = new ArrayList<ModelDetallePremios>();
		
		caja.setCampana("142016");
		caja.setCodigoBarras("123123123");
		caja.setDejadoPup(1);
		caja.setItem(1);
		caja.setNombre("blabla");
		caja.setOrden(12434523);
		caja.setRecolectadoPup(1);
		caja.setRegistro("23423423");
		caja.setZona("zona1");
		cajas.add(caja);
		documento.setEnviadosCods(1);
		documento.setEnviadosRemitos(0);
		documento.setRecibidosCods(1);
		documento.setRecibidosRemitos(0);
		documento.setRecolectadosCods(1);
		documento.setRecolectadosRemitos(1);
		documento.setRegitro(1123213);
		documentos.add(documento);
		premio.setCantidad(30);
		premio.setDejadoPup(1);
		premio.setEan13("asd");
		premio.setFsc("asdasdasd");
		premio.setRecolectadoPup(1);
		premios.add(premio);
		
		modelo.setCorreo("iscjangelhg@gmail.com");
		modelo.setIdPup(1);
		modelo.setDetalleCajas(cajas);
		modelo.setDetalleDocumentos(documentos);
		modelo.setDetallePremios(premios);
		modeloList.add(modelo);
		return modeloList;
	}
	
}
