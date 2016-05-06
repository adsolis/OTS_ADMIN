package com.datacode.avon_ots_admin.reports;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import com.datacode.avon_ots_admin.model.ArchivoCorreo;
import com.datacode.avon_ots_admin.model.Bancos;
import com.datacode.avon_ots_admin.model.Campania;
import com.datacode.avon_ots_admin.model.Division;
import com.datacode.avon_ots_admin.model.EstadoOrden;
import com.datacode.avon_ots_admin.model.Orden;
import com.datacode.avon_ots_admin.model.Premios;
import com.datacode.avon_ots_admin.model.ProgramacionReparto;
import com.datacode.avon_ots_admin.model.RazonesDevolucion;
import com.datacode.avon_ots_admin.model.Representante;
import com.datacode.avon_ots_admin.model.TipoLiquidacion;
import com.datacode.avon_ots_admin.model.TipoPago;
import com.datacode.avon_ots_admin.model.TipoSiniestro;
import com.datacode.avon_ots_admin.model.Unitarios;
import com.datacode.avon_ots_admin.model.Zona;
import com.datacode.avon_ots_admin.reports.model.ModelAnalisisEfectivo;
import com.datacode.avon_ots_admin.reports.model.ModelDetalleCajas;
import com.datacode.avon_ots_admin.reports.model.ModelExpRecepcionAjustesPremios;
import com.datacode.avon_ots_admin.reports.model.ModelHeaderReparto;
import com.datacode.avon_ots_admin.reports.model.ModelHeaderResumen;
import com.datacode.avon_ots_admin.reports.model.ModelHistorialPorRepresentante;
import com.datacode.avon_ots_admin.reports.model.ModelHistoricoDeRepresentantes;
import com.datacode.avon_ots_admin.reports.model.ModelManifiestoRutaEnCampania;
import com.datacode.avon_ots_admin.reports.model.ModelOrdenesDejadasRecolectadas;
import com.datacode.avon_ots_admin.reports.model.ModelRelPedidosDejados;
import com.datacode.avon_ots_admin.reports.model.ModelRelPedidosPrestados;
import com.datacode.avon_ots_admin.reports.model.ModelRepConsultaRepresentantes;
import com.datacode.avon_ots_admin.reports.model.ModelRepDescargaEnrutado;
import com.datacode.avon_ots_admin.reports.model.ModelRepDomiciliosIncorrectos;
import com.datacode.avon_ots_admin.reports.model.ModelRepFaltantesTotales;
import com.datacode.avon_ots_admin.reports.model.ModelRepMailDejadosCajas;
import com.datacode.avon_ots_admin.reports.model.ModelRepMailDejadosPremios;
import com.datacode.avon_ots_admin.reports.model.ModelRepPremios;
import com.datacode.avon_ots_admin.reports.model.ModelRepRecepcionCarga;
import com.datacode.avon_ots_admin.reports.model.ModelRepRecoleccionRemitos;
import com.datacode.avon_ots_admin.reports.model.ModelRepSalidaCamioneta;
import com.datacode.avon_ots_admin.reports.model.ModelReporteConcultaBuenVecino;
import com.datacode.avon_ots_admin.reports.model.ModelReporteConsultaEstatusOdenesRep;
import com.datacode.avon_ots_admin.reports.model.ModelReporteItemsNoEntregadosEnAlmacen;
import com.datacode.avon_ots_admin.reports.model.ModelReporteItemsNoEscaneados;
import com.datacode.avon_ots_admin.reports.model.ModelReporteResumenGeneralZonas;
import com.datacode.avon_ots_admin.reports.model.NoAceptacionReparto;
import com.datacode.avon_ots_admin.reports.model.NoAceptacionRepartoTotal;
import com.datacode.avon_ots_ws.EntregaVentanillaStub.EntregaVentanilla;
import com.datacode.avon_ots_ws.LiquidacionRepartoWeb;

public class JasperGenerator {

