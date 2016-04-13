package com.datacode.avon_ots_admin.reports;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.SimpleFormatter;

import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import com.datacode.avon_ots_admin.model.ArchivoCorreo;
import com.datacode.avon_ots_admin.reports.model.ModelAnalisisEfectivo;
import com.datacode.avon_ots_admin.reports.model.ModelHeaderReparto;
import com.datacode.avon_ots_admin.reports.model.ModelHeaderResumen;
import com.datacode.avon_ots_admin.reports.model.ModelHistorialPorRepresentante;
import com.datacode.avon_ots_admin.reports.model.ModelHistoricoDeRepresentantes;
import com.datacode.avon_ots_admin.reports.model.ModelManifiestoRutaEnCampania;
import com.datacode.avon_ots_admin.reports.model.ModelRelPedidosDejados;
import com.datacode.avon_ots_admin.reports.model.ModelRelPedidosPrestados;
import com.datacode.avon_ots_admin.reports.model.ModelRepConsultaRepresentantes;
import com.datacode.avon_ots_admin.reports.model.ModelRepDescargaEnrutado;
import com.datacode.avon_ots_admin.reports.model.ModelRepFaltantesCajas;
import com.datacode.avon_ots_admin.reports.model.ModelRepFaltantesTotales;
import com.datacode.avon_ots_admin.reports.model.ModelRepFaltantesUnitarios;
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
import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.CampaniaControllerStub.Campania;
import com.datacode.avon_ots_ws.ReportesControllerStub.DestinatarioReporte;
import com.datacode.avon_ots_ws.ReportesControllerStub.Reporte;
import com.datacode.avon_ots_ws.RutasControllerStub.Rutas;
import com.datacode.avon_ots_ws.UtilsStub;

/**
 * @author javier.gallegos clase que manejara la pantalla de generacion de
 *         reportes, asi como la generacion de los filtros
 * 
 */
public class ControllerGeneradorReportes {

	Configuracion config = (Configuracion) FacesContext.getCurrentInstance()
			.getExternalContext().getSessionMap().get("configuracion");

	private int idModulo = 0;
	private UIForm form;
	private Integer repSel;
	private Integer ldcSel;
	private Boolean panelRepPedidosPrestados = new Boolean(false);
	private Boolean panelRepPedidosDejados = new Boolean(false);
	private Boolean panelBotonGenerar = new Boolean(false);
	private Boolean panelRepConsultaRepresentante = new Boolean(false);
	private Boolean panelHistorialPorRepresentante = new Boolean(false);
	private Boolean panelHistoricoRepresentantes = new Boolean(false);
	private Boolean panelRepReparto = new Boolean(false);
	private Boolean panelDestinatarios = new Boolean(false);
	private Boolean panelRepConsultaRepresentantes = new Boolean(false);
	private Boolean panelRepManifiestoRutaEnCampania = new Boolean(false);
	private Boolean panelRepSalidaCamioneta = new Boolean(false);
	private Boolean panelRepRecepcionCarga = new Boolean(false);
	private Boolean panelRepDescargaEnrutado = new Boolean(false);
	private Boolean enviarReporte = new Boolean(false);
	private Boolean pnlListaNARGlobal = new Boolean(false);
	private Boolean panelRepAnalisisEfectivo = new Boolean(false);
	private Boolean pnlListaNARTotal = new Boolean(false);
	private Boolean panelReportePremios = new Boolean(false);
	private Boolean panelReporteResumen = new Boolean(false);
	private Boolean panelReporteItemsNoEntregados = new Boolean(false);
	private Boolean panelReporteConsultaEstatusOrdenes = new Boolean(false);
	private Boolean panelReporteBuenVecino = new Boolean(false);
	private Boolean panelReporteRecoleccionDeRemito = new Boolean(false);
	private Boolean panelReporteItemsNoEscaneados = new Boolean(false);
	private Boolean panelReporteFaltanteDeCajas = new Boolean(false);
	private Boolean panelReporteResumenGeneralZonas = new Boolean(false);

	private List<SelectItem> listaCampanias = null;
	private List<SelectItem> listaZonas = null;
	private List<SelectItem> listaGerentes = null;
	private List<SelectItem> listaRutas = null;
	private List<SelectItem> listaSubBodegas = null;
	private List<SelectItem> divisiones = null;
	private List<SelectItem> listaAnios = null;
	private ArchivoCorreo archivoCorreo;
	JasperGenerator jasper = new JasperGenerator();
	ConsultaDatosReportes obtenerData = new ConsultaDatosReportes();
	private String mensajeError = "";
	private String formatoReporte;
	// Inician las variables de filtros
	private Date filtroFecha1;
	private Integer filtroIdCampania;
	private Integer filtroIdZona;
	private Integer filtroIdDivision;
	private Integer filtroIdGerente;
	private Integer filtroIdRuta;
	private String filtroString1;
	private Integer filtroSubBodega;
	private Integer filtroAnio;
	private String filtroDescripcionPremio;
	private String filtroCampaniaRep;
	private String filtroRegistro;
	private String observacionesRecepcion;
	private Reporte[] arrayReportes;

	public List<SelectItem> getDivisiones() {
		obtenerData.obtenerDivisiones();
		divisiones = new ArrayList<SelectItem>();
		SelectItem s = new SelectItem();
		s.setValue("0");
		s.setLabel("Seleccione");
		divisiones.add(s);
		divisiones.addAll(obtenerData.obtenerDivisiones());
		return divisiones;
	}

	public void setDivisiones(List<SelectItem> divisiones) {
		this.divisiones = divisiones;
	}

	public Boolean getPanelReporteResumenGeneralZonas() {
		return panelReporteResumenGeneralZonas;
	}

	public void setPanelReporteResumenGeneralZonas(
			Boolean panelReporteResumenGeneralZonas) {
		this.panelReporteResumenGeneralZonas = panelReporteResumenGeneralZonas;
	}

	public Integer getFiltroIdDivision() {
		return filtroIdDivision;
	}

	public void setFiltroIdDivision(Integer filtroIdDivision) {
		this.filtroIdDivision = filtroIdDivision;
	}

	public String getObservacionesRecepcion() {
		return observacionesRecepcion;
	}

	public void setObservacionesRecepcion(String observacionesRecepcion) {
		this.observacionesRecepcion = observacionesRecepcion;
	}

	public Boolean getPanelReporteItemsNoEscaneados() {
		return panelReporteItemsNoEscaneados;
	}

	public void setPanelReporteItemsNoEscaneados(
			Boolean panelReporteItemsNoEscaneados) {
		this.panelReporteItemsNoEscaneados = panelReporteItemsNoEscaneados;
	}

	public Boolean getPanelReporteRecoleccionDeRemito() {
		return panelReporteRecoleccionDeRemito;
	}

	public void setPanelReporteRecoleccionDeRemito(
			Boolean panelReporteRecoleccionDeRemito) {
		this.panelReporteRecoleccionDeRemito = panelReporteRecoleccionDeRemito;
	}

	public Boolean getPanelReporteFaltanteDeCajas() {
		return panelReporteFaltanteDeCajas;
	}

	public void setPanelReporteFaltanteDeCajas(
			Boolean panelReporteFaltanteDeCajas) {
		this.panelReporteFaltanteDeCajas = panelReporteFaltanteDeCajas;
	}

	public String getFiltroRegistro() {
		return filtroRegistro;
	}

	public void setFiltroRegistro(String filtroRegistro) {
		this.filtroRegistro = filtroRegistro;
	}

	public String getFiltroCampaniaRep() {
		return filtroCampaniaRep;
	}

	public void setFiltroCampaniaRep(String filtroCampaniaRep) {
		this.filtroCampaniaRep = filtroCampaniaRep;
	}

	public ControllerGeneradorReportes() {
		this.repSel = 0;
		HttpServletRequest req = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		if (idModulo == 0 && req.getParameter("pm") != null) {
			idModulo = Integer.parseInt(req.getParameter("pm"));
		}
	}

	public List<SelectItem> getReportes() {

		List<SelectItem> reportes = new ArrayList<SelectItem>();

		/*
		 * SelectItem item1 = new SelectItem();
		 * item1.setLabel("Pedidos Entregados a Gerente Zonal");
		 * item1.setValue("10"); reportes.add(item1);
		 * 
		 * SelectItem item2 = new SelectItem();
		 * item2.setLabel("Pedidos Dejados en Sub bodega"); item2.setValue("9");
		 * reportes.add(item2);
		 * 
		 * SelectItem item3 = new SelectItem();
		 * item3.setLabel("Consulta representante"); item3.setValue("2");
		 * reportes.add(item3);
		 * 
		 * SelectItem item4 = new SelectItem();
		 * item4.setLabel("Historial Por Representante"); item4.setValue("4");
		 * reportes.add(item4);
		 * 
		 * SelectItem item5 = new SelectItem();
		 * item5.setLabel("Historico Representantes"); item5.setValue("5");
		 * reportes.add(item5);
		 * 
		 * SelectItem item6 = new SelectItem();
		 * item6.setLabel("Manifiesto de Ruta en Campaña"); item6.setValue("8");
		 * reportes.add(item6);
		 * 
		 * SelectItem item7 = new SelectItem();
		 * item7.setLabel("Control de Salida de Camioneta");
		 * item7.setValue("12"); reportes.add(item7);
		 * 
		 * SelectItem item8 = new SelectItem();
		 * item8.setLabel("Lista NAR Global"); item8.setValue("6");
		 * reportes.add(item8);
		 * 
		 * SelectItem item9 = new SelectItem();
		 * item9.setLabel("Control de Recepción de Carga");
		 * item9.setValue("11"); reportes.add(item9);
		 * 
		 * SelectItem item10 = new SelectItem();
		 * item10.setLabel("Descarga y Enrutado"); item10.setValue("3");
		 * reportes.add(item10);
		 * 
		 * SelectItem item11 = new SelectItem();
		 * item11.setLabel("Analisis de Depositos de Efectivo");
		 * item11.setValue("1"); reportes.add(item11);
		 * 
		 * SelectItem item12 = new SelectItem();
		 * item12.setLabel("Lista NAR Total"); item12.setValue("7");
		 * reportes.add(item12);
		 * 
		 * SelectItem item13 = new SelectItem(); item13.setLabel("Reparto");
		 * item13.setValue("13"); reportes.add(item13);
		 * 
		 * SelectItem item14 = new SelectItem(); item14.setLabel("Premios");
		 * item14.setValue("14"); reportes.add(item14);
		 * 
		 * SelectItem item15 = new SelectItem(); item15.setLabel("Resumen");
		 * item15.setValue("15"); reportes.add(item15);
		 * 
		 * Collections.sort(reportes, new SelectItemComparator());
		 * 
		 * SelectItem item0 = new SelectItem();
		 * item0.setLabel("Seleccione Reporte"); item0.setValue("0");
		 * reportes.add(0, item0);
		 */

		ConsultaDatosReportes consulta = new ConsultaDatosReportes();
		arrayReportes = consulta.obtenerReportes(config.getIdLDC());

		if (arrayReportes != null) {
			for (Reporte reporte : arrayReportes) {
				SelectItem item = new SelectItem();
				item.setLabel(reporte.getNombre());
				item.setValue(reporte.getIdReporte());
				reportes.add(item);
			}
		}

		Collections.sort(reportes, new SelectItemComparator());
		SelectItem item0 = new SelectItem();
		item0.setLabel("Seleccione Reporte");
		item0.setValue("0");
		reportes.add(0, item0);

		return reportes;

	}

	public List<SelectItem> getRutas() {
		if (listaRutas == null) {
			cargarRutas();
		}

		return listaRutas;
	}

	public List<SelectItem> getRutasSeleccione() {

		cargarRutasSeleccione();

		return listaRutas;
	}

	public List<SelectItem> getListaLDC() {
		List<SelectItem> ListaLdc = new ArrayList<SelectItem>();

		Utils util = new Utils();
		UtilsStub.LDC[] arregloLdc = util.getListaLDC(0);

		if (arregloLdc != null && arregloLdc.length > 0) {
			if (arregloLdc.length > 1) {
				SelectItem item = new SelectItem();
				item.setLabel("Seleccione LDC");
				item.setValue(0);
				ListaLdc.add(0, item);
			}
			for (UtilsStub.LDC ldc : arregloLdc) {
				SelectItem item = new SelectItem();
				item.setLabel(ldc.getDESCRIPCION() + '-'
						+ ldc.getRAZON_SOCIAL());
				item.setValue(ldc.getID_LDC());
				ListaLdc.add(item);
			}
		} else {
			SelectItem item = new SelectItem();
			item.setLabel("No hay LDC revise configuración");
			item.setValue(0);
			ListaLdc.add(0, item);
		}

		return ListaLdc;
	}

	public List<SelectItem> getListaAnios() {
		if (listaAnios != null && listaAnios.size() > 1)
			return listaAnios;

		listaAnios = new ArrayList<SelectItem>();

		Utils util = new Utils();
		Campania[] arrCampanias = util.getCampanias("REPORTES_ANIOS", 0, 0, 0);

		if (arrCampanias != null && arrCampanias.length > 0) {

			SelectItem item = null;

			for (Campania camp : arrCampanias) {
				item = new SelectItem();
				item.setLabel(String.valueOf(camp.getAnioCampania()));
				item.setValue(camp.getAnioCampania());
				listaAnios.add(item);
			}
		} else {
			SelectItem item = new SelectItem();
			item.setLabel("No hay Campañas revise configuración");
			item.setValue(0);
			listaAnios.add(0, item);
		}

		return listaAnios;
	}

	public List<SelectItem> getListaCampanias() {

		listaCampanias = new ArrayList<SelectItem>();

		Utils util = new Utils();
		Campania[] arrCampanias = util.getCampanias("REPORTES_NUM_CAMPANIAS",
				0, 0, 0);

		if (arrCampanias != null && arrCampanias.length > 0) {

			SelectItem item = null;
			item = new SelectItem();
			item.setLabel("Seleccione campaña");
			item.setValue(0);
			listaCampanias.add(item);

			for (Campania camp : arrCampanias) {
				item = new SelectItem();
				item.setLabel(String.valueOf(camp.getCampania()));
				item.setValue(camp.getCampania());
				listaCampanias.add(item);
			}
		} else {
			SelectItem item = new SelectItem();
			item.setLabel("No hay Campañas revise configuración");
			item.setValue(0);
			listaCampanias.add(0, item);
		}
		return listaCampanias;
	}

	/**
	 * Listener para el llenado de la lista de rutas
	 * 
	 * @author jorge.torner
	 * @since 31/01/2012
	 * @param e
	 */
	public void llenarRutas(ValueChangeEvent e) {
		mensajeError = "";
		if (e.getNewValue() != null) {
			filtroIdZona = Integer.parseInt(e.getNewValue().toString());
			cargarRutas();

		}
	}