	public ArchivoCorreo generaExpRecepcionAjustes(
			List<ModelExpRecepcionAjustesPremios> valores, String formato)
			throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();
		String archivoSalida = "Recepcion Ajustes";

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//recepcionAjustePremios.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//recepcionAjustePremios.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//recepcionAjustePremios.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generaReporteRecepcionCarga(
			List<ModelRepRecepcionCarga> valores, String formato,
			String observaciones, String archivoSalida) throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", rutaRep + "\\");
		parameters.put("LOGO_AVON_DIR", rutaImg + "\\headerAvon.gif");
		parameters.put("LOGO_LDC_DIR", rutaImg + "\\ldc_logo.jpg");
		parameters.put("observacionesCaptura", observaciones);
		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//controlRecepcionCarga.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//controlRecepcionCarga.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//controlRecepcionCarga.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generaReporteDescargaEnrutado(
			List<ModelRepDescargaEnrutado> valores, String formato,
			String archivoSalida) throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", rutaRep + "\\");
		parameters.put("LOGO_AVON_DIR", rutaImg + "\\headerAvon.gif");
		parameters.put("LOGO_LDC_DIR", rutaImg + "\\ldc_logo.jpg");
		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//descargaEnrutado.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//descargaEnrutado.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//descargaEnrutado.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generaReporteReparto(ModelHeaderReparto valores,
			String formato, String archivoSalida) throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", rutaRep + "\\");
		parameters.put("LOGO_AVON_DIR", rutaImg + "\\headerAvon.gif");
		parameters.put("LOGO_LDC_DIR", rutaImg + "\\ldc_logo.jpg");
		parameters.put("ENV_ORD_C_REPARTO",
				"" + valores.getEnvTotalOrdenesConReparto());
		parameters.put("ENV_ORD_S_REPARTO",
				"" + valores.getEnvTotalOrdenesSinReparto());
		parameters.put("ENV_PRIMERAS_ORDENES",
				"" + valores.getEnvTotalPrimerasOrdenes());
		parameters.put("ENV_ORDENES_TOTALES",
				"" + valores.getEnvTotalOrdenesTotales());
		parameters.put("ENV_CAJAS_TOTALES",
				"" + valores.getEnvTotalCajasTotales());
		parameters.put("ENV_CAJ_ORD", "" + valores.getEnvTotalCajProm());
		parameters.put("ENV_PREMIOS", "" + valores.getEnvTotalPremios());
		parameters.put("KM_INICIO_RUTA", "" + valores.getKmTotalInicioRuta());
		parameters.put("HR_INICIO_RUTA", "" + valores.getHrTotalInicioRuta());
		parameters.put("KM_PRIMERA_ENTREGA",
				"" + valores.getKmTotalPrimeraEntrega());
		parameters.put("HR_PRIMERA_ENTREGA",
				"" + valores.getHrTotalPrimeraEntrega());
		parameters.put("KM_ULTIMA_ENTREGA",
				"" + valores.getKmTotalUltimaEntrega());
		parameters.put("HR_ULTIMA_ENTREGA",
				"" + valores.getHrTotalUltimaEntrega());
		parameters.put("KM_FIN_RUTA", "" + valores.getKmTotalFinRuta());
		parameters.put("HR_FIN_RUTA", "" + valores.getHrTotalFinRuta());
		parameters.put("KM_REPARTO_GLOBAL",
				"" + valores.getKmTotalRepartoGlobal());
		parameters.put("HR_REPARTO_GLOBAL",
				"" + valores.getHrTotalRepartoGlobal());
		parameters.put("KM_REPARTO_EFECTIVO",
				"" + valores.getKmTotalRepartoEfectivo());
		parameters.put("HR_REPARTO_EFECTIVO",
				"" + valores.getHrTotalRepartoEfectivo());
		parameters.put("KM_ARRASTRE", "" + valores.getKmTotalArrastre());
		parameters.put("HR_ARRASTRE", "" + valores.getHrTotalArrastre());
		parameters.put("PRO_TIEMPO_VISITA",
				"" + valores.getProTotalTiempoVisita());
		parameters.put("PRO_ORD_HR", "" + valores.getProTotalOrdHr());
		parameters.put("ENT_PRIMERA_ORDEN",
				"" + valores.getEntTotalPrimeraOrden());
		parameters.put("ENT_ORDENES_ESTABLECIDAS",
				"" + valores.getEntTotalOrdenesEstablecidas());
		parameters.put("ENT_CAJAS_TOTALES",
				"" + valores.getEntTotalCajasTotales());
		parameters.put("ENT_PREMIOS", "" + valores.getEntTotalPremios());
		parameters.put("ACP_PREMIOS", "" + valores.getAcpTotalPremios());
		parameters.put("ACP_PRIMERA_ORDEN",
				"" + valores.getAcpTotalPrimeraOrden());
		parameters.put("ACP_TOTAL", "" + valores.getAcpTotalTotal());
		parameters.put("DEV_ORDENES", "" + valores.getDevTotalOrdenes());
		parameters.put("DEV_CAJAS", "" + valores.getDevTotalCajas());
		parameters.put("DEV_PREMIOS", "" + valores.getDevTotalPremios());
		parameters.put("CAU_NOVIVEAHI", "" + valores.getCauPorNoviveahi());
		parameters.put("CAU_NOPAGO", "" + valores.getCauPorNoPago());
		parameters.put("CAU_NODEJOFICHA", "" + valores.getCauPorNodejoFicha());
		parameters.put("CAU_CAMBIODEDOM",
				"" + valores.getCauPorCambiodedomicilio());
		parameters
				.put("CAU_CERRADOTOTAL", "" + valores.getCauPorCerradoTotal());
		parameters.put("CAU_DIFERENCIAENCOBRO",
				"" + valores.getCauPorDiferenciaenCobro());
		parameters.put("CAU_FUERADEZONA", "" + valores.getCauPorFueraZona());
		parameters.put("CAU_NOMETIOPEDIDO",
				"" + valores.getCauPorNometioPedido());
		parameters.put("CAU_DOMINCOMPLETO",
				"" + valores.getCauPorDomIncompleto());
		parameters.put("CAU_NOESPERAREPARTO",
				"" + valores.getCauPorNoEsperaReparto());
		parameters.put("CAU_EXTRAVIOFICHA",
				"" + valores.getCauPorExtravioFicha());
		parameters.put("CAU_OTROS", "" + valores.getCauPorOtro());
		parameters.put("CAUT_NOVIVEAHI", "" + valores.getCauTotNoviveahi());
		parameters.put("CAUT_NOPAGO", "" + valores.getCauTotNoPago());
		parameters.put("CAUT_NODEJOFICHA", "" + valores.getCauTotNodejoFicha());
		parameters.put("CAUT_CAMBIODEDOM",
				"" + valores.getCauTotCambiodedomicilio());
		parameters.put("CAUT_CERRADOTOTAL",
				"" + valores.getCauTotCerradoTotal());
		parameters.put("CAUT_DIFERENCIAENCOBRO",
				"" + valores.getCauTotDiferenciaenCobro());
		parameters.put("CAUT_FUERADEZONA", "" + valores.getCauTotFueraZona());
		parameters.put("CAUT_NOMETIOPEDIDO",
				"" + valores.getCauTotNometioPedido());
		parameters.put("CAUT_DOMINCOMPLETO",
				"" + valores.getCauTotDomIncompleto());
		parameters.put("CAUT_NOESPERAREPARTO",
				"" + valores.getCauTotNoEsperaReparto());
		parameters.put("CAUT_EXTRAVIOFICHA",
				"" + valores.getCauTotExtravioFicha());
		parameters.put("CAUT_OTROS", "" + valores.getCauTotOtro());
		parameters.put("CAUT_DIFERENCIA", "" + valores.getCauTotDiferencia());
		parameters.put("CAU_DIFERENCIA", "" + valores.getCauPorDiferencia());
		parameters.put("RENDIMIENTO_LITROS", valores.getRenTotalLitros());
		parameters.put("RENDIMIENTO_KM_LITROS", valores.getRenTotalKmLt());

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters,
					valores.getDetalleReparto(), rutaRep + "//reparto.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters,
					valores.getDetalleReparto(), rutaRep + "//reparto.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters,
					valores.getDetalleReparto(), rutaRep + "//reparto.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generaReporteSalidaCamioneta(
			List<ModelRepSalidaCamioneta> valores, String formato,
			String archivoSalida) throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", rutaRep + "\\");
		parameters.put("LOGO_AVON_DIR", rutaImg + "\\headerAvon.gif");
		parameters.put("LOGO_LDC_DIR", rutaImg + "\\ldc_logo.jpg");
		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//controlSalidaCamioneta.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//controlSalidaCamioneta.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//controlSalidaCamioneta.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generaReporteConsultaRepresentantes(
			List<ModelRepConsultaRepresentantes> valores, String formato,
			String archivoSalida) throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//consultaRepresentantes.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//consultaRepresentantes.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//consultaRepresentantes.jasper");
		}
		return archivo;
	}

	public ByteArrayOutputStream generaReporteEmailDejadosPremios(
			List<ModelRepMailDejadosPremios> valores, String formato,
			String realPath) throws IOException {
		ByteArrayOutputStream archivo = new ByteArrayOutputStream();

		// ServletContext context = ((ServletContext) FacesContext
		// .getCurrentInstance().getExternalContext().getContext());
		//
		// String rutaRep = context.getRealPath("/reportes");
		// String rutaRep="E:\\TOMCAT\\webapps\\avon_ots_admin\\reportes";

		// Llenamos la lista de parametros
		String archivoSalida = "";
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (formato.equals("XLS")) {
			archivo = generarReporteExcelArchivo(archivoSalida, parameters,
					valores, realPath + "//mailDejadosPremios.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDFArchivo(archivoSalida, parameters,
					valores, realPath + "//mailDejadosPremios.jasper");
		}
		return archivo;
	}

	public ByteArrayOutputStream generaReporteEmailDejadosCajas(
			List<ModelRepMailDejadosCajas> valores, String formato,
			String realPath) throws IOException {
		ByteArrayOutputStream archivo = new ByteArrayOutputStream();

		// ServletContext context = ((ServletContext) FacesContext
		// .getCurrentInstance().getExternalContext().getContext());
		//
		// String rutaRep = context.getRealPath("/reportes");
		// String rutaRep="E:\\TOMCAT\\webapps\\avon_ots_admin\\reportes";

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		String archivoSalida = "";
		if (formato.equals("XLS")) {
			archivo = generarReporteExcelArchivo(archivoSalida, parameters,
					valores, realPath + "//mailDejadosCajas.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDFArchivo(archivoSalida, parameters,
					valores, realPath + "//mailDejadosCajas.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generaReporteDirectorioDeLideres(List<String> valores,
			String formato, String archivoSalida) throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//directorioDeLideres.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//directorioDeLideres.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//directorioDeLideres.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generaReportePedidosDejados(
			List<ModelRelPedidosDejados> valores, String formato,
			String archivoSalida) throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", rutaRep + "\\");
		parameters.put("LOGO_AVON_DIR", rutaImg + "\\headerAvon.gif");
		parameters.put("LOGO_LDC_DIR", rutaImg + "\\ldc_logo.jpg");

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//relacionPedidosDejados.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//relacionPedidosDejados.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//relacionPedidosDejados.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generaReportePedidosPrestados(
			List<ModelRelPedidosPrestados> valores, String formato,
			String archivoSalida) throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", rutaRep + "\\");
		parameters.put("IMAGEN_DIR", rutaImg + "\\avon_logo1.jpg");
		parameters.put("LOGO_LDC_DIR", rutaImg + "\\ldc_logo.jpg");
		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//relacionPedidosPrestados.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//relacionPedidosPrestados.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//relacionPedidosPrestados.jasper");
		}
		return archivo;
	}

	/**
	 * @author Javier.gallegos
	 * @since 17/01-2012
	 * @param archivoSalida
	 * @param parameters
	 * @param lstObjetos
	 * @param reporte
	 * 
	 *            metodo que llama el contexto para poder generar el reporte en
	 *            jasper
	 */
	private ArchivoCorreo generarReportePDF(String archivoSalida,
			Map<String, Object> parameters, Object lstObjetos, String reporte) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ArchivoCorreo archivo = new ArchivoCorreo();
		try {
			archivoSalida = archivoSalida + ".pdf";
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context
					.getExternalContext().getResponse();

			response.flushBuffer();

			JRPdfExporter exporter = new JRPdfExporter();

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline;filename="
					+ archivoSalida);

			ServletOutputStream out2 = response.getOutputStream();

			JasperPrint jasperPrint = null;

			jasperPrint = llenaReporte(parameters, lstObjetos, reporte);

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
					archivoSalida);
			exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, output);
			exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING,
					"UTF-8");
			exporter.exportReport();
			output.writeTo(out2);
			out2.flush();
			context.responseComplete();

			// Anadir este codigo si se desea visualizar el reporte en e visor
			// de Jasper
			// JasperViewer jasperViewer = new JasperViewer (jasperPrint, true);
			// jasperViewer.show ();

		} catch (JRException e) {
			e.printStackTrace();
			System.err.println("OCURRIO UNA EXCEPCION JRExcepcion");
		} catch (IOException e) {
			e.printStackTrace();
		}
		archivo.setBytes(output);
		archivo.setNombreArchivo(archivoSalida);
		archivo.setType("application/pdf");
		return archivo;
	}

	private ArchivoCorreo generarReporteExcel(String archivoSalida,
			Map<String, Object> parameters, Object lstObjetos, String reporte) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ArchivoCorreo archivo = new ArchivoCorreo();
		try {
			archivoSalida = archivoSalida + ".xls";
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context
					.getExternalContext().getResponse();

			response.flushBuffer();

			JasperPrint jasperPrint = null;

			jasperPrint = llenaReporte(parameters, lstObjetos, reporte);

			// coding For Excel:
			JRXlsExporter exporterXLS = new JRXlsExporter();
			exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT,
					jasperPrint);
			exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
					output);
			exporterXLS
					.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
							Boolean.FALSE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporterXLS.exportReport();

			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "inline; filename=\""
					+ archivoSalida + "\"");
			response.setContentLength(output.size());
			ServletOutputStream out = response.getOutputStream();
			output.writeTo(out);
			out.flush();
			context.responseComplete();
			// JasperViewer.viewReport(jasperPrint, false);

		} catch (JRException e) {
			e.printStackTrace();
			System.err.println("OCURRIO UNA EXCEPCION JRExcepcion");
		} catch (IOException e) {
			e.printStackTrace();
		}
		archivo.setBytes(output);
		archivo.setNombreArchivo(archivoSalida);
		archivo.setType("application/vnd.ms-excel");
		return archivo;
	}

	private ArchivoCorreo generarReporteCSV(String archivoSalida,
			Map<String, Object> parameters, Object lstObjetos, String reporte) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ArchivoCorreo archivo = new ArchivoCorreo();
		try {
			archivoSalida = archivoSalida + ".csv";
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context
					.getExternalContext().getResponse();

			response.flushBuffer();

			JasperPrint jasperPrint = null;

			jasperPrint = llenaReporte(parameters, lstObjetos, reporte);

			// coding For Excel:
			JRCsvExporter exporterCSV = new JRCsvExporter();
			exporterCSV.setParameter(JRCsvExporterParameter.JASPER_PRINT,
					jasperPrint);
			exporterCSV.setParameter(JRCsvExporterParameter.OUTPUT_STREAM,
					output);
			exporterCSV.setParameter(JRCsvExporterParameter.CHARACTER_ENCODING,
					"ISO-8859-1");
			exporterCSV.exportReport();

			response.setContentType("text/csv");
			response.setHeader("Content-disposition", "inline; filename=\""
					+ archivoSalida + "\"");
			response.setContentLength(output.size());
			ServletOutputStream out = response.getOutputStream();
			output.writeTo(out);
			out.flush();
			context.responseComplete();

		} catch (JRException e) {
			e.printStackTrace();
			System.err.println("OCURRIO UNA EXCEPCION JRExcepcion");
		} catch (IOException e) {
			e.printStackTrace();
		}
		archivo.setBytes(output);
		archivo.setNombreArchivo(archivoSalida);
		archivo.setType("text/csv");
		return archivo;
	}

	/**
	 * @author javier.gallegos
	 * @since 17/01/2012
	 * @param parameters
	 * @param lstObjetos
	 * @param reporte
	 * @return
	 * @throws JRException
	 * 
	 *             metodo que llena lo necesario para mandar crear el reporte en
	 *             jasper
	 */
	private JasperPrint llenaReporte(Map<String, Object> parameters,
			Object lstObjetos, String reporte) throws JRException {

		File reportFile = new File(reporte);

		// Verificamos que el archivo jasper exista.
		reportFile.getAbsolutePath();
		if (!reportFile.exists()) {
			throw new JRException(
					"El reporte especificado no existe. verifique la ruta.");
		}
		// CARGAMOS EL REPORTE
		JasperReport jasperReport = (JasperReport) JRLoader
				.loadObject(reportFile);

		// PASAMOS JRBeanCollectionDataSource (EL CUAL ES LA LISTA DE OBJETOS
		// QUE VAMOS A IMPRIMIR)
		JasperPrint resultado = null;
		try {
			resultado = JasperFillManager.fillReport(jasperReport, parameters,
					new JRBeanCollectionDataSource((List<Object>) lstObjetos));
		} catch (Exception e) {

		}
		// JasperExportManager.exportReportToPdfFile(
		// resultado, "c://nominaIDN.pdf");
		return resultado;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 15/02/2012
	 * @param valores
	 * @param formato
	 * @return archivo
	 * @throws IOException
	 * 
	 */
	public ArchivoCorreo generaReporteHistorialPorRepresentante(
			List<ModelHistorialPorRepresentante> valores, String formato,
			String archivoSalida) throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", rutaRep + "\\");
		parameters.put("IMAGEN_DIR", rutaImg + "\\avon_logo1.jpg");

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//historialPorRepresentante.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//historialPorRepresentante.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//historialPorRepresentante.jasper");
		}
		return archivo;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 15/02/2012
	 * @param valores
	 * @param formato
	 * @return archivo
	 * @throws IOException
	 * 
	 */
	public ArchivoCorreo generaReporteHistoricoRepresentantes(
			List<ModelHistoricoDeRepresentantes> valores, String formato,
			String archivoSalida) throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", rutaRep + "\\");
		parameters.put("IMAGEN_DIR", rutaImg + "\\headerAvon.gif");

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//historicoRepresentantes.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//historicoRepresentantes.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//historicoRepresentantes.jasper");
		}
		return archivo;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 15/02/2012
	 * @param valores
	 * @param formato
	 * @return
	 * 
	 */
	public ArchivoCorreo generaReporteAnalisisEfectivo(
			List<ModelAnalisisEfectivo> valores, String formato,
			String archivoSalida) {
		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", rutaRep + "\\");
		parameters.put("IMAGEN_DIR", rutaImg + "\\headerAvon.gif");

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//analisisDeEfectivo.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//analisisDeEfectivo.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//analisisDeEfectivo.jasper");
		}
		return archivo;
	}

	/**
	 * Generación del reporte de Manifiesto de Ruta en Campaña Asigna nombre de
	 * archivo de salida, parámetros y reporte a usar.
	 * 
	 * @author jorge.torner
	 * @since 31/01/2012
	 * @param valores
	 * @param formato
	 * @return
	 * @throws IOException
	 */
	public ArchivoCorreo generaReporteManifiestoRutaEnCampania(
			List<ModelManifiestoRutaEnCampania> valores, String formato,
			String archivoSalida) throws IOException {

		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", rutaRep + "\\");
		parameters.put("IMAGEN_DIR", rutaImg + "\\headerAvon.gif");
		parameters.put("IMG_LDC_LOGO_DIR", rutaImg + "\\ldc_logo.jpg");

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "\\manifiestoRutaEnCampania.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "\\manifiestoRutaEnCampania.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "\\manifiestoRutaEnCampania.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generaReporteItemsNoEntregadosEnAlmacen(
			List<ModelReporteItemsNoEntregadosEnAlmacen> valores,
			String formato, String archivoSalida) throws IOException {

		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");
		SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("IMAGE_DIR", rutaImg + "\\");
		parameters.put("zona", valores.get(0).getZona());
		parameters.put("campania", valores.get(0).getCampania());
		parameters.put("fechaImpresion", df1.format(new Date()));
		parameters.put("horaImpresion", df2.format(new Date()));
		parameters.put("total", "" + valores.size());

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "\\itemsNoEntregadosEnAlmacen.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "\\itemsNoEntregadosEnAlmacen.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "\\itemsNoEntregadosEnAlmacen.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generaReporteConsultaEstatusOrdenes(
			List<ModelReporteConsultaEstatusOdenesRep> datosReporte,
			String formato, String campania, String registro,
			String archivoSalida) throws IOException {

		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", rutaRep + "\\");
		parameters.put("IMAGE_DIR", rutaImg + "\\");
		parameters.put("zona", datosReporte.get(0).getZona());
		parameters.put("nombre", datosReporte.get(0).getNombre());
		parameters.put("campania", campania);
		parameters.put("registro", registro);

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters,
					datosReporte, rutaRep + "\\ReporteConsultaOrdenes.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters,
					datosReporte, rutaRep + "\\ReporteConsultaOrdenes.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters,
					datosReporte, rutaRep + "\\ReporteConsultaOrdenes.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generaReporteConsultaBuenVecino(
			List<ModelReporteConcultaBuenVecino> datosReporte, String formato,
			String zona, String ruta, String archivoSalida) throws IOException {

		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", rutaRep + "\\");
		parameters.put("IMAGE_DIR", rutaImg + "\\");
		parameters.put("zona", zona);
		parameters.put("ruta", ruta);

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters,
					datosReporte, rutaRep
							+ "\\ReporteConsultaBuenVecino.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters,
					datosReporte, rutaRep
							+ "\\ReporteConsultaBuenVecino.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters,
					datosReporte, rutaRep
							+ "\\ReporteConsultaBuenVecino.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generaReporteControlRecepcionDeCarga(
			List<ModelReporteConcultaBuenVecino> datosReporte, String formato,
			String zona, String campania, String archivoSalida)
			throws IOException {

		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", rutaRep + "\\");
		parameters.put("IMAGE_DIR", rutaImg + "\\");
		parameters.put("zona", zona);
		parameters.put("campania", campania);

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters,
					datosReporte, rutaRep
							+ "\\ReporteConsultaBuenVecino.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters,
					datosReporte, rutaRep
							+ "\\ReporteConsultaBuenVecino.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters,
					datosReporte, rutaRep
							+ "\\ReporteConsultaBuenVecino.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generaReporteRecoleccionRemitos(
			List<ModelRepRecoleccionRemitos> datosReporte, String formato,
			String archivoSalida) throws IOException {

		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("IMAGE_DIR", rutaImg + "\\");
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
		parameters.put("fecha", df.format(new Date()));
		parameters.put("hora", df2.format(new Date()));

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters,
					datosReporte, rutaRep
							+ "\\ReporteRecoleccionRemitos.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters,
					datosReporte, rutaRep
							+ "\\ReporteRecoleccionRemitos.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters,
					datosReporte, rutaRep
							+ "\\ReporteRecoleccionRemitos.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generaReporteItemsNoEscaneados(
			List<ModelReporteItemsNoEscaneados> datosReporte, String formato,
			String archivoSalida) throws IOException {

		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("IMAGE_DIR", rutaImg + "\\");
		parameters.put("zona", datosReporte.get(0).getZona());
		parameters.put("campania", datosReporte.get(0).getCampania());
		parameters.put("fecha", df.format(new Date()));
		parameters.put("hora", df2.format(new Date()));

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters,
					datosReporte, rutaRep + "\\ReporteItemsNoEscaneados.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters,
					datosReporte, rutaRep + "\\ReporteItemsNoEscaneados.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters,
					datosReporte, rutaRep + "\\ReporteItemsNoEscaneados.jasper");
		}
		return archivo;
	}

	public ByteArrayOutputStream generaReporteDomiciliosIncorrectos(
			List<ModelRepDomiciliosIncorrectos> datosReporte, String formato,
			String realPath) throws IOException {

		ByteArrayOutputStream archivo = new ByteArrayOutputStream();
		String archivoSalida = "";
		String rutaRep = realPath + "reportes";
		String rutaImg = realPath + "img";

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("IMAGE_DIR", rutaImg + "\\");

		archivo = generarReportePDFArchivo(archivoSalida, parameters,
				datosReporte, rutaRep + "\\ReporteDomiciliosIncorrectos.jasper");
		return archivo;
	}

	public ArchivoCorreo generaReporteFaltanteCajas(
			List<ModelRepFaltantesTotales> datosReporte, String formato,
			String zona, String campania, String archivoSalida)
			throws IOException {

		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("IMAGE_DIR", rutaImg + "\\");
		parameters.put("SUBREPORT_DIR", rutaRep + "\\");
		parameters.put("zona", zona);
		parameters.put("campania", campania);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
		parameters.put("fecha", df.format(new Date()));
		parameters.put("hora", df2.format(new Date()));

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters,
					datosReporte, rutaRep + "\\ReporteItemsFaltantes.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters,
					datosReporte, rutaRep + "\\ReporteItemsFaltantes.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters,
					datosReporte, rutaRep + "\\ReporteItemsFaltantes.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generaReporteResumenGeneralZona(
			List<ModelReporteResumenGeneralZonas> datosReporte, String formato,
			String campania, String division, String archivoSalida,String fecha)
			throws IOException {

		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("IMAGEN_DIR", rutaImg + "\\headerAvon.gif");
		parameters.put("LOGO_LDC_DIR", rutaImg + "\\ldc_logo.jpg");
		parameters.put("p_Division", division);
		parameters.put("p_Campania", campania);
		parameters.put("p_fecha", fecha);

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters,
					datosReporte, rutaRep
							+ "\\ReporteResumenGeneralZona.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters,
					datosReporte, rutaRep
							+ "\\ReporteResumenGeneralZona.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters,
					datosReporte, rutaRep
							+ "\\ReporteResumenGeneralZona.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generaReporteLiquidacionRepartoWeb(
			List<LiquidacionRepartoWeb> datosReporte, String formato, String archivoSalida, String fecha)
			throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();
		ServletContext context = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext());
		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");
		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("IMAGEN_DIR", rutaImg + "\\headerAvon.gif");
		parameters.put("LOGO_LDC_DIR", rutaImg + "\\ldc_logo.jpg");
		parameters.put("p_fecha", fecha);
		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, datosReporte, rutaRep + "\\Liquidacion_Enrutado.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, datosReporte, rutaRep + "\\Liquidacion_Enrutado.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, datosReporte, rutaRep + "\\Liquidacion_Enrutado.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generaReporteRecoleccionDeRemito(
			List<ModelReporteConcultaBuenVecino> datosReporte, String formato,
			String zona, String campania, String ruta, String archivoSalida)
			throws IOException {

		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", rutaRep + "\\");
		parameters.put("IMAGE_DIR", rutaImg + "\\");
		parameters.put("zona", zona);
		parameters.put("campania", campania);
		parameters.put("ruta", ruta);

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters,
					datosReporte, rutaRep
							+ "\\ReporteConsultaBuenVecino.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters,
					datosReporte, rutaRep
							+ "\\ReporteConsultaBuenVecino.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters,
					datosReporte, rutaRep
							+ "\\ReporteConsultaBuenVecino.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generarReporteDePremios(List<ModelRepPremios> valores,
			String formato, String archivoSalida) throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("LOGO_AVON_DIR", rutaImg + "\\headerAvon.gif");
		parameters.put("LOGO_LDC_DIR", rutaImg + "\\ldc_logo.jpg");
		parameters.put("SUBREPORT_DIR", rutaRep + "\\");

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//reportePremios.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//reportePremios.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//reportePremios.jasper");
		}
		return archivo;
	}

	public ArchivoCorreo generarReporteResumen(ModelHeaderResumen valores,
			String formato, String archivoSalida) throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaImg = context.getRealPath("/img");
		String rutaRep = context.getRealPath("/reportes");
		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("LOGO_AVON_DIR", rutaImg + "\\headerAvon.gif");
		parameters.put("LOGO_LDC_DIR", rutaImg + "\\ldc_logo.jpg");
		parameters.put("ANIO_CAMPANIA", valores.getAnioCampania());
		parameters.put("TOTAL_INICIO_RUTA_PROMEDIO",
				valores.getTotalInicioRutaPromedio());
		parameters.put("TOTAL_PRIMER_VISITA_PROMEDIO",
				valores.getTotalPrimerVisitaPromedio());
		parameters.put("TOTAL_ULTIMA_VISITA_PROMEDIO",
				valores.getTotalUltimaVisitaPromedio());
		parameters.put("TOTAL_FIN_RUTA_PROMEDIO",
				valores.getTotalFinRutaPromedio());
		parameters.put("TOTAL_TIEMPO_PROMEDIO_VISITA",
				valores.getTotalTiempoPromedioVisita());
		parameters.put("TOTAL_PRODUCTIVIDAD_ORDEN_HORA",
				valores.getTotalProductividadOrdenHora());
		parameters.put("TOTAL_CAU_NO_VIVE_AHI", valores.getTotalCauNoViveAhi());
		parameters.put("TOTAL_CAU_NO_PAGO", valores.getTotalCauNoPago());
		parameters.put("TOTAL_CAU_NO_DEJO_FICHA",
				valores.getTotalCauNoDejoFicha());
		parameters.put("TOTAL_CAU_CAMBIO_DOM", valores.getTotalCauCambioDom());
		parameters.put("TOTAL_CAU_CERRADO_TOTAL",
				valores.getTotalCauCerradoTotal());
		parameters.put("TOTAL_CAU_DIF_EN_COBRO",
				valores.getTotalCauDifEnCobro());
		parameters.put("TOTAL_CAU_FUERA_ZONA", valores.getTotalCauFueraZona());
		parameters.put("TOTAL_CAU_NO_METIO_PEDIDO",
				valores.getTotalCauNoMetioPedido());
		parameters.put("TOTAL_CAU_DOM_INCOMPLETO",
				valores.getTotalCauDomIncompleto());
		parameters.put("TOTAL_CAU_NO_ESPERA_REPARTO",
				valores.getTotalCauNoEsperaReparto());
		parameters.put("TOTAL_CAU_EXTRAVIO_FICHA",
				valores.getTotalCauExtravioFicha());
		parameters.put("TOTAL_CAU_OTRO", valores.getTotalCauOtro());
		parameters.put("TOTAL_CAU_TOTAL", valores.getTotalCauTotal());

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters,
					valores.getDetalles(), rutaRep + "//reporteResumen.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters,
					valores.getDetalles(), rutaRep + "//reporteResumen.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters,
					valores.getDetalles(), rutaRep + "//reporteResumen.jasper");
		}
		return archivo;
	}

	/*
	 * ***************************************** GENERA REPORTES DE GRIDS
	 * -CUADMIN001-04 **********************************************************
	 */
	/* ******************************************************** ************************************************************************************** */
	/**
	 * Obtienes los valores a Imprimir de [Bancos] y el tipo de Formato para
	 * Exportar a [Excel o PDF]
	 * 
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @param valores
	 *            - List[Bancos]
	 * @param formato
	 *            - PDF - XLS
	 * @throws IOException
	 *             - Error al Obtener el Objeto
	 */
	public void generaGridBancos(List<Bancos> valores, String formato)
			throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();
		String archivoSalida = "Bancos";
		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "\\GridBank.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "\\GridBank.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "\\GridBank.jasper");
		}
	}

	/**
	 * Obtienes los valores a Imprimir de [Campania] y el tipo de Formato para
	 * Exportar a [Excel o PDF]
	 * 
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @param valores
	 *            - List[Campania]
	 * @param formato
	 *            - PDF - XLS
	 * @throws IOException
	 *             - Error al Obtener el Objeto
	 */
	public void generaGridCampanias(List<Campania> valores, String formato)
			throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();
		String archivoSalida = "Campanias";

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "\\GridCampanias.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "\\GridCampanias.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "\\GridCampanias.jasper");
		}
	}

	/**
	 * Obtienes los valores a Imprimir de [Division] y el tipo de Formato para
	 * Exportar a [Excel o PDF]
	 * 
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @param valores
	 *            - List[Division]
	 * @param formato
	 *            - PDF - XLS
	 * @throws IOException
	 *             - Error al Obtener el Objeto
	 */
	public void generaGridDivisiones(List<Division> valores, String formato)
			throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();
		String archivoSalida = "División";

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "\\GridDivision.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "\\GridDivision.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "\\GridDivision.jasper");
		}
	}

	/**
	 * Obtienes los valores a Imprimir de [Zona] y el tipo de Formato para
	 * Exportar a [Excel o PDF]
	 * 
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @param valores
	 *            - List[Zona]
	 * @param formato
	 *            - PDF - XLS
	 * @throws IOException
	 *             - Error al Obtener el Objeto
	 */
	public void generaGridZonas(List<Zona> valores, String formato)
			throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();
		String archivoSalida = "Zonas";

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//GridZona.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//GridZona.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "\\GridZona.jasper");
		}
	}

	/**
	 * Obtienes los valores a Imprimir de [Representante] y el tipo de Formato
	 * para Exportar a [Excel o PDF]
	 * 
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @param valores
	 *            - List[Representante]
	 * @param formato
	 *            - PDF - XLS
	 * @throws IOException
	 *             - Error al Obtener el Objeto
	 */
	public void generaGridRepresentantes(List<Representante> valores,
			String formato) throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();
		String archivoSalida = "Representante";

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//GridRepresentante.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//GridRepresentante.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "\\GridRepresentante.jasper");
		}
	}

	/**
	 * Obtienes los valores a Imprimir de [TipoPago] y el tipo de Formato para
	 * Exportar a [Excel o PDF]
	 * 
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @param valores
	 *            - List[TipoPago]
	 * @param formato
	 *            - PDF - XLS
	 * @throws IOException
	 *             - Error al Obtener el Objeto
	 */
	public void generaGridTipoPago(List<TipoPago> valores, String formato)
			throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();
		String archivoSalida = "Tipos de Pago";

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//GridTipoPago.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//GridTipoPago.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//GridTipoPago.jasper");
		}
	}

	/**
	 * Obtienes los valores a Imprimir de [Orden] y el tipo de Formato para
	 * Exportar a [Excel o PDF]
	 * 
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @param valores
	 *            - List[Orden]
	 * @param formato
	 *            - PDF - XLS
	 * @throws IOException
	 *             - Error al Obtener el Objeto
	 */
	public void generaGridOrden(List<Orden> valores, String formato)
			throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();
		String archivoSalida = "Orden";

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//GridOrden.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//GridOrden.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//GridOrden.jasper");
		}
	}

	/**
	 * Obtienes los valores a Imprimir de [RazonesDevolucion] y el tipo de
	 * Formato para Exportar a [Excel o PDF]
	 * 
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @param valores
	 *            - List[RazonesDevolucion]
	 * @param formato
	 *            - PDF - XLS
	 * @throws IOException
	 *             - Error al Obtener el Objeto
	 */
	public void generaGridRazonesDevolucion(List<RazonesDevolucion> valores,
			String formato) throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();
		String archivoSalida = "Razones de Devolución";

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//GridRazonDevolucion.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//GridRazonDevolucion.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//GridRazonDevolucion.jasper");
		}
	}

	/**
	 * Obtienes los valores a Imprimir de [TipoLquidacion] y el tipo de Formato
	 * para Exportar a [Excel o PDF]
	 * 
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @param valores
	 *            - List[TipoLiquidacion]
	 * @param formato
	 *            - PDF - XLS
	 * @throws IOException
	 *             - Error al Obtener el Objeto
	 */
	public void generaGridTipoLiquidacion(List<TipoLiquidacion> valores,
			String formato) throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();
		String archivoSalida = "Tipo de Liquidación";

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//GridTipoLiquidacion.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//GridTipoLiquidacion.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//GridTipoLiquidacion.jasper");
		}
	}

	/**
	 * Obtienes los valores a Imprimir de [EstadoOrden] y el tipo de Formato
	 * para Exportar a [Excel o PDF]
	 * 
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @param valores
	 *            - List[EstadoOrden]
	 * @param formato
	 *            - PDF - XLS
	 * @throws IOException
	 *             - Error al Obtener el Objeto
	 */
	public void generaGridEstadoOrden(List<EstadoOrden> valores, String formato)
			throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();
		String archivoSalida = "Estado de la Orden";

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//GridEstadoOrden.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//GridEstadoOrden.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//GridEstadoOrden.jasper");
		}
	}

	/**
	 * Obtienes los valores a Imprimir de [TipoSiniestro] y el tipo de Formato
	 * para Exportar a [Excel o PDF]
	 * 
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @param valores
	 *            - List[TipoSiniestro]
	 * @param formato
	 *            - PDF - XLS
	 * @throws IOException
	 *             - Error al Obtener el Objeto
	 */
	public void generaGridTipoSiniestro(List<TipoSiniestro> valores,
			String formato) throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();
		String archivoSalida = "Tipos de Siniestro";

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//GridTipoSiniestro.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//GridTipoSiniestro.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//GridTipoSiniestro.jasper");
		}
	}

	/**
	 * Obtienes los valores a Imprimir de [Unitarios] y el tipo de Formato para
	 * Exportar a [Excel o PDF]
	 * 
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @param valores
	 *            - List[Unitarios]
	 * @param formato
	 *            - PDF - XLS
	 * @throws IOException
	 *             - Error al Obtener el Objeto
	 */
	public void generaGridUnitarios(List<Unitarios> valores, String formato)
			throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();
		String archivoSalida = "Unitarios";

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "//GridUnitario.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "//GridUnitario.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "//GridUnitario.jasper");
		}
	}

	/**
	 * Obtienes los valores a Imprimir de [Premios] y el tipo de Formato para
	 * Exportar a [Excel o PDF]
	 * 
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @param valores
	 *            - List[Premios]
	 * @param formato
	 *            - PDF - XLS
	 * @throws IOException
	 *             - Error al Obtener el Objeto
	 */
	public void generaGridPremios(List<Premios> valores, String formato)
			throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();
		String archivoSalida = "Premios";

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "\\GridPremio.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "\\GridPremio.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "\\GridPremio.jasper");
		}
	}

	/**
	 * Obtienes los valores a Imprimir de [ProgramacionReparto] y el tipo de
	 * Formato para Exportar a [Excel o PDF]
	 * 
	 * @author brenda.estrada
	 * @since 25/01/2012
	 * @param valores
	 *            - List[ProgramacionReparto]
	 * @param formato
	 *            - PDF - XLS
	 * @throws IOException
	 *             - Error al Obtener el Objeto
	 */
	public void generaGridProgramacionReparto(
			List<ProgramacionReparto> valores, String formato)
			throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();
		String archivoSalida = "Programación de Reparto";

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");

		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "\\GridProgramacionReparto.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "\\GridProgramacionReparto.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "\\GridProgramacionReparto.jasper");
		}
	}

	/**
	 * @author Javier.gallegos
	 * @since 17/01-2012
	 * @param archivoSalida
	 * @param parameters
	 * @param lstObjetos
	 * @param reporte
	 * 
	 *            metodo que llama el contexto para poder generar el reporte en
	 *            jasper
	 */
	private ByteArrayOutputStream generarReportePDFArchivo(
			String archivoSalida, Map<String, Object> parameters,
			Object lstObjetos, String reporte) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {

			JRPdfExporter exporter = new JRPdfExporter();
			JasperPrint jasperPrint = null;
			jasperPrint = llenaReporte(parameters, lstObjetos, reporte);

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
					archivoSalida);
			exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, output);
			exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING,
					"UTF-8");
			exporter.exportReport();

		} catch (JRException e) {
			e.printStackTrace();
			System.err.println("OCURRIO UNA EXCEPCION JRExcepcion");
		}
		return output;
	}

	private ByteArrayOutputStream generarReporteExcelArchivo(
			String archivoSalida, Map<String, Object> parameters,
			Object lstObjetos, String reporte) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			JasperPrint jasperPrint = null;
			jasperPrint = llenaReporte(parameters, lstObjetos, reporte);
			// coding For Excel:
			JRXlsExporter exporterXLS = new JRXlsExporter();
			exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT,
					jasperPrint);
			exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
					output);
			exporterXLS
					.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
							Boolean.FALSE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporterXLS.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
			System.err.println("OCURRIO UNA EXCEPCION JRExcepcion");
		}
		return output;
	}

	/**
	 * Obtienes los valores a Imprimir de [NoAceptacionReparto] y el tipo de
	 * Formato para Exportar a [Excel o PDF]
	 * 
	 * @author brenda.estrada
	 * @since 02/02/2012
	 * @param valores
	 *            - List[NoAceptacionReparto]
	 * @param formato
	 *            - PDF - XLS
	 * @throws IOException
	 *             - Error al Obtener el Objeto
	 */
	public ArchivoCorreo generaReporteNAR(List<NoAceptacionReparto> valores,
			String formato, String archivoSalida) throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());
		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");
		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", rutaRep + "\\");
		parameters.put("IMAGEN_DIR", rutaImg + "\\headerAvon.gif");
		parameters.put("IMG_LDC_LOGO_DIR", rutaImg + "\\ldc_logo.jpg");

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "\\ReporteListaNARGlobal.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "\\ReporteListaNARGlobal.jasper");
		} else if (formato.equals("CSV")) {
			archivo = generarReporteCSV(archivoSalida, parameters, valores,
					rutaRep + "\\ReporteListaNARGlobal.jasper");
		}
		return archivo;
	}

	/**
	 * Obtienes los valores a Imprimir de [NoAceptacionRepartoTotal] y el tipo
	 * de Formato para Exportar a [Excel o PDF]
	 * 
	 * @author brenda.estrada
	 * @since 21/02/2012
	 * @param valores
	 *            - List[NoAceptacionRepartoTotal]
	 * @param formato
	 *            - PDF - XLS
	 * @throws IOException
	 *             - Error al Obtener el Objeto
	 */
	public ArchivoCorreo generaReporteNARTotal(
			List<NoAceptacionRepartoTotal> valores, String formato,
			String archivoSalida) throws IOException {
		ArchivoCorreo archivo = new ArchivoCorreo();

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());
		String rutaRep = context.getRealPath("/reportes");
		String rutaImg = context.getRealPath("/img");
		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", rutaRep + "\\");
		parameters.put("IMAGEN_DIR", rutaImg + "\\headerAvon.gif");
		parameters.put("IMG_LDC_LOGO_DIR", rutaImg + "\\ldc_logo.jpg");

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + "\\ReporteListaNARTotal.jasper");
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + "\\ReporteListaNARTotal.jasper");
		} else if (formato.equals("CSV")) {
			generarReporteCSV(archivoSalida, parameters, valores, rutaRep
					+ "\\ReporteListaNARTotal.jasper");
		}
		return archivo;
	}

	public void generaRptEntVentanilla(List<EntregaVentanilla> valores,
			String formato) throws IOException {

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		// String rutaImg = context.getRealPath("/img");
		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		// parameters.put("SUBREPORT_DIR", rutaRep + "\\");
		// parameters.put("LOGO_AVON_DIR", rutaImg + "\\headerAvon.gif");
		String archivoSalida = "Reporte en Ventanilla";
		if (formato.equals("XLS")) {
			generarReporteExcel(archivoSalida, parameters, valores, rutaRep
					+ "\\rptPruebaEntrega.jasper");
		} else if (formato.equals("PDF")) {
			generarReportePDF(archivoSalida, parameters, valores, rutaRep
					+ "\\rptPruebaEntrega.jasper");
		} else if (formato.equals("CSV")) {
			generarReporteCSV(archivoSalida, parameters, valores, rutaRep
					+ "\\rptPruebaEntrega.jasper");
		}
	}

	/**
	 * Genera el archivo de salida para los catalogos de Mto de Elemento
	 * 
	 * @param valores
	 *            - Objeto de Valores [Lista]
	 * @param formato
	 *            ContentType [EXCEL - PDF]
	 * @param nombreArchivo
	 *            - Nombre de Salida del archivo
	 * @param nombreJasper
	 *            - Nombre del Objeto .jasper
	 * @author brenda.estrada
	 */
	public void generaGridMtoElemento(Object valores, String formato,
			String nombreArchivo, String nombreJasper) {
		ArchivoCorreo archivo = new ArchivoCorreo();
		String archivoSalida = nombreArchivo;

		ServletContext context = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext());

		String rutaRep = context.getRealPath("/reportes");
		// Llenamos la lista de parametros
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (formato.equals("XLS")) {
			archivo = generarReporteExcel(archivoSalida, parameters, valores,
					rutaRep + nombreJasper);
		} else if (formato.equals("PDF")) {
			archivo = generarReportePDF(archivoSalida, parameters, valores,
					rutaRep + nombreJasper);
		}
	}
	
	public ByteArrayOutputStream generaReporteEmailOrdenesDejadasRecolectadasPUP(
			ModelOrdenesDejadasRecolectadas orden,String tipoOrden,String formato, String realPath) throws IOException {
		ByteArrayOutputStream archivo = new ByteArrayOutputStream();
		String archivoSalida = "";
		String nombreJasper = "";
		Map<String, Object> parameters = new HashMap<String, Object>();
		Map<String, Object> detalleCajas = new HashMap<String, Object>();
		List<Map<String, Object>> listaCajas = new ArrayList<Map<String, Object>>();
		
		for(ModelDetalleCajas caja: orden.getDetalleCajas()) {
			detalleCajas.put("campana", caja.getCampana());
			
			listaCajas.add(detalleCajas);
		}
		
		
		parameters.put("listaDetalleCajas", listaCajas);
		if (tipoOrden.equals("dejada"))
			nombreJasper = "//mailOrdenesDejadasPUP.jasper";
		else if(tipoOrden.equals("recolectada"))
			nombreJasper = "//mailOrdenesRecolectadasPUP.jasper";
		archivo = generarReporteExcelArchivo(archivoSalida, parameters,
				orden, realPath + nombreJasper);
		return archivo;
	}
}