	public void actualizarCampanias(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			filtroIdZona = Integer.parseInt(e.getNewValue().toString());
			getCampanias();
			enviarReporte = false;
			archivoCorreo = null;
			observacionesRecepcion = "";
			mensajeError = "";
		}
	}

	public void actualizarCampaniasCarga(ActionEvent e) {

		getCampanias();
		enviarReporte = false;
		archivoCorreo = null;
		observacionesRecepcion = "";
		mensajeError = "";

	}

	public void actualizarObservaciones(ActionEvent e) {
		ConsultaDatosReportes rep = new ConsultaDatosReportes();
		observacionesRecepcion = rep
				.consultarDatosReporteControlRecepcionCargaObservaciones(
						filtroIdZona, filtroIdCampania, config.getIdUsuario());
		enviarReporte = false;
		archivoCorreo = null;
		mensajeError = "";
	}

	public void reseteaValorArchivo(ActionEvent e) {

		enviarReporte = false;
		archivoCorreo = null;
		mensajeError = "";

	}

	public void actualizarCombos(ValueChangeEvent e) {
		mensajeError = "";
		if (e.getNewValue() != null) {
			filtroIdZona = Integer.parseInt(e.getNewValue().toString());
			cargarRutas();
			getCampanias();
			enviarReporte = false;
			archivoCorreo = null;

		}
	}

	/**
	 * @author javier.gallegos
	 * @since 19-01-2012
	 * @return metodo que carga los posibles Filtros del reporte seleccionado
	 */
	public void cargarFiltrosV(ValueChangeEvent e) {
		repSel = Integer.parseInt(e.getNewValue().toString());
		ocultaPaneles();
		if (repSel != 0) {
			limpiarIdCombos();
			panelBotonGenerar = true;
			panelDestinatarios = true;
			enviarReporte = false;
			archivoCorreo = null;
			filtroIdZona = 0;
			filtroIdRuta = 0;
			filtroIdCampania = 0;
			filtroRegistro = "";
			mensajeError = "";

			switch (repSel) {

			case 1:
				panelRepAnalisisEfectivo = true;
				break;
			case 2:
				panelRepConsultaRepresentante = true;
				break;
			case 3:
				panelRepDescargaEnrutado = true;
				break;
			case 4:
				panelHistorialPorRepresentante = true;
				break;
			case 5:
				panelHistoricoRepresentantes = true;
				break;
			case 6:
				pnlListaNARGlobal = true;
				break;
			case 7:
				pnlListaNARTotal = true;
				break;
			case 8:
				panelRepManifiestoRutaEnCampania = true;
				break;
			case 9:
				panelRepPedidosDejados = true;
				break;
			case 10:
				panelRepPedidosPrestados = true;
				break;
			case 11:
				panelRepRecepcionCarga = true;
				break;
			case 12:
				panelRepSalidaCamioneta = true;
				break;
			case 13:
				panelRepReparto = true;
				break;
			case 14:
				panelReportePremios = true;
				break;
			case 15:
				panelReporteResumen = true;
				break;
			case 20:
				panelReporteItemsNoEntregados = true;
				break;
			case 21:
				panelReporteConsultaEstatusOrdenes = true;
				break;
			case 22:
				panelReporteBuenVecino = true;
				break;
			case 23:
				panelReporteFaltanteDeCajas = true;
				break;
			case 24:
				panelReporteRecoleccionDeRemito = true;
				break;
			case 28:
				panelReporteItemsNoEscaneados = true;
				break;
			case 30:
				panelReporteResumenGeneralZonas = true;
				break;
			}
		} else {
			// mensaje debe seleccionar un reporte
			panelBotonGenerar = false;
			panelDestinatarios = false;
		}

	}

	public void getSubBodegasReportePedidosDejados(ValueChangeEvent e) {

		if (e.getNewValue() != null) {
			filtroIdRuta = Integer.parseInt(e.getNewValue().toString());
			getSubBodegas();
		}
	}

	/**
	 * Carga la lista de las rutas de acuerdo a los filtros
	 * 
	 * @author jorge.torner
	 * @since 31/01/2012
	 */
	public void cargarRutas() {

		if (filtroIdZona != null && filtroIdZona > 0) {
			Utils utils = new Utils();
			Rutas[] rutas = utils.getRutas("CUADMIN_REPMANIFIESTORUTA",
					config.getIdLDC(), filtroIdZona, 0);

			listaRutas = new ArrayList<SelectItem>();
			if (rutas != null) {
				for (int o = 0; o < rutas.length; o++) {
					listaRutas.add(new SelectItem(rutas[o].getIdRuta(),
							rutas[o].getCveRuta()));
				}
			} else {
				listaRutas.add(new SelectItem("0",
						"No hay Rutas para la zona seleccionada"));

			}
		} else {
			listaRutas = new ArrayList<SelectItem>();
		}
	}

	public void cargarRutasSeleccione() {

		if (filtroIdZona != null && filtroIdZona > 0) {
			Utils utils = new Utils();
			Rutas[] rutas = utils.getRutas("CUADMIN_REPMANIFIESTORUTA",
					config.getIdLDC(), filtroIdZona, 0);

			listaRutas = new ArrayList<SelectItem>();
			if (rutas != null) {
				for (int o = 0; o < rutas.length; o++) {
					listaRutas.add(new SelectItem(rutas[o].getIdRuta(),
							rutas[o].getCveRuta()));
				}
				listaRutas.add(new SelectItem("0", "Seleccione Ruta"));
			} else {
				listaRutas.add(new SelectItem("0",
						"No hay Rutas para la zona seleccionada"));

			}
		} else {
			listaRutas = new ArrayList<SelectItem>();
		}
	}

	public List<SelectItem> getGerentes() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		if (listaGerentes == null) {

			ConsultaDatosReportes consulta = new ConsultaDatosReportes();
			lista = consulta.obtenerListaEntregadoA(config.getIdLDC());
			listaGerentes = lista;
		} else {
			lista = listaGerentes;
		}
		return lista;
	}

	public List<SelectItem> getSubBodegas() {

		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem itemSel = new SelectItem();
		itemSel.setLabel("Selecciona Sub bodega");
		itemSel.setValue(0);
		lista.add(itemSel);

		try {
			ConsultaDatosReportes consulta = new ConsultaDatosReportes();
			lista = consulta.obtenerSubBodegasPedidosDejados(0, filtroIdZona,
					config.getIdPais(), config.getIdLDC(),
					config.getIdUsuario());
			listaSubBodegas = lista;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	public List<SelectItem> getZonas() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		if (listaZonas == null || listaZonas.size() <= 1) {

			ConsultaDatosReportes consulta = new ConsultaDatosReportes();
			lista = consulta.obtenerZonas(ldcSel);
			listaZonas = lista;
		} else {
			lista = listaZonas;
		}
		return lista;
	}

	public List<SelectItem> getCampanias() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		ConsultaDatosReportes consulta = new ConsultaDatosReportes();
		lista = consulta.obtenerCampanias(config.getIdLDC(), filtroIdRuta,
				config.getIdUsuario(), filtroIdZona);
		listaCampanias = lista;

		return lista;
	}

	public List<SelectItem> getCampaniasSinID() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		ConsultaDatosReportes consulta = new ConsultaDatosReportes();
		lista = consulta.obtenerCampaniasSinId();
		listaCampanias = lista;

		return lista;
	}

	public List<SelectItem> getCampaniasReporte() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		ConsultaDatosReportes consulta = new ConsultaDatosReportes();
		lista = consulta.obtenerCampaniasReportes();
		listaCampanias = lista;

		return lista;
	}

	public List<DestinatarioReporte> getDestinatarios() {
		List<DestinatarioReporte> lista = new ArrayList<DestinatarioReporte>();

		ConsultaDatosReportes consulta = new ConsultaDatosReportes();
		if (repSel != null) {
			lista = consulta
					.obtenerDestinatariosReporte(getDescripcionReporte(repSel));

		}

		return lista;
	}

	private void ocultaPaneles() {
		panelRepPedidosDejados = false;
		panelRepPedidosPrestados = false;
		panelRepConsultaRepresentante = false;
		panelHistorialPorRepresentante = false;
		panelHistoricoRepresentantes = false;
		panelRepManifiestoRutaEnCampania = false;
		panelRepSalidaCamioneta = false;
		panelRepRecepcionCarga = false;
		pnlListaNARGlobal = false;
		panelRepDescargaEnrutado = false;
		panelRepAnalisisEfectivo = false;
		panelRepReparto = false;
		pnlListaNARTotal = false;
		panelReportePremios = false;
		panelReporteResumen = false;
		panelReporteItemsNoEntregados = false;
		panelReporteConsultaEstatusOrdenes = false;
		panelReporteBuenVecino = false;
		panelReporteFaltanteDeCajas = false;
		panelReporteRecoleccionDeRemito = false;
		panelReporteItemsNoEscaneados = false;
		panelReporteResumenGeneralZonas = false;

	}

	private void limpiarIdCombos() {
		filtroIdCampania = 0;
		filtroIdZona = 0;
		filtroIdGerente = 0;
		filtroIdRuta = 0;
		filtroIdDivision = 0;
	}

	public HashMap<String, Boolean> getMapaAccion() {
		HashMap<String, Boolean> mapa = new HashMap<String, Boolean>();
		@SuppressWarnings("unchecked")
		HashMap<Integer, HashMap<String, Boolean>> mapaFull = (HashMap<Integer, HashMap<String, Boolean>>) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("accionesM");
		mapa = mapaFull.get(idModulo);

		return mapa;
	}

	public String generarReporte() {

		mensajeError = "";

		switch (repSel) {

		case 1:
			generarReporteAnalisisEfectivo();
			break;
		case 2:
			generaRepConsultaRepresentantes();
			break;
		case 3:
			generarReporteDescargaEnrutado();
			break;
		case 4:
			generarReporteHistorialPorRepresentante();
			break;
		case 5:
			generarReporteHistoricoRepresentantes();
			break;
		case 6:
			generaNoAceptacionReparto();
			break;
		case 7:
			generaNoAceptacionRepartoTotal();
			break;
		case 8:
			generarReporteManifiestoRutaEnCampania();
			break;
		case 9:
			generaRepPedDejados();
			break;
		case 10:
			generaRepPedPrestados();
			break;
		case 11:
			generarReporteRecepcionCarga();
			break;
		case 12:
			generarReporteSalidaCamioneta();
			break;
		case 13:
			generarReporteReparto();
			break;
		case 14:
			generarReporteDePremios();
			break;
		case 15:
			generarReporteResumen();
			break;
		case 20:
			generarReporteItemsNoEntregadosEnAlmacen();
			break;

		case 21:
			generarReporteConsultaEstatusOrdenes();
			break;
		case 22:
			generarReporteConsultaBUenVecino();
			break;
		case 23:
			generarReporteFaltanteDeCajas();
			break;
		case 24:
			generarReporteRecoleccionRemito();
			break;
		case 28:
			generaReporteItemsNoEscaneados();
			break;
		case 30:
			generaReporteIResumenGeneralZonas();
			break;
		}

		return "";
	}

	private void generaReporteIResumenGeneralZonas() {

		String descDivision = "";
		String descZona = "";

		if (filtroIdDivision != 0) {
			descDivision = getDescripcionDivision(filtroIdDivision);
		} else {
			descDivision = "";
		}
		if (filtroIdZona != 0) {
			descZona = getDescripcionZona(filtroIdZona);
		}
		String descCampania = getDescripcionCampaniaRep(filtroCampaniaRep);

		List<ModelReporteResumenGeneralZonas> datos = obtenerData
				.consultaDatosReporteResumenGeneralZona("" + filtroCampaniaRep,
						"" + filtroIdZona, "" + filtroIdDivision);
		if (datos.size() == 0) {
			mensajeError = "No se cuenta con órdenes facturadas  para zonas en campaña: "
					+ descCampania
					+ ",  favor de revisar la fecha de  facturación de las zonas en esa campaña";

			if (filtroIdZona != 0) {
				mensajeError = "No se cuenta con órdenes facturadas  para la zona: "
						+ descZona
						+ " en campaña: "
						+ descCampania
						+ ",  favor de revisar la fecha de  facturación de dicha zona";
			}
			if (filtroIdDivision != 0) {
				mensajeError = "No se cuenta con órdenes facturadas  para zonas de división: "
						+ descDivision
						+ " en campaña: "
						+ descCampania
						+ ",  favor de revisar la fecha de  facturación de las zonas";
			}
		}
		if (datos.size() > 0) {
			enviarReporte = true;
			try {
				archivoCorreo = jasper.generaReporteResumenGeneralZona(datos,
						formatoReporte, descCampania, descDivision,
						getNombreReporte(repSel), Utils.obtenerFechaReportes());
			} catch (IOException e) {
				mensajeError = "Error al generar el reporte en el formato seleccionado.";
			}
		}

	}

	/**
	 * Genera reporte de Lista de No ACeptacion de Reparto NAR
	 * 
	 * @author brenda.estrada
	 * @since 02/02/2012
	 */
	private void generaNoAceptacionReparto() {
		if (filtroIdCampania != 0 && filtroIdZona != 0) {
			List<NoAceptacionReparto> dataNAR = null;
			String descZona = "";
			String descCamp = "";
			descZona = getDescripcionZona(filtroIdZona);
			descCamp = getDescripcionCampania(filtroIdCampania);
			dataNAR = obtenerData
					.obtenerDatosNAR(filtroIdZona, filtroIdCampania,
							config.getIdUsuario(), descZona, descCamp);

			if (dataNAR.size() > 0) {
				enviarReporte = true;
				try {
					archivoCorreo = jasper.generaReporteNAR(dataNAR,
							formatoReporte, getNombreReporte(repSel));
				} catch (IOException e) {
					mensajeError = "Error al generar el reporte en el formato seleccionado.";
				}
			} else {
				mensajeError = "No existen datos que mostrar en el reporte de No Aceptación de Reparto.";
			}
		} else {
			mensajeError = "Debe seleccionar filtros de Zona y Campaña.";
		}
	}

	public String generarReportePrueba() {
		JasperGenerator generador = new JasperGenerator();
		List<ModelRelPedidosDejados> modelo = new ArrayList<ModelRelPedidosDejados>();
		if (modelo != null && modelo.size() > 0) {
			enviarReporte = true;
			try {
				archivoCorreo = generador.generaReportePedidosDejados(modelo,
						formatoReporte, getNombreReporte(repSel));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			mensajeError = "No existen datos para los filtros seleccionados";
		}
		return "";
	}

	private void generaRepConsultaRepresentantes() {
		if (filtroString1 != null && !filtroString1.equals("")) {
			mensajeError = "";
			ConsultaDatosReportes consulta = new ConsultaDatosReportes();
			JasperGenerator generador = new JasperGenerator();
			List<ModelRepConsultaRepresentantes> valores = consulta
					.datosReporteConsultaRepresentantes(filtroString1,
							config.getIdLDC());
			if (valores != null && valores.size() > 0) {
				enviarReporte = true;
				try {
					archivoCorreo = generador
							.generaReporteConsultaRepresentantes(valores,
									formatoReporte, getNombreReporte(repSel));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				mensajeError = "No existen datos para generar el reporte de Consulta representante";
			}
		} else {
			mensajeError = "Olvidó el campo Account";
		}
	}

	private void generaRepPedPrestados() {
		if (filtroIdCampania != 0 && filtroIdZona != 0) {
			List<ModelRelPedidosPrestados> valores = new ArrayList<ModelRelPedidosPrestados>();

			ConsultaDatosReportes consulta = new ConsultaDatosReportes();
			valores = consulta.datosReportePedidosPrestados(filtroIdZona,
					filtroIdCampania, filtroIdGerente, filtroFecha1);
			if (valores.size() > 0) {
				enviarReporte = true;
				JasperGenerator generador = new JasperGenerator();
				try {
					archivoCorreo = generador.generaReportePedidosPrestados(
							valores, formatoReporte, getNombreReporte(repSel));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				mensajeError = "No hay datos con los filtros seleccionados para mostrar el reporte";
			}
		} else {
			mensajeError = "Debe seleccionar valor para todos los filtros";
		}

	}

	private void generaRepPedDejados() {
		if (filtroIdCampania != 0 && filtroIdZona != 0 && filtroIdRuta != 0) {

			ConsultaDatosReportes consulta = new ConsultaDatosReportes();
			List<ModelRelPedidosDejados> valores = consulta
					.datosReporteRelacionPedidosDejados(filtroIdRuta,
							filtroIdCampania, ldcSel, filtroSubBodega);
			if (valores.size() > 0) {
				enviarReporte = true;
				JasperGenerator generador = new JasperGenerator();
				try {
					archivoCorreo = generador.generaReportePedidosDejados(
							valores, formatoReporte, getNombreReporte(repSel));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				mensajeError = "No hay datos con los filtros seleccionados para mostrar el reporte";
			}
		} else {
			mensajeError = "Debe seleccionar valor para todos los filtros";
		}
	}

	private void generarReporteManifiestoRutaEnCampania() {
		// Validamos filtros
		if (filtroIdCampania != 0 && filtroIdZona != 0 && filtroIdRuta != 0) {
			mensajeError = "";
			// Obtenemos datos de reporte y asignamos a lista de la estructura
			// necesaria
			ConsultaDatosReportes reportes = new ConsultaDatosReportes();
			ModelManifiestoRutaEnCampania datosReporte = reportes
					.obtenerReporteManifiestoRutaEnCampania(filtroIdRuta,
							filtroIdCampania, config.getIdUsuario(), ldcSel);

			if (datosReporte != null) {
				enviarReporte = true;
				List<ModelManifiestoRutaEnCampania> lReporte = new ArrayList<ModelManifiestoRutaEnCampania>();
				lReporte.add(datosReporte);
				JasperGenerator generador = new JasperGenerator();
				try {
					archivoCorreo = generador
							.generaReporteManifiestoRutaEnCampania(lReporte,
									formatoReporte, getNombreReporte(repSel));
				} catch (IOException e) {
					mensajeError = "Error al intentar generar el reporte";
				}
			} else
				mensajeError = "No hay datos para mostrar en el reporte";
		} else {
			mensajeError = "Debe seleccionar valor para todos los filtros";
		}

	}

	private void generarReporteItemsNoEntregadosEnAlmacen() {
		// Validamos filtros
		if (filtroIdZona != 0 && filtroIdCampania != 0) {
			mensajeError = "";
			// Obtenemos datos de reporte y asignamos a lista de la estructura
			// necesaria
			ConsultaDatosReportes reportes = new ConsultaDatosReportes();
			List<ModelReporteItemsNoEntregadosEnAlmacen> datosReporte = reportes
					.obtenerItemsNoEntregadosEnAlmacen(filtroIdRuta,
							filtroIdCampania, config.getIdUsuario(),
							filtroIdZona);

			if (datosReporte != null && datosReporte.size() > 0) {
				enviarReporte = true;
				JasperGenerator generador = new JasperGenerator();
				try {
					archivoCorreo = generador
							.generaReporteItemsNoEntregadosEnAlmacen(
									datosReporte, formatoReporte,
									getNombreReporte(repSel));
				} catch (IOException e) {
					mensajeError = "Error al intentar generar el reporte";
				}
			} else
				mensajeError = "No hay datos para mostrar en el reporte";
		} else {
			mensajeError = "Debe seleccionar valor para todos los filtros";
		}

	}

	private void generarReporteConsultaEstatusOrdenes() {
		// Validamos filtros
		if (!filtroCampaniaRep.equals("0") && filtroString1 != null
				&& filtroString1.length() > 0) {
			mensajeError = "";
			// Obtenemos datos de reporte y asignamos a lista de la estructura
			// necesaria
			ConsultaDatosReportes reportes = new ConsultaDatosReportes();
			List<ModelReporteConsultaEstatusOdenesRep> datosReporte = reportes
					.obtenerConsultaEstatusOrdenesRep(
							filtroCampaniaRep.substring(2, 6),
							filtroCampaniaRep.substring(0, 2),
							config.getIdUsuario(), filtroString1);

			if (datosReporte != null && datosReporte.size() > 0) {
				enviarReporte = true;
				JasperGenerator generador = new JasperGenerator();
				try {
					archivoCorreo = generador
							.generaReporteConsultaEstatusOrdenes(datosReporte,
									formatoReporte, filtroCampaniaRep,
									filtroString1, getNombreReporte(repSel));
				} catch (IOException e) {
					mensajeError = "Error al intentar generar el reporte";
				}
			} else
				mensajeError = "No hay datos para mostrar en el reporte";
		} else {
			mensajeError = "Debe seleccionar valor para todos los filtros";
		}

	}

	private void generarReporteRecoleccionRemito() {
		// Validamos filtros
		if (filtroIdZona != 0) {
			mensajeError = "";
			// Obtenemos datos de reporte y asignamos a lista de la estructura
			// necesaria

			ConsultaDatosReportes reportes = new ConsultaDatosReportes();
			List<ModelRepRecoleccionRemitos> datosReporte = reportes
					.datosReporteRecoleccionDeRemito(config.getIdUsuario(),
							filtroIdZona, filtroIdCampania, filtroIdRuta);
			if (datosReporte != null && datosReporte.size() > 0) {
				enviarReporte = true;
				JasperGenerator generador = new JasperGenerator();
				try {
					archivoCorreo = generador.generaReporteRecoleccionRemitos(
							datosReporte, formatoReporte,
							getNombreReporte(repSel));
				} catch (IOException e) {
					mensajeError = "Error al intentar generar el reporte";
				}
			} else
				mensajeError = "No existe información para los filtros proporcionados";
		} else {
			mensajeError = "Por favor, seleccione la zona";
		}

	}

	private void generaReporteItemsNoEscaneados() {
		// Validamos filtros
		if (filtroIdZona != 0 && filtroIdCampania != 0) {
			mensajeError = "";
			// Obtenemos datos de reporte y asignamos a lista de la estructura
			// necesaria

			ConsultaDatosReportes reportes = new ConsultaDatosReportes();
			List<ModelReporteItemsNoEscaneados> datosReporte = reportes
					.datosReporteItemsNoEscaneados(config.getIdUsuario(),
							filtroIdZona, filtroIdCampania);
			if (datosReporte != null && datosReporte.size() > 0) {
				enviarReporte = true;
				JasperGenerator generador = new JasperGenerator();
				try {
					archivoCorreo = generador.generaReporteItemsNoEscaneados(
							datosReporte, formatoReporte,
							getNombreReporte(repSel));
				} catch (IOException e) {
					mensajeError = "Error al intentar generar el reporte";
				}
			} else
				mensajeError = "No existen datos para generar el reporte con los filtros seleccionados";
		} else {
			mensajeError = "Por favor, seleccione Zona/Campaña";
		}

	}

	private void generarReporteFaltanteDeCajas() {
		// Validamos filtros
		if (filtroIdZona != 0 && filtroIdCampania != 0) {
			mensajeError = "";
			// Obtenemos datos de reporte y asignamos a lista de la estructura
			// necesaria

			ConsultaDatosReportes reportes = new ConsultaDatosReportes();
			List<ModelRepFaltantesTotales> datosReporte = reportes
					.datosReporteItemsFaltantesTotales(config.getIdUsuario(),
							filtroIdZona, filtroIdCampania);

			List<ModelRepFaltantesCajas> listaCajas = reportes
					.datosReporteItemsFaltantesDetalleCajas(
							config.getIdUsuario(), filtroIdZona,
							filtroIdCampania);

			List<ModelRepFaltantesUnitarios> listaUnitarios = reportes
					.datosReporteItemsFaltantesDetalleUnitarios(
							config.getIdUsuario(), filtroIdZona,
							filtroIdCampania);

			String zona = "";

			List<SelectItem> zons = getZonas();
			if (zons != null && zons.size() > 0) {
				for (SelectItem s : zons) {
					if (s.getValue().equals("" + filtroIdZona)) {
						zona = s.getLabel();
					}
				}
			}

			String campania = "";

			List<SelectItem> camps = getCampanias();
			if (camps != null && camps.size() > 0) {
				for (SelectItem s : camps) {
					if (Integer.parseInt(s.getValue().toString()) == filtroIdCampania) {
						campania = s.getLabel();
					}
				}
			}
			if (datosReporte != null && datosReporte.size() > 0
					&& listaCajas != null && listaUnitarios != null
					&& (listaCajas.size() > 0 || listaUnitarios.size() > 0)) {
				enviarReporte = true;
				datosReporte.get(0).setDetalleCajas(listaCajas);
				datosReporte.get(0).setDetalleUnitarios(listaUnitarios);

				JasperGenerator generador = new JasperGenerator();
				try {
					archivoCorreo = generador.generaReporteFaltanteCajas(
							datosReporte, formatoReporte, zona, campania,
							getNombreReporte(repSel));
				} catch (IOException e) {
					mensajeError = "Error al intentar generar el reporte";
				}
			} else
				mensajeError = "No existen datos para generar el reporte con los filtros seleccionados";
		} else {
			mensajeError = "Por favor, seleccione ambos criterios de Búsqueda";
		}

	}

	private void generarReporteControlRecepcionDeCarga() {
		// Validamos filtros
		if (filtroIdZona != 0 && filtroIdCampania != 0) {
			mensajeError = "";
			// Obtenemos datos de reporte y asignamos a lista de la estructura
			// necesaria

			ConsultaDatosReportes reportes = new ConsultaDatosReportes();
			List<ModelReporteConcultaBuenVecino> datosReporte = reportes
					.datosReporteControlRecepcionDeCarga(config.getIdUsuario(),
							filtroIdZona, filtroIdCampania);
			String zona = "";
			String campania = "";
			if (datosReporte != null && datosReporte.size() > 0) {
				enviarReporte = true;
				JasperGenerator generador = new JasperGenerator();
				try {
					archivoCorreo = generador
							.generaReporteControlRecepcionDeCarga(datosReporte,
									formatoReporte, zona, campania,
									getNombreReporte(repSel));
				} catch (IOException e) {
					mensajeError = "Error al intentar generar el reporte";
				}
			} else
				mensajeError = "No hay datos para los criterios seleccionados";
		} else {
			mensajeError = "Por favor, seleccione los criterios de Búsqueda";
		}

	}

	private void generarReporteConsultaBUenVecino() {
		// Validamos filtros
		if (filtroIdZona != 0) {
			mensajeError = "";
			// Obtenemos datos de reporte y asignamos a lista de la estructura
			// necesaria
			if (filtroRegistro == null) {
				filtroRegistro = "";
			}
			ConsultaDatosReportes reportes = new ConsultaDatosReportes();
			List<ModelReporteConcultaBuenVecino> datosReporte = reportes
					.datosReporteConsultaBuenVecino(config.getIdUsuario(),
							filtroIdZona, filtroIdRuta, filtroRegistro);
			String ruta = "";
			String zona = "";

			List<SelectItem> zons = getZonas();
			if (zons != null && zons.size() > 0) {
				for (SelectItem s : zons) {
					if (s.getValue().equals("" + filtroIdZona)) {
						zona = s.getLabel();
					}
				}
			}

			if (filtroIdRuta != 0) {
				List<SelectItem> rutas = getRutasSeleccione();
				if (rutas != null && rutas.size() > 0) {
					for (SelectItem s : rutas) {
						if (s.getValue().equals("" + filtroIdRuta)) {
							ruta = s.getLabel();
						}
					}
				}
			}
			if (datosReporte != null && datosReporte.size() > 0) {
				enviarReporte = true;
				JasperGenerator generador = new JasperGenerator();
				try {
					archivoCorreo = generador.generaReporteConsultaBuenVecino(
							datosReporte, formatoReporte, zona, ruta,
							getNombreReporte(repSel));
				} catch (IOException e) {
					mensajeError = "Error al intentar generar el reporte";
				}
			} else
				mensajeError = "No hay datos para los criterios seleccionados";
		} else {
			mensajeError = "Por favor, seleccione zona.";
		}

	}

	public String generaReporteMail() {
		// try {
		// JobMandarMailDejados job= new JobMandarMailDejados();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return "";
	}

	private void generarReporteHistorialPorRepresentante() {
		mensajeError = "";
		List<ModelHistorialPorRepresentante> valores = new ArrayList<ModelHistorialPorRepresentante>();

		if (filtroString1.equals("")) {
			filtroString1 = "0";
		}
		int registroRepresentante = Integer.parseInt(filtroString1);
		ConsultaDatosReportes consulta = new ConsultaDatosReportes();
		valores = consulta.consultarDatosReporteHistorialPorRepresentante(
				String.valueOf(filtroIdCampania), config.getIdUsuario(),
				ldcSel, registroRepresentante, String.valueOf(filtroAnio));

		if (valores != null && valores.size() > 0) {
			enviarReporte = true;
			JasperGenerator generador = new JasperGenerator();
			try {
				archivoCorreo = generador
						.generaReporteHistorialPorRepresentante(valores,
								formatoReporte, getNombreReporte(repSel));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (valores != null) {
			mensajeError = "No existen datos para generar el reporte";
		} else {
			mensajeError = "Ocurrió un error al generar el reporte";
		}
	}

	private void generarReporteHistoricoRepresentantes() {
		mensajeError = "";
		List<ModelHistoricoDeRepresentantes> valores = null;

		String descZona = null;
		String descCamp = null;
		int numZona = 0;

		if (filtroIdZona != 0) {
			try {
				descZona = getDescripcionZona(filtroIdZona);
				numZona = Integer.parseInt(descZona);
			} catch (NumberFormatException e) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M11",
						"Surgió un error al generar el archivo del reporte Historico de Representantes",
						e.getMessage(), config.getIdUsuario());
			}
		}

		if (filtroIdCampania != 0) {
			descCamp = String.valueOf(filtroIdCampania);
		}

		ConsultaDatosReportes consulta = new ConsultaDatosReportes();
		valores = consulta.consultarDatosReporteHistoricoRepresentantes(
				numZona, filtroIdCampania, config.getIdUsuario(), ldcSel,
				filtroAnio);
		if (valores != null && valores.size() > 0) {
			enviarReporte = true;
			valores.get(0).setZona(descZona);
			valores.get(0).setCampania(descCamp);
			JasperGenerator generador = new JasperGenerator();
			try {
				archivoCorreo = generador.generaReporteHistoricoRepresentantes(
						valores, formatoReporte, getNombreReporte(repSel));
			} catch (IOException e) {
				Utils.GuardarLogMensajeBD("ReportesAdmin", "M11",
						"Surgió un error al generar el archivo "
								+ formatoReporte
								+ " del reporte Historico de Representantes",
						e.getMessage(), config.getIdUsuario());
				e.printStackTrace();
			}
		} else if (valores != null) {
			mensajeError = "No existen datos para generar el reporte";
		} else {
			mensajeError = "Ocurrió un error al generar el reporte";
		}
	}

	private void generarReporteAnalisisEfectivo() {

		if (filtroIdCampania != 0 && filtroIdZona != 0) {
			List<ModelAnalisisEfectivo> valores = new ArrayList<ModelAnalisisEfectivo>();
			ConsultaDatosReportes consulta = new ConsultaDatosReportes();
			valores = consulta.consultarDatosReporteAnalisisEfectivo(
					filtroIdCampania, filtroIdZona, filtroIdRuta,
					config.getIdUsuario(), config.getIdLDC());
			if (valores != null && valores.size() > 0) {
				enviarReporte = true;
				JasperGenerator generador = new JasperGenerator();
				archivoCorreo = generador.generaReporteAnalisisEfectivo(
						valores, formatoReporte, getNombreReporte(repSel));
			} else {
				mensajeError = "No existen datos que mostrar en el reporte de Analisis de Efectivo.";
			}
		} else {
			mensajeError = "Debe seleccionar valor para todos los filtros";
		}
	}

	private void generarReporteSalidaCamioneta() {
		if (filtroIdCampania != 0 && filtroIdZona != 0 && filtroIdRuta != 0) {

			mensajeError = "";
			ConsultaDatosReportes consulta = new ConsultaDatosReportes();
			JasperGenerator generador = new JasperGenerator();
			try {

				List<ModelRepSalidaCamioneta> datos = consulta
						.datosReporteSalidaCamioneta(filtroIdCampania,
								filtroIdRuta, filtroIdZona, config.getIdLDC(),
								filtroFecha1);
				if (datos != null && datos.size() > 0) {
					enviarReporte = true;
					archivoCorreo = generador.generaReporteSalidaCamioneta(
							datos, formatoReporte, getNombreReporte(repSel));
				} else {
					mensajeError = "No existen datos que mostrar en el reporte de Control de Salida de Camioneta";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			mensajeError = "Debe seleccionar valor para todos los filtros";
		}
	}

	private void generarReporteRecepcionCarga() {
		// antes de generarlo guardamos las observaciones ingresadas
		ConsultaDatosReportes consulta = new ConsultaDatosReportes();
		boolean res = consulta
				.consultarDatosReporteControlRecepcionCargaActualizaObservaciones(
						filtroIdZona, filtroIdCampania, observacionesRecepcion,
						config.getIdUsuario());

		if (filtroIdCampania != 0 && filtroIdZona != 0) {
			mensajeError = "";

			List<ModelRepRecepcionCarga> datos = consulta
					.consultarDatosReporteControlRecepcionCarga(filtroIdZona,
							filtroIdCampania, config.getIdLDC(),
							config.getIdUsuario());
			if (datos != null && datos.size() > 0) {
				enviarReporte = true;
				JasperGenerator generador = new JasperGenerator();
				try {
					archivoCorreo = generador.generaReporteRecepcionCarga(
							datos, formatoReporte, observacionesRecepcion,
							getNombreReporte(repSel));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				mensajeError = "No existen datos para generar el reporte con los filtros seleccionados.";
			}

		} else {
			mensajeError = "Por favor seleccione Zona y/o Campaña.";
		}
	}

	private void generarReporteDescargaEnrutado() {

		mensajeError = "";
		ConsultaDatosReportes consulta = new ConsultaDatosReportes();
		JasperGenerator generador = new JasperGenerator();
		String descZona = getDescripcionZona(filtroIdZona);

		if (filtroIdZona == 0) {
			descZona = "0";
		}

		try {
			List<ModelRepDescargaEnrutado> valores = consulta
					.obtenerDescargaEnrutado(Integer.parseInt(descZona),
							filtroIdCampania, filtroAnio, ldcSel);
			if (valores != null && valores.size() > 0) {
				enviarReporte = true;
				archivoCorreo = generador.generaReporteDescargaEnrutado(
						valores, formatoReporte, getNombreReporte(repSel));
			} else if (valores != null) {
				mensajeError = "No existen datos para generar el reporte";
			} else {
				mensajeError = "Ocurrió un error al generar el reporte";
			}
		} catch (IOException e) {
			Utils.GuardarLogMensajeBD("ReportesAdmin", "M11",
					"Surgió un error al generar el archivo " + formatoReporte
							+ " del reporte de Descarga y Enrutado",
					e.getMessage(), config.getIdUsuario());
			mensajeError = "Ocurrió un error al generar el reporte";
		} catch (NumberFormatException e) {
			Utils.GuardarLogMensajeBD("ReportesAdmin", "M11",
					"Surgió un error al generar reporte Descarga y Enrutado",
					e.getMessage(), config.getIdUsuario());
			mensajeError = "Ocurrió un error al generar el reporte";
		}
	}

	private void generarReporteReparto() {

		mensajeError = "";
		String descZona = getDescripcionZona(filtroIdZona);
		ModelHeaderReparto datosReparto = null;
		if (filtroIdZona == 0) {
			descZona = "0";
		}

		ConsultaDatosReportes consulta = new ConsultaDatosReportes();
		try {
			datosReparto = consulta.obtenerDatosReparto(
					Integer.parseInt(descZona), filtroIdCampania, filtroAnio,
					config.getIdLDC(), config.getIdUsuario());
			if (datosReparto != null) {
				enviarReporte = true;
				JasperGenerator generador = new JasperGenerator();
				archivoCorreo = generador.generaReporteReparto(datosReparto,
						formatoReporte, getNombreReporte(repSel));
			} else {
				mensajeError = "No existen datos para generar el reporte";
			}
		} catch (IOException e) {
			Utils.GuardarLogMensajeBD("ReportesAdmin", "M11",
					"Surgió un error al generar reporte Reparto",
					e.getMessage(), config.getIdUsuario());
			mensajeError = "Ocurrió un error al generar el reporte";
		} catch (NumberFormatException e) {
			Utils.GuardarLogMensajeBD("ReportesAdmin", "M11",
					"Surgió un error al generar reporte Reparto",
					e.getMessage(), config.getIdUsuario());
			mensajeError = "Ocurrió un error al generar el reporte";
		} catch (Exception e) {
			Utils.GuardarLogMensajeBD("ReportesAdmin", "M11",
					"Surgió un error al generar reporte Reparto",
					e.getMessage(), config.getIdUsuario());
			mensajeError = "Ocurrió un error al generar el reporte";
		}
	}

	/**
	 * Genera reporte de Lista de No ACeptacion de Reparto NAR Total
	 * 
	 * @author brenda.estrada
	 * @since 02/02/2012
	 */
	private void generaNoAceptacionRepartoTotal() {
		if (filtroIdCampania != 0 && filtroIdZona != 0) {
			List<NoAceptacionRepartoTotal> dataNART = null;
			String descZona = "";
			String descCamp = "";
			descZona = getDescripcionZona(filtroIdZona);
			descCamp = getDescripcionCampania(filtroIdCampania);
			// Obtener Datos de Objetos NAR Total
			dataNART = obtenerData
					.obtenerDatosNARTotal(filtroIdZona, filtroIdCampania,
							config.getIdUsuario(), descZona, descCamp);

			if (dataNART.size() > 0) {
				// Si existen Datos - Invoca metodo que GENERA PDF XLS
				enviarReporte = true;
				try {
					// Se ejecuta el metodo para generar el reporte en el
					// Formato Seleccionado
					archivoCorreo = jasper.generaReporteNARTotal(dataNART,
							formatoReporte, getNombreReporte(repSel));
				} catch (IOException e) {
					mensajeError = "Error al generar el reporte en el formato seleccionado.";
				}
			} else {
				mensajeError = "No existen datos que mostrar en el reporte de No Aceptación de Reparto.";
			}
		} else {
			mensajeError = "Debe seleccionar filtros de Zona y Campaña.";
		}
	}

	private void generarReporteDePremios() {
		mensajeError = "";
		String descZona = null;
		descZona = getDescripcionZona(filtroIdZona);
		String descCampania = null;

		if (filtroIdZona == 0) {
			descZona = null;
		}
		if (filtroIdCampania != 0) {
			descCampania = String.valueOf(filtroIdCampania);
		}

		ConsultaDatosReportes consulta = new ConsultaDatosReportes();
		List<ModelRepPremios> listaPremios = consulta
				.consultarDatosReportePremios(descZona,
						String.valueOf(filtroIdCampania), filtroString1,
						filtroDescripcionPremio, ldcSel, config.getIdUsuario(),
						String.valueOf(filtroAnio));

		if (listaPremios != null && listaPremios.size() > 0) {
			enviarReporte = true;
			listaPremios.get(0).setZonaH(descZona);
			listaPremios.get(0).setCampaniaH(descCampania);
			JasperGenerator generador = new JasperGenerator();
			try {
				archivoCorreo = generador.generarReporteDePremios(listaPremios,
						formatoReporte, getNombreReporte(repSel));
			} catch (IOException e) {
				Utils.GuardarLogMensajeBD("ReportesAdmin", "M14",
						"Surgió un error al generar el archivo "
								+ formatoReporte + " del reporte de premios",
						e.getMessage(), config.getIdUsuario());
				e.printStackTrace();
			}
		} else if (listaPremios != null) {
			mensajeError = "No existen datos para generar el reporte de premios";
		} else {
			mensajeError = "Ocurrió un error al generar el reporte de premios";
		}
	}

	private void generarReporteResumen() {

		ConsultaDatosReportes consulta = new ConsultaDatosReportes();
		ModelHeaderResumen valores = null;
		try {
			valores = consulta.consultarDatosReporteResumen(filtroIdCampania,
					filtroAnio, config.getIdLDC(), config.getIdUsuario());
			if (valores != null) {
				enviarReporte = true;
				valores.setAnioCampania(String.valueOf(filtroAnio));
				JasperGenerator generador = new JasperGenerator();
				archivoCorreo = generador.generarReporteResumen(valores,
						formatoReporte, getNombreReporte(repSel));
			} else {
				mensajeError = "No existen datos para generar el reporte de resumen";
			}
		} catch (IOException e) {
			Utils.GuardarLogMensajeBD("ReportesAdmin", "M15",
					"Surgió un error al generar el archivo " + formatoReporte
							+ " del reporte de resumen", e.getMessage(),
					config.getIdUsuario());
			mensajeError = "Surgió un error al generar el reporte de Resumen";
		} catch (Exception e) {
			Utils.GuardarLogMensajeBD("ReportesAdmin", "M15",
					"Surgió un error al generar el archivo " + formatoReporte
							+ " del reporte de resumen", e.getMessage(),
					config.getIdUsuario());
			mensajeError = "Surgió un error al generar el reporte de Resumen";
		}
	}

	public String enviarReporte() {
		mensajeError = "";
		if (enviarReporte) {
			if (getDestinatarios().size() > 0) {
				if (archivoCorreo != null) {
					mensajeError = "";
					EnvioReporteMail envio = new EnvioReporteMail();
					String extension = "";
					if ("PDF".equals(formatoReporte)) {
						extension = "pdf";
					} else if ("XLS".equals(formatoReporte)) {
						extension = "xls";
					} else if ("CSV".equals(formatoReporte)) {
						extension = "csv";
					}
					List<ArchivoCorreo> archivos = new ArrayList<ArchivoCorreo>();
					String nombreArchivo = Utils.generarNombreArchivoCorreo(
							getDescripcionReporte(repSel),
							config.getClaveLDC(), extension);
					archivoCorreo.setNombreArchivo(nombreArchivo);

					archivos.add(archivoCorreo);
					String descripcionReporte = getDescripcionReporteBien(repSel);
					String asuntoMail = " OTS_" + config.getClaveLDC()
							+ " : Envío Reporte  " + descripcionReporte + "";

					String textoMail = "A través del presente se adjunta el reporte: ";

					textoMail += "" + descripcionReporte + "";

					String recipientes = "";
					int cont = 0;
					for (DestinatarioReporte destinatario : getDestinatarios()) {
						if (cont == 0) {
							recipientes = recipientes + destinatario.getMail();
						} else {
							recipientes = recipientes + ","
									+ destinatario.getMail();
						}
						cont++;
					}

					mensajeError = envio.enviarReporteMail(archivos,
							config.getIdLDC(), recipientes, textoMail,
							asuntoMail, config.getIdUsuario(), repSel,
							getDescripcionReporte(repSel));
					if ("".equals(mensajeError.trim())) {
						mensajeError = "Correo Enviado";
					}
				} else {
					mensajeError = "No se tiene el reporte disponible para enviar "
							+ "por correo electrónico. Favor de seleccionar antes los "
							+ "criterios de consulta y oprimir el botón \"Generar reporte\" y"
							+ " después oprima \"Enviar reporte\"";
				}
			} else {
				mensajeError = "No se tiene configurado este reporte para enviarse por correo"
						+ " a un destinatario. Para enviar el reporte por correo electrónico debe"
						+ " primeros asignar dicho reporte a destinatarios desde la opción"
						+ " en el sistema \"Catalogo de Reporte por Tipo de destinatario\"";
			}
		} else {
			mensajeError = "No se tiene el reporte disponible para enviar "
					+ "por correo electrónico. Favor de seleccionar antes los "
					+ "criterios de consulta y oprimir el botón \"Generar reporte\" y"
					+ " después oprima \"Enviar reporte\"";
		}
		return "";
	}

	private String getDescripcionZona(Integer idZona) {
		String zona = "";
		for (SelectItem item : getZonas()) {
			Integer idZonaValue = Integer.parseInt(String.valueOf(item
					.getValue()));
			if (idZona.equals(idZonaValue)) {
				zona = item.getLabel();
			}
		}
		return zona;
	}

	private String getDescripcionDivision(Integer idDivision) {
		String zona = "";
		for (SelectItem item : getDivisiones()) {
			Integer idZonaValue = Integer.parseInt(String.valueOf(item
					.getValue()));
			if (idDivision.equals(idZonaValue)) {
				zona = item.getLabel();
			}
		}
		return zona;
	}

	private String getDescripcionCampaniaRep(String idDivision) {
		String zona = "";
		for (SelectItem item : getCampaniasReporte()) {
			String idZonaValue = String.valueOf(item.getValue());
			if (idDivision.equals(idZonaValue)) {
				zona = item.getLabel();
			}
		}
		return zona;
	}

	private String getDescripcionEntregado(Integer idEntregado) {
		String entregado = "";
		for (SelectItem item : getGerentes()) {
			if (item.getValue() == idEntregado) {
				entregado = item.getLabel();
			}
		}
		return entregado;
	}

	private String getDescripcionRuta(Integer idRuta) {
		String ruta = "";
		for (SelectItem item : listaRutas) {
			if (item.getValue() == idRuta) {
				ruta = item.getLabel();
			}
		}
		return ruta;
	}

	private String getDescripcionCampania(Integer idCamp) {
		String campa = "";
		for (SelectItem item : getCampanias()) {
			if (item.getValue().equals(idCamp)) {
				campa = item.getLabel();
			}
		}
		return campa;
	}

	private String getDescripcionCampaniaReporte(String idCamp) {
		String campa = "";
		for (SelectItem item : getCampanias()) {
			if (item.getValue().equals(idCamp)) {
				campa = item.getLabel();
			}
		}
		return campa;
	}

	private String getDescripcionReporte(Integer idRep) {
		String rep = "";
		try {
			for (SelectItem item : getReportes()) {
				if (idRep.equals(Integer.parseInt(item.getValue().toString()))) {
					rep = item.getLabel();
					break;
				}
			}
		} catch (Exception ex) {
		}

		return rep;
	}

	private String getDescripcionReporteBien(Integer idRep) {
		String rep = "";
		if (arrayReportes != null) {
			for (Reporte reporte : arrayReportes) {
				if (reporte.getIdReporte() == idRep) {
					rep = reporte.getDescripcion();
				}
			}
		}
		return rep;
	}

	private String getNombreReporte(Integer idRep) {
		String rep = "";
		try {
			for (SelectItem item : getReportes()) {
				if (idRep.equals(Integer.parseInt(item.getValue().toString()))) {
					rep = item.getLabel();
					break;
				}
			}
		} catch (Exception ex) {
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String fecha = df.format(new Date());

		String res = rep + "_" + config.getClaveLDC() + "_" + fecha;
		return res;
	}

	// METODOS GET Y SET. ATRIBUTOS QUE CONTROLAN LOS ELEMENTOS DE LA PÁGINA DE
	// REPORTES.
	public Boolean getPnlListaNARGlobal() {
		return pnlListaNARGlobal;
	}

	public void setPnlListaNARGlobal(Boolean pnlListaNARGlobal) {
		this.pnlListaNARGlobal = pnlListaNARGlobal;
	}

	public Boolean getPnlListaNARTotal() {
		return pnlListaNARTotal;
	}

	public void setPnlListaNARTotal(Boolean pnlListaNARTotal) {
		this.pnlListaNARTotal = pnlListaNARTotal;
	}

	public Integer getLdcSel() {
		return ldcSel;
	}

	public void setLdcSel(Integer ldcSel) {
		this.ldcSel = ldcSel;
	}

	public String getFiltroString1() {
		return filtroString1;
	}

	public String getFiltroDescripcionPremio() {
		return filtroDescripcionPremio;
	}

	public void setFiltroDescripcionPremio(String filtroDescripcionPremio) {
		this.filtroDescripcionPremio = filtroDescripcionPremio;
	}

	public void setFiltroString1(String filtroString1) {
		this.filtroString1 = new Utils().EliminaCerosIzquierda(filtroString1);
	}

	public Integer getFiltroIdCampania() {
		return filtroIdCampania;
	}

	public void setFiltroIdCampania(Integer filtroIdCampania) {
		this.filtroIdCampania = filtroIdCampania;
	}

	public Integer getFiltroIdZona() {
		return filtroIdZona;
	}

	public void setFiltroIdZona(Integer filtroIdZona) {
		this.filtroIdZona = filtroIdZona;
	}

	public Integer getFiltroIdGerente() {
		return filtroIdGerente;
	}

	public void setFiltroIdGerente(Integer filtroIdGerente) {
		this.filtroIdGerente = filtroIdGerente;
	}

	public Date getFiltroFecha1() {
		return filtroFecha1;
	}

	public void setFiltroFecha1(Date filtroFecha1) {
		this.filtroFecha1 = filtroFecha1;
	}

	public Integer getFiltroIdRuta() {
		return filtroIdRuta;
	}

	public void setFiltroIdRuta(Integer filtroIdRuta) {
		this.filtroIdRuta = filtroIdRuta;
	}

	public Integer getFiltroSubBodega() {
		return filtroSubBodega;
	}

	public void setFiltroSubBodega(Integer filtroSubBodega) {
		this.filtroSubBodega = filtroSubBodega;
	}

	public Integer getFiltroAnio() {
		return filtroAnio;
	}

	public void setFiltroAnio(Integer filtroAnio) {
		this.filtroAnio = filtroAnio;
	}

	public Boolean getPanelRepAnalisisEfectivo() {
		return panelRepAnalisisEfectivo;
	}

	public void setPanelRepAnalisisEfectivo(Boolean panelRepAnalisisEfectivo) {
		this.panelRepAnalisisEfectivo = panelRepAnalisisEfectivo;
	}

	public Boolean getPanelReporteConsultaEstatusOrdenes() {
		return panelReporteConsultaEstatusOrdenes;
	}

	public void setPanelReporteConsultaEstatusOrdenes(
			Boolean panelReporteConsultaEstatusOrdenes) {
		this.panelReporteConsultaEstatusOrdenes = panelReporteConsultaEstatusOrdenes;
	}

	public Boolean getPanelRepReparto() {
		return panelRepReparto;
	}

	public void setPanelRepReparto(Boolean panelRepReparto) {
		this.panelRepReparto = panelRepReparto;
	}

	public Boolean getPanelRepDescargaEnrutado() {
		return panelRepDescargaEnrutado;
	}

	public void setPanelRepDescargaEnrutado(Boolean panelRepDescargaEnrutado) {
		this.panelRepDescargaEnrutado = panelRepDescargaEnrutado;
	}

	public Boolean getPanelRepRecepcionCarga() {
		return panelRepRecepcionCarga;
	}

	public void setPanelRepRecepcionCarga(Boolean panelRepRecepcionCarga) {
		this.panelRepRecepcionCarga = panelRepRecepcionCarga;
	}

	public Boolean getEnviarReporte() {
		return enviarReporte;
	}

	public void setEnviarReporte(Boolean enviarReporte) {
		this.enviarReporte = enviarReporte;
	}

	public Boolean getPanelRepSalidaCamioneta() {
		return panelRepSalidaCamioneta;
	}

	public void setPanelRepSalidaCamioneta(Boolean panelRepSalidaCamioneta) {
		this.panelRepSalidaCamioneta = panelRepSalidaCamioneta;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public Boolean getPanelRepManifiestoRutaEnCampania() {
		return panelRepManifiestoRutaEnCampania;
	}

	public void setPanelRepManifiestoRutaEnCampania(
			Boolean panelRepManifiestoRutaEnCampania) {
		this.panelRepManifiestoRutaEnCampania = panelRepManifiestoRutaEnCampania;
	}

	public Boolean getPanelRepConsultaRepresentantes() {
		return panelRepConsultaRepresentantes;
	}

	public void setPanelRepConsultaRepresentantes(
			Boolean panelRepConsultaRepresentantes) {
		this.panelRepConsultaRepresentantes = panelRepConsultaRepresentantes;
	}

	public Boolean getPanelDestinatarios() {
		return panelDestinatarios;
	}

	public void setPanelDestinatarios(Boolean panelDestinatarios) {
		this.panelDestinatarios = panelDestinatarios;
	}

	public Boolean getpanelHistoricoRepresentantes() {
		return panelHistoricoRepresentantes;
	}

	public void setpanelHistoricoRepresentantes(
			Boolean panelHistoricoRepresentantes) {
		this.panelHistoricoRepresentantes = panelHistoricoRepresentantes;
	}

	public Boolean getpanelHistorialPorRepresentante() {
		return panelHistorialPorRepresentante;
	}

	public void setpanelHistorialPorRepresentante(
			Boolean panelHistorialPorRepresentante) {
		this.panelHistorialPorRepresentante = panelHistorialPorRepresentante;
	}

	public Boolean getPanelRepConsultaRepresentante() {
		return panelRepConsultaRepresentante;
	}

	public void setPanelRepConsultaRepresentante(
			Boolean panelRepConsultaRepresentante) {
		this.panelRepConsultaRepresentante = panelRepConsultaRepresentante;
	}

	public Boolean getPanelBotonGenerar() {
		return panelBotonGenerar;
	}

	public void setPanelBotonGenerar(Boolean panelBotonGenerar) {
		this.panelBotonGenerar = panelBotonGenerar;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public Boolean getPanelRepPedidosDejados() {
		return panelRepPedidosDejados;
	}

	public void setPanelRepPedidosDejados(Boolean panelRepPedidosDejados) {
		this.panelRepPedidosDejados = panelRepPedidosDejados;
	}

	public Boolean getPanelReportePremios() {
		return panelReportePremios;
	}

	public void setPanelReportePremios(Boolean panelReportePremios) {
		this.panelReportePremios = panelReportePremios;
	}

	public Boolean getPanelRepPedidosPrestados() {
		return panelRepPedidosPrestados;
	}

	public void setPanelRepPedidosPrestados(Boolean panelRepPedidosPrestados) {
		this.panelRepPedidosPrestados = panelRepPedidosPrestados;
	}

	public Integer getRepSel() {
		return repSel;
	}

	public void setRepSel(Integer repSel) {
		this.repSel = repSel;
	}

	public UIForm getForm() {
		return form;
	}

	public void setForm(UIForm form) {
		this.form = form;
	}

	public Boolean getPanelReporteResumen() {
		return panelReporteResumen;
	}

	public void setPanelReporteResumen(Boolean panelReporteResumen) {
		this.panelReporteResumen = panelReporteResumen;
	}

	public Boolean getPanelReporteItemsNoEntregados() {
		return panelReporteItemsNoEntregados;
	}

	public void setPanelReporteItemsNoEntregados(
			Boolean panelReporteItemsNoEntregados) {
		this.panelReporteItemsNoEntregados = panelReporteItemsNoEntregados;
	}

	public Boolean getPanelReporteBuenVecino() {
		return panelReporteBuenVecino;
	}

	public void setPanelReporteBuenVecino(Boolean panelReporteBuenVecino) {
		this.panelReporteBuenVecino = panelReporteBuenVecino;
	}

}
